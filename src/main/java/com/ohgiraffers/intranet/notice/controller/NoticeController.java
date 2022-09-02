package com.ohgiraffers.intranet.notice.controller;

import com.ohgiraffers.intranet.common.paging.Pagenation;
import com.ohgiraffers.intranet.common.paging.SelectCriteria;
import com.ohgiraffers.intranet.notice.model.dto.NoticeDTO;
import com.ohgiraffers.intranet.notice.model.dto.NoticeFileDTO;
import com.ohgiraffers.intranet.notice.model.service.NoticeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/notice/*")
public class NoticeController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final NoticeService noticeService;

    public NoticeController(NoticeService noticeService){

        this.noticeService = noticeService;
    }

    @GetMapping("/regist")
    public String noticeRegistPage(Model model){

        List<NoticeDTO> noticeList = noticeService.selectWriterinfo();

        log.info("작성자 값 불러와졌는지 확인 : " + noticeList);

        return "notice/noticeRegist";
    }

    @PostMapping("/notice/regist")
    public String noticeRegist(@ModelAttribute NoticeDTO notice, HttpServletRequest request, @RequestParam(name="originName", required=false) MultipartFile originName) {

        notice.getTitle();
        notice.getContents();

        log.info("notice regist : " + notice);

        //notice insert
        int registResult = noticeService.noticeRegist(notice);


        log.info("registResult : " + registResult);

        NoticeFileDTO noticeFile = new NoticeFileDTO();

        String root = request.getSession().getServletContext().getRealPath("resources");
        log.info("root 확인 : " + root);

        String savePath = root + "//uploadFiles/noticeFile";
        File mkdir = new File(savePath);

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
            noticeFile.setSavePath(savePath);

            log.info("noticeFileDTO값 확인 : " + noticeFile);
            // file insert
            int fileResult = noticeService.noticeFileInsert(noticeFile);

            try {
                originName.transferTo(new File(savePath + "//" + saveName));
            } catch (IOException e) {

                e.printStackTrace();
                new File(savePath + "//" + saveName).delete();
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
}
