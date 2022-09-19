package com.ohgiraffers.intranet.main.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ohgiraffers.intranet.common.paging.Pagenation;
import com.ohgiraffers.intranet.common.paging.SelectCriteria;
import com.ohgiraffers.intranet.member.model.dto.UserImpl;
import com.ohgiraffers.intranet.msBoard.model.dto.MsBoardDTO;
import com.ohgiraffers.intranet.msBoard.model.service.MsBoardServiceImpl;
import com.ohgiraffers.intranet.notice.model.dto.NoticeDTO;
import com.ohgiraffers.intranet.notice.model.service.NoticeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
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

    /* 메인페이지 메시지 요청 */
    @GetMapping("/mainMs")
    @ResponseBody
    public String selectMessage(HttpServletRequest request, @AuthenticationPrincipal User user) {

        int pageNo = 1;

        String searchCondition = request.getParameter("searchCondition");
        String searchValue = request.getParameter("searchValue");
        int memNum = ((UserImpl) user).getMem_num();

        Map<String, Object> searchMap = new HashMap<>();
        searchMap.put("searchCondition", searchCondition);
        searchMap.put("searchValue", searchValue);
        searchMap.put("memNum", memNum);

        int totalCount = msBoardService.selectTotalCount(searchMap);

        /* 한 페이지에 보여 줄 게시물 수 */
        int limit = 8; // 얘도 파라미터로 전달받아도 된다.

        /* 한 번에 보여질 페이징 버튼의 갯수 */
        int buttonAmount = 0;

        /* 페이징 처리를 위한 로직 호출 후 페이징 처리에 관한 정보를 담고 있는 인스턴스를 반환받는다. */
        SelectCriteria selectCriteria = null;

        if (searchCondition != null && !"".equals(searchCondition)) {
            selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, searchCondition,
                    searchValue);
        } else {
            selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
        }

        Map<String, Object> searchList = new HashMap<>();
        searchList.put("selectCriteria", selectCriteria);
        searchList.put("memNum", memNum);

        System.out.println("searchList + " + searchList);

        List<MsBoardDTO> boardList = msBoardService.selectMsRecpBoard(searchList);

//        mv.addObject("boardList", boardList);
//        mv.addObject("selectCriteria", selectCriteria);
//        mv.addObject("searchList", searchList);
//        mv.setViewName("message/messageRecpBox");

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

        System.out.println("boardList = " + boardList);
        System.out.println("searchList = " + searchList);
        System.out.println("gson = " + gson);

        return gson.toJson(boardList);
    }

}
