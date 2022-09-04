package com.ohgiraffers.intranet.notice.controller;

import com.ohgiraffers.intranet.common.paging.Pagenation;
import com.ohgiraffers.intranet.common.paging.SelectCriteria;
import com.ohgiraffers.intranet.member.model.dto.MemberDTO;
import com.ohgiraffers.intranet.notice.model.dto.NewsDTO;
import com.ohgiraffers.intranet.notice.model.dto.NoticeDTO;
import com.ohgiraffers.intranet.notice.model.dto.NoticeFileDTO;
import com.ohgiraffers.intranet.notice.model.service.NoticeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private String IMAGE_DIR;
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final NoticeService noticeService;
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
                    searchCondition, searchValue);{

            }
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

        log.info("notice값 불러오나 확인 " + notice);

        noticeService.noticeUpdate(notice);
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
}
