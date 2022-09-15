package com.ohgiraffers.intranet.notice.controller;

import com.ohgiraffers.intranet.common.paging.Pagenation;
import com.ohgiraffers.intranet.common.paging.SelectCriteria;
import com.ohgiraffers.intranet.notice.model.dto.*;
import com.ohgiraffers.intranet.notice.model.service.NoticeService;
import net.coobird.thumbnailator.Thumbnails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;


@Controller
@RequestMapping(value = {"/notice/*"})
public class NoticeController {

//    @Value("src/main/resources")
    private String IMAGE_DIR;
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final NoticeService noticeService;

    @Autowired
    public NoticeController(NoticeService noticeService){

        this.noticeService = noticeService;
    }

    //공지사항
    @GetMapping("/regist")
    public String noticeRegistPage(){

        return "notice/noticeRegist";
    }

    @PostMapping("/regist")
    public String noticeRegist(@ModelAttribute NoticeDTO notice, HttpServletRequest request, @RequestParam(name="originName", required=false) MultipartFile originName) throws FileNotFoundException {

        //notice insert
        int registResult = noticeService.noticeRegist(notice);


        log.info("registResult : " + registResult);

        NoticeFileDTO noticeFile = new NoticeFileDTO();

        String filePath = ResourceUtils.getURL("src/main/resources").getPath() + "upload";

        String fileUploadDirectory = filePath +	 "/noticeFile";

        File mkdir = new File(fileUploadDirectory);

        if(!mkdir.exists()){
            mkdir.mkdirs();
        }

        String originFileName = "";
        String ext = "";
        String saveName = "";

        if(originName.getSize() > 0) { //파일 첨부한거 있으면
            //파일명 변경하고
            originFileName = originName.getOriginalFilename();
            ext = originFileName.substring(originFileName.lastIndexOf("."));
            saveName = UUID.randomUUID().toString().replace("-", "") + ext;

            noticeFile.setOriginName(originFileName);
            noticeFile.setSaveName(saveName);
            noticeFile.setSavePath(fileUploadDirectory);

            log.info("noticeFileDTO값 확인 : " + noticeFile);
            // file insert
            int fileResult = noticeService.noticeFileInsert(noticeFile);

            try {
                originName.transferTo(new File(fileUploadDirectory + "//" + saveName));

            } catch (IOException e) {

                e.printStackTrace();
                new File(fileUploadDirectory + "//" + saveName).delete();
            }

        }

        return "redirect:/notice/list";

    }

    @GetMapping("/list")
    public ModelAndView noticeList(HttpServletRequest request, ModelAndView mv){

        String currentPage = request.getParameter("currentPage");
        int pageNo = 1;

        if(currentPage != null && !"".equals(currentPage)){
            pageNo = Integer.parseInt(currentPage);
        }

        String searchCondition = request.getParameter("searchCondition");
        String searchValue = request.getParameter("searchValue");

        Map<String, String> searchMap = new HashMap<>();
        searchMap.put("searchCondition", searchCondition);
        searchMap.put("searchValue", searchValue);
        log.info("검색조건 확인 : " +searchMap);

        int totalCount = noticeService.selectTotalCount(searchMap);

        int limit = 10;
        int buttonAmount = 5;

        SelectCriteria selectCriteria = null;

        if(searchCondition != null && !"".equals(searchCondition)){

            selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount,
                    searchCondition, searchValue);
        } else {

            selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
        }
        log.info("selectCriteria 확인 : " + selectCriteria);

        List<NoticeDTO> noticeList = noticeService.selectNoticeList(selectCriteria);
        log.info("noticeList값 확인 : " + noticeList);

        mv.addObject("noticeList", noticeList);
        mv.addObject("selectCriteria", selectCriteria);
        log.info("selectCriteria 확인 : " + selectCriteria);

        log.info("dept값 가져오나 확인 : " + noticeList);

        mv.setViewName("notice/noticeList");

        return mv;
    }

    @GetMapping("/detail")
    public String noticeDetailPage(HttpServletRequest request, Model model){


        int no = Integer.parseInt(request.getParameter("no"));
        log.info("no 값 대체 머야 : " + request.getParameter("no"));

        NoticeDTO noticeDetail = noticeService.selectNoticeDetail(no);
        model.addAttribute("notice", noticeDetail);

        return "/notice/noticeDetail";
    }

    @GetMapping("/update")
    public String noticeUpdatePage(HttpServletRequest request, Model model){

        int no = Integer.parseInt(request.getParameter("no"));
        log.info("no 값 대체 머야 : " + request.getParameter("no"));

        NoticeDTO notice = noticeService.selectNoticeDetail(no);
        model.addAttribute("notice", notice);

        return"notice/noticeUpdate";
    }

    @PostMapping("/update")
    public String noticeUpdate(@ModelAttribute NoticeDTO notice, RedirectAttributes rttr, @RequestParam(name="originName", required=false) MultipartFile originName) throws FileNotFoundException {

        NoticeFileDTO noticeFile = new NoticeFileDTO();

        String filePath = ResourceUtils.getURL("src/main/resources").getPath() + "upload";

        String fileUploadDirectory = filePath + "/noticeFile";

        File mkdir = new File(fileUploadDirectory);

        if(!mkdir.exists()){
            mkdir.mkdirs();
        }

        String originFileName = "";
        String ext = "";
        String saveName = "";

        if(originName.getSize() > 0) { //파일 첨부한거 있으면
            //파일명 변경하고
            originFileName = originName.getOriginalFilename();
            ext = originFileName.substring(originFileName.lastIndexOf("."));
            saveName = UUID.randomUUID().toString().replace("-", "") + ext;

            int result = noticeService.noticeUpdate(notice);

            if(result > 0){

                if(notice.getFile() != null){

                int result2 = noticeService.noticeFileDelete(notice.getNo());

                if(result2 > 0){

                    noticeFile.setNtNo(notice.getNo());
                    noticeFile.setOriginName(originFileName);
                    noticeFile.setSaveName(saveName);
                    noticeFile.setSavePath(fileUploadDirectory);

                    noticeService.noticeFileUpdate(noticeFile);
                }

                } else {

                    noticeFile.setNtNo(notice.getNo());
                    noticeFile.setOriginName(originFileName);
                    noticeFile.setSaveName(saveName);
                    noticeFile.setSavePath(fileUploadDirectory);

                    noticeService.noticeFileUpdate(noticeFile);
                }

            }

            log.info("noticeFileDTO값 확인 : " + noticeFile);
            // file insert

            try {
                originName.transferTo(new File(fileUploadDirectory + "//" + saveName));
            } catch (IOException e) {

                e.printStackTrace();
                new File(fileUploadDirectory + "//" + saveName).delete();
            }

        }

        log.info("notice값 불러오나 확인 " + notice);


        rttr.addFlashAttribute("message", "수정이 완료되었습니다");

        return "redirect:/notice/list";
    }

    @GetMapping("/delete")
    public String noticeDelete(@ModelAttribute NoticeDTO notice, HttpServletRequest request){

        int no = Integer.parseInt(request.getParameter("no"));
        log.info("no값 들고오는지 확인 : " + no);

        noticeService.noticeDelete(notice);

        return "redirect:/notice/list";
    }

    // 사내소식
    @GetMapping("/news/regist")
    public String newsRegistPage(){

        return "notice/news/newsRegist";
    }

    @PostMapping("/news/regist")
    public String newsRegist(@ModelAttribute NewsDTO news, HttpServletRequest request, @RequestParam(name="originName", required=false) MultipartFile originName) throws FileNotFoundException {

        //notice insert
        int registResult = noticeService.newsRegist(news);


        log.info("registResult : " + registResult);

        NewsFileDTO newsFile = new NewsFileDTO();

        String filePath = ResourceUtils.getURL("src/main/resources").getPath() + "upload";

        String fileUploadDirectory = filePath + "/newsFile";

        File mkdir = new File(fileUploadDirectory);

        if(!mkdir.exists()){
            mkdir.mkdirs();
        }

        String originFileName = "";
        String ext = "";
        String saveName = "";

        if(originName.getSize() > 0) { //파일 첨부한거 있으면
            //파일명 변경하고
            originFileName = originName.getOriginalFilename();
            ext = originFileName.substring(originFileName.lastIndexOf("."));
            saveName = UUID.randomUUID().toString().replace("-", "") + ext;

            newsFile.setOriginName(originFileName);
            newsFile.setSaveName(saveName);
            newsFile.setSavePath(fileUploadDirectory);

            log.info("noticeFileDTO값 확인 : " + newsFile);
            // file insert
            int fileResult = noticeService.newsFileInsert(newsFile);

            try {
                originName.transferTo(new File(fileUploadDirectory + "//" + saveName));
            } catch (IOException e) {

                e.printStackTrace();
                new File(fileUploadDirectory + "//" + saveName).delete();
            }

        }

        return "redirect:/notice/news/list";

    }

    @GetMapping("/news/list")
    public ModelAndView newsList(HttpServletRequest request, ModelAndView mv){

        String currentPage = request.getParameter("currentPage");
        int pageNo = 1;

        if(currentPage != null && !"".equals(currentPage)){
            pageNo = Integer.parseInt(currentPage);
        }

        String searchCondition = request.getParameter("searchCondition");
        String searchValue = request.getParameter("searchValue");

        Map<String, String> searchMap = new HashMap<>();
        searchMap.put("searchCondition", searchCondition);
        searchMap.put("searchValue", searchValue);

        int totalCount = noticeService.selectNewsTotalCount(searchMap);

        int limit = 10;
        int buttonAmount = 5;

        SelectCriteria selectCriteria = null;

        if(searchCondition != null && !"".equals(searchCondition)){

            selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount,
                    searchCondition, searchValue);
        } else {

            selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
        }
        log.info("selectCriteria 확인 : " + selectCriteria);

        List<NewsDTO> newsList = noticeService.selectNewsList(selectCriteria);

        mv.addObject("newsList", newsList);
        mv.addObject("selectCriteria", selectCriteria);

        mv.setViewName("notice/news/newsList");

        return mv;
    }

    /* 사내소식 상세조회 */
    @GetMapping("/news/detail")
    public String newsDetailPage(HttpServletRequest request, Model model){


        int no = Integer.parseInt(request.getParameter("no"));

        NewsDTO newsDetail = noticeService.selectNewsDetail(no);
        model.addAttribute("news", newsDetail);

        return "/notice/news/newsDetail";
    }

    /* 사내소식 수정 */
    @GetMapping("/news/update")
    public String newsUpdatePage(HttpServletRequest request, Model model){

        int no = Integer.parseInt(request.getParameter("no"));

        NewsDTO news = noticeService.selectNewsDetail(no);
        model.addAttribute("news", news);

        return"notice/news/newsUpdate";
    }

    @PostMapping("/news/update")
    public String noticeUpdate(@ModelAttribute NewsDTO news, RedirectAttributes rttr, @RequestParam(name="originName", required=false) MultipartFile originName) throws FileNotFoundException {

        NewsFileDTO newsFile = new NewsFileDTO();

        String filePath = ResourceUtils.getURL("src/main/resources").getPath() + "upload";

        String fileUploadDirectory = filePath + "/newsFile";

        File mkdir = new File(fileUploadDirectory);

        if(!mkdir.exists()){
            mkdir.mkdirs();
        }

        String originFileName = "";
        String ext = "";
        String saveName = "";

        if(originName.getSize() > 0) { //파일 첨부한거 있으면
            //파일명 변경하고
            originFileName = originName.getOriginalFilename();
            ext = originFileName.substring(originFileName.lastIndexOf("."));
            saveName = UUID.randomUUID().toString().replace("-", "") + ext;

            int result = noticeService.newsUpdate(news);

            if(result > 0){

                if(news.getFile() != null){

                    int result2 = noticeService.newsFileDelete(news.getNo());

                    if(result2 > 0){

                        newsFile.setNwNo(news.getNo());
                        newsFile.setOriginName(originFileName);
                        newsFile.setSaveName(saveName);
                        newsFile.setSavePath(fileUploadDirectory);

                        noticeService.newsFileUpdate(newsFile);
                    }

                } else {

                    newsFile.setNwNo(news.getNo());
                    newsFile.setOriginName(originFileName);
                    newsFile.setSaveName(saveName);
                    newsFile.setSavePath(fileUploadDirectory);

                    noticeService.newsFileUpdate(newsFile);
                }
            }
            // file insert
            try {
                originName.transferTo(new File(fileUploadDirectory + "//" + saveName));
            } catch (IOException e) {

                e.printStackTrace();
                new File(fileUploadDirectory + "//" + saveName).delete();
            }

        }

        rttr.addFlashAttribute("message", "수정이 완료되었습니다");

        return "redirect:/notice/news/list";
    }

    @GetMapping("/news/delete")
    public String newsDelete(@ModelAttribute NewsDTO news, HttpServletRequest request){

        int no = Integer.parseInt(request.getParameter("no"));

        noticeService.newsDelete(news);

        return "redirect:/notice/news/list";
    }

    /* 갤러리 등록 */
    @GetMapping("/gallery/regist")
    public String galleryRegistPage(){

        return "notice/gallery/galleryRegist";
    }

    @PostMapping("gallery/regist")
    public String galleryRegist(@ModelAttribute GalleryDTO gallery, HttpServletRequest request,
                                @RequestParam("thumbnailImg1") MultipartFile thumbnailImg1,
                                @RequestParam("thumbnailImg2") MultipartFile thumbnailImg2,
                                @RequestParam("thumbnailImg3") MultipartFile thumbnailImg3,
                                @RequestParam("thumbnailImg4") MultipartFile thumbnailImg4,
                                @RequestParam("thumbnailImg5") MultipartFile thumbnailImg5,
                                @RequestParam("thumbnailImg6") MultipartFile thumbnailImg6,
                                @RequestParam("thumbnailImg7") MultipartFile thumbnailImg7,
                                @RequestParam("thumbnailImg8") MultipartFile thumbnailImg8,
                                RedirectAttributes rttr) throws FileNotFoundException {

        String rootLocation = IMAGE_DIR;

        String filePath = ResourceUtils.getURL("src/main/resources").getPath() + "upload";

        String fileUploadDirectory = filePath + "/galleryFile";
        String thumbnailDirectory = filePath + "/thumbnailFile";

        File directory = new File(fileUploadDirectory);
        File directory2 = new File(thumbnailDirectory);

        if(!directory.exists() || !directory2.exists()){

            directory.mkdirs();
            directory2.mkdirs();
        }

        List<Map<String, String>> fileList = new ArrayList<>();

        List<MultipartFile> paramFileList = new ArrayList<>();
        paramFileList.add(thumbnailImg1);
        paramFileList.add(thumbnailImg2);
        paramFileList.add(thumbnailImg3);
        paramFileList.add(thumbnailImg4);
        paramFileList.add(thumbnailImg5);
        paramFileList.add(thumbnailImg6);
        paramFileList.add(thumbnailImg7);
        paramFileList.add(thumbnailImg8);

        for(MultipartFile paramFile : paramFileList) {

            if (paramFile.getSize() > 0) {
                String originFileName = paramFile.getOriginalFilename();

                String ext = originFileName.substring(originFileName.lastIndexOf("."));
                if(ext != "jpg" || ext != "png" || ext != "gif" || ext != "jpeg"){

                }

                String savedFileName = UUID.randomUUID().toString().replace("-", "") + ext;

                try {
                    paramFile.transferTo(new File(fileUploadDirectory + "/" + savedFileName));

                    Map<String, String> fileMap = new HashMap<>();
                    fileMap.put("originFileName", originFileName);
                    fileMap.put("savedFileName", savedFileName);
                    fileMap.put("savePath", fileUploadDirectory);

                    String filedName = paramFile.getName();

                    int width = 1200;
                    int height = 900;

                    if("thumbnailImg1".equals(filedName)){
                        fileMap.put("fileType", "TITLE");
                    } else {
                        fileMap.put("fileType", "BODY");
                    }


                    Thumbnails.of(fileUploadDirectory + "/" + savedFileName).size(width, height)
                            .toFile(thumbnailDirectory + "/thumb_" + savedFileName);

                    fileMap.put("thumbnailPath", "/upload/thumbnailFile/thumb_" + savedFileName);

                    fileList.add(fileMap);

                    // galleryDTO에 값 담기

                    gallery.setGalleryFile(new ArrayList<GalleryFileDTO>());
                    List<GalleryFileDTO> list = gallery.getGalleryFile();
                    for (int i = 0; i < fileList.size(); i++) {
                        Map<String, String> file = fileList.get(i);

                        GalleryFileDTO tempFileInfo = new GalleryFileDTO();
                        tempFileInfo.setOriginName(file.get("originFileName"));
                        tempFileInfo.setSaveName(file.get("savedFileName"));
                        tempFileInfo.setSavePath(file.get("savePath"));
                        tempFileInfo.setThumbnailPath(file.get("thumbnailPath"));
                        tempFileInfo.setFileType(file.get("fileType"));

                        list.add(tempFileInfo);
                    }

                } catch (IllegalStateException | IOException e) {
                    e.printStackTrace();
                    //Exception 발생 시 파일 삭제
                    int cnt = 0;
                    for (int i = 0; i < fileList.size(); i++) {
                        Map<String, String> file = fileList.get(i);

                        File deleteFile = new File(fileUploadDirectory + "/" + file.get("savedFileName"));
                        boolean isDeleted1 = deleteFile.delete();

                        File deleteThumbnail = new File(thumbnailDirectory + "/thumb_" + file.get("savedFileName"));
                        boolean isDeleted2 = deleteThumbnail.delete();

                        if (isDeleted1 && isDeleted2) {
                            cnt++;
                        }
                    }

                    if (cnt == fileList.size()) {
                        log.info("업로드 실패한 사진 삭제 완료");
                        e.printStackTrace();
                    } else {
                        e.printStackTrace();
                    }
                }

            }
        }

        noticeService.galleryRegist(gallery); //gallery insert

        rttr.addFlashAttribute("message", "갤러리 등록이 완료되었습니다.");

        return "redirect:/notice/gallery/list";
        }

        /* 갤러리 리스트 조회 */
        @GetMapping("/gallery/list")
        public ModelAndView gallryList(HttpServletRequest request, ModelAndView mv){

            String currentPage = request.getParameter("currentPage");
            int pageNo = 1;

            if(currentPage != null && !"".equals(currentPage)){
                pageNo = Integer.parseInt(currentPage);
            }

            if(pageNo <= 0){
                pageNo = 1;
            }

            String searchCondition = null;
            String searchValue = request.getParameter("searchValue");

            if(searchValue != null){
                searchCondition = "title";
            }

            Map<String, String> searchMap = new HashMap<>();
            searchMap.put("searchCondition", searchCondition);
            searchMap.put("searchValue", searchValue);

            int totalCount = noticeService.selectGalleryTotalCount(searchMap);

            int limit = 10;
            int buttonAmount = 5;

            SelectCriteria selectCriteria = null;

            if(searchCondition != null && !"".equals(searchCondition)){

                selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount,
                        searchCondition, searchValue);
            } else {

                selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
            }
            log.info("selectCriteria 확인 : " + selectCriteria);

            List<GalleryDTO> galleryList = noticeService.selectGalleryList(selectCriteria);

            mv.addObject("galleryList", galleryList);
            mv.addObject("selectCriteria", selectCriteria);

            mv.setViewName("notice/gallery/galleryList");

            return mv;
        }

        /* 갤러리 상세 조회 */
        @GetMapping("/gallery/detail")
        public String selectGalleryDetail(HttpServletRequest request, Model model){

            int no = Integer.parseInt(request.getParameter("no"));

            GalleryDTO galleryDetail = noticeService.selectGalleryDetail(no);
 
            
            
            List<GalleryFileDTO> galleryFileDetail = noticeService.selectGalleryFile(no);

            log.info("galleryDetail값 확인 : " + galleryDetail);

            model.addAttribute("gallery", galleryDetail);
            model.addAttribute("galleryFileDetail", galleryFileDetail);

            return "notice/gallery/galleryDetail";
        }

        /* 갤러리 게시글 수정 */
        @GetMapping("/gallery/update")
        public String galleryUpdatePage(HttpServletRequest request, Model model){

            int no = Integer.parseInt(request.getParameter("no"));

            GalleryDTO galleryDetail = noticeService.selectGalleryDetail(no);
            List<GalleryFileDTO> galleryFileDetail = noticeService.selectGalleryFile(no);

            log.info("galleryDetail값 확인 : " + galleryDetail);

            model.addAttribute("gallery", galleryDetail);
            model.addAttribute("galleryFileDetail", galleryFileDetail);

            return"notice/gallery/galleryUpdate";
        }

        @PostMapping("/gallery/update")
        public String galleryUpdate(@ModelAttribute GalleryDTO gallery, HttpServletRequest request,
                                    @RequestParam("thumbnailImg1") MultipartFile thumbnailImg1,
                                    @RequestParam("thumbnailImg2") MultipartFile thumbnailImg2,
                                    @RequestParam("thumbnailImg3") MultipartFile thumbnailImg3,
                                    @RequestParam("thumbnailImg4") MultipartFile thumbnailImg4,
                                    @RequestParam("thumbnailImg5") MultipartFile thumbnailImg5,
                                    @RequestParam("thumbnailImg6") MultipartFile thumbnailImg6,
                                    @RequestParam("thumbnailImg7") MultipartFile thumbnailImg7,
                                    @RequestParam("thumbnailImg8") MultipartFile thumbnailImg8,
                                    RedirectAttributes rttr) throws FileNotFoundException {

            String rootLocation = IMAGE_DIR;

            String filePath = ResourceUtils.getURL("src/main/resources").getPath() + "upload";

            String fileUploadDirectory = filePath + "/galleryFile";
            String thumbnailDirectory = filePath + "/thumbnailFile";

            File directory = new File(fileUploadDirectory);
            File directory2 = new File(thumbnailDirectory);

            if(!directory.exists() || !directory2.exists()){

                directory.mkdirs();
                directory2.mkdirs();
            }

            List<Map<String, String>> fileList = new ArrayList<>();

            List<MultipartFile> paramFileList = new ArrayList<>();
            paramFileList.add(thumbnailImg1);
            paramFileList.add(thumbnailImg2);
            paramFileList.add(thumbnailImg3);
            paramFileList.add(thumbnailImg4);
            paramFileList.add(thumbnailImg5);
            paramFileList.add(thumbnailImg6);
            paramFileList.add(thumbnailImg7);
            paramFileList.add(thumbnailImg8);

            for(MultipartFile paramFile : paramFileList) {

                if (paramFile.getSize() > 0) {
                    String originFileName = paramFile.getOriginalFilename();

                    String ext = originFileName.substring(originFileName.lastIndexOf("."));
                    String savedFileName = UUID.randomUUID().toString().replace("-", "") + ext;

                    try {
                        paramFile.transferTo(new File(fileUploadDirectory + "/" + savedFileName));

                        Map<String, String> fileMap = new HashMap<>();
                        fileMap.put("originFileName", originFileName);
                        fileMap.put("savedFileName", savedFileName);
                        fileMap.put("savePath", fileUploadDirectory);

                        String filedName = paramFile.getName();

                        int width = 1200;
                        int height = 900;

                        if("thumbnailImg1".equals(filedName)){
                            fileMap.put("fileType", "TITLE");
                        } else {
                            fileMap.put("fileType", "BODY");
                        }


                        Thumbnails.of(fileUploadDirectory + "/" + savedFileName).size(width, height)
                                .toFile(thumbnailDirectory + "/thumb_" + savedFileName);

                        fileMap.put("thumbnailPath", "/upload/thumbnailFile/thumb_" + savedFileName);

                        fileList.add(fileMap);

                        // galleryDTO에 값 담기

                        gallery.setGalleryFile(new ArrayList<GalleryFileDTO>());
                        List<GalleryFileDTO> list = gallery.getGalleryFile();
                        for (int i = 0; i < fileList.size(); i++) {
                            Map<String, String> file = fileList.get(i);

                            GalleryFileDTO tempFileInfo = new GalleryFileDTO();
                            tempFileInfo.setOriginName(file.get("originFileName"));
                            tempFileInfo.setSaveName(file.get("savedFileName"));
                            tempFileInfo.setSavePath(file.get("savePath"));
                            tempFileInfo.setThumbnailPath(file.get("thumbnailPath"));
                            tempFileInfo.setFileType(file.get("fileType"));

                            list.add(tempFileInfo);
                        }

                    } catch (IllegalStateException | IOException e) {
                        e.printStackTrace();
                        //Exception 발생 시 파일 삭제
                        int cnt = 0;
                        for (int i = 0; i < fileList.size(); i++) {
                            Map<String, String> file = fileList.get(i);

                            File deleteFile = new File(fileUploadDirectory + "/" + file.get("savedFileName"));
                            boolean isDeleted1 = deleteFile.delete();

                            File deleteThumbnail = new File(thumbnailDirectory + "/thumb_" + file.get("savedFileName"));
                            boolean isDeleted2 = deleteThumbnail.delete();

                            if (isDeleted1 && isDeleted2) {
                                cnt++;
                            }
                        }

                        if (cnt == fileList.size()) {
                            log.info("업로드 실패한 사진 삭제 완료");
                            e.printStackTrace();
                        } else {
                            e.printStackTrace();
                        }
                    }

                }
            }

            noticeService.galleryUpdate(gallery);

            rttr.addFlashAttribute("message", "갤러리 수정이 완료되었습니다.");

            return "redirect:/notice/gallery/list";
        }

        @GetMapping("/gallery/delete")
        public String galleryDelete(@ModelAttribute GalleryDTO gallery, HttpServletRequest request){

            int no = Integer.parseInt(request.getParameter("no"));

            noticeService.galleryDelete(gallery);

            return "redirect:/notice/gallery/list";

        }

    }

