package com.ohgiraffers.intranet.board.controller;


import com.ohgiraffers.intranet.board.model.dto.FreeinsertDTO;
import com.ohgiraffers.intranet.board.model.service.BoardService;
import com.ohgiraffers.intranet.common.paging.Pagenation;
import com.ohgiraffers.intranet.common.paging.SelectCriteria;
import com.ohgiraffers.intranet.notice.model.dto.NoticeDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board/*")
public class BoardController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final BoardService boardService;

    public BoardController(BoardService boardService) {

        this.boardService = boardService;
    }

    @GetMapping("/insert")
    public String freeinsertPage() {

        return "freelist/freeboardinsert";
    }

    @PostMapping("/insert")
    public String freelist(@ModelAttribute FreeinsertDTO freeinsert, HttpServletRequest request) {

        freeinsert.getTitle();
        freeinsert.getContents();
        log.info("free insert : " + freeinsert);

        int registResult = boardService.freeinsert(freeinsert);
        return "";

    }
    @GetMapping("/list")
    public ModelAndView boardlist(HttpServletRequest request, ModelAndView mv){

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

        int totalCount = boardService.selectTotalCount(searchMap);

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

        List<FreeinsertDTO> boardList = boardService.selectBoardList(selectCriteria);
        log.info("boardlist 확인 : " + boardList);

        mv.addObject("boardList", boardList);
        mv.addObject("selectCriteria", selectCriteria);
        log.info("selectCriteria 확인 : " + selectCriteria);

        log.info("dept값 가져오나 확인 : " + boardList);

      mv.setViewName("freelist/freelist");

        return mv;
    }

    @GetMapping("/detail")
    public String boardDetailPage(HttpServletRequest request, Model model){


        int no = Integer.parseInt(request.getParameter("no"));
        log.info("no 값 : " + request.getParameter("no"));

        FreeinsertDTO boardDetail = boardService.selectBoardDetail(no);
        model.addAttribute("notice", boardDetail);

        return "/freelist/boardDetail";
    }


}


