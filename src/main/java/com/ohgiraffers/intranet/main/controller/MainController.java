package com.ohgiraffers.intranet.main.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ohgiraffers.intranet.common.paging.Pagenation;
import com.ohgiraffers.intranet.common.paging.SelectCriteria;
import com.ohgiraffers.intranet.msBoard.model.service.MsBoardServiceImpl;
import com.ohgiraffers.intranet.notice.model.dto.NoticeDTO;
import com.ohgiraffers.intranet.notice.model.service.NoticeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/main/*")
public class MainController {

    private final NoticeServiceImpl noticeService;
    private final MsBoardServiceImpl msBoardService;

    @Autowired
    public MainController(NoticeServiceImpl noticeService, MsBoardServiceImpl msBoardService) {

        this.noticeService = noticeService;
        this.msBoardService = msBoardService;
    }


    /* 메인페이지 메인요청 */
    @GetMapping(value = {"/" , "/main"} )
    public ModelAndView main(ModelAndView mv){

        mv.setViewName("main/main");

        return mv;
    }

    /* 메인페이지 공지사항 요청 */
    @GetMapping("/mainNo")
    @ResponseBody
    public String selectNotice(HttpServletRequest request) {

        int pageNo = 1;

        String searchCondition = request.getParameter("searchCondition");
        String searchValue = request.getParameter("searchValue");

        Map<String, String> searchMap = new HashMap<>();
        searchMap.put("searchCondition", searchCondition);
        searchMap.put("searchValue", searchValue);
        System.out.println("확인 : " +searchMap);

        int totalCount = noticeService.selectTotalCount(searchMap);

        int limit = 8;
        int buttonAmount = 0;

        SelectCriteria selectCriteria = null;

        if(searchCondition != null && !"".equals(searchCondition)){

            selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount,
                    searchCondition, searchValue);
        } else {

            selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
        }

        List<NoticeDTO> noticeList = noticeService.selectNoticeList(selectCriteria);
        System.out.println("selectCriteria = " + selectCriteria);

//        noMv.addObject("noticeList", noticeList);
//        noMv.addObject("selectCriteria", selectCriteria);

        System.out.println("selectCriteria = " + selectCriteria);

        System.out.println("noticeList = " + noticeList);

//        noMv.setViewName("main/mainNo");

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();


        return gson.toJson(noticeList);
    }



}
