package com.ohgiraffers.intranet.board.controller;


import com.ohgiraffers.intranet.board.model.dto.AnonyDTO;
import com.ohgiraffers.intranet.board.model.dto.CommentDTO;
import com.ohgiraffers.intranet.board.model.dto.FreeinsertDTO;
import com.ohgiraffers.intranet.board.model.service.BoardService;
import com.ohgiraffers.intranet.common.paging.Pagenation;
import com.ohgiraffers.intranet.common.paging.SelectCriteria;
import com.ohgiraffers.intranet.notice.model.dto.NewsDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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

        //log.info("free insert : " + freeinsert.getTitle());
        //log.info("free insert : " +  freeinsert.getContents());
        //log.info("free insert : " +  freeinsert.getMem_num());
        log.info("freeinsert : " + freeinsert);

        int registResult = boardService.freeinsert(freeinsert);

        log.info("registResult : " + registResult);

        return "redirect:/board/list";

    }
//    댓글

    @PostMapping("/comment")
    public String boardcomment(@ModelAttribute CommentDTO boardcomment, HttpServletRequest request) {

        String fr_no = request.getParameter("fr_no");
        boardcomment.setFr_no(fr_no);
        log.info("fr_no값 : " + boardcomment.getFr_no());

        int registResult = boardService.boardcomment(boardcomment);


        return "redirect:/board/detail?no="+fr_no;

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
    public ModelAndView boardDetailPage(HttpServletRequest request, ModelAndView mv) {


        String no = request.getParameter("no");
        log.info("no 값 : " + no);

        /* 게시판 상세보기 */
        FreeinsertDTO boardDetail = boardService.selectBoardDetail(no);
        mv.addObject("board", boardDetail);

        String fr_no = request.getParameter("no");
        log.info("fr_no값 불러오나 확인 : " + fr_no);

        /* 게시판 댓글 불러오기 */
        List<CommentDTO> commentList = boardService.selectComentList(fr_no);
        log.info("List값 확인 : " + commentList);

        mv.addObject("commentList", commentList);
        mv.setViewName("freelist/boardDetail");

        return mv;

    }

    @GetMapping("/update")
    public String boardUpdatePage(HttpServletRequest request, Model model) {

        String no = (request.getParameter("no"));

        FreeinsertDTO board = boardService.selectboardDetail(no);
        model.addAttribute("board", board);



        return "freelist/boardcorrection";
    }



    @PostMapping("/update")

    public String boardUpdatePag(@ModelAttribute FreeinsertDTO freeinsert, HttpServletRequest request) {

        //log.info("free insert : " + freeinsert.getTitle());
        //log.info("free insert : " +  freeinsert.getContents());
        //log.info("free insert : " +  freeinsert.getMem_num());
        //log.info("freeinsert : " + freeinsert);

        int registResult = boardService.boardUpdate(freeinsert);

        log.info("registResult : " + registResult);

        return "board/boardcorrection";


    }

    @GetMapping("/delete")

    public String boardDelete(@ModelAttribute FreeinsertDTO freeinsert, HttpServletRequest request) {

        String no = request.getParameter("no");

        boardService.boardDelete(freeinsert);

        return "redirect:/board/list";
    }


//    익명

    @GetMapping("/eiboard/list")

    public ModelAndView eiboardlist(HttpServletRequest request, ModelAndView mv) {

        String currentPage = request.getParameter("currentPage");
        int pageNo = 1;

        if (currentPage != null && !"".equals(currentPage)) {
            pageNo = Integer.parseInt(currentPage);
        }

        String searchCondition = request.getParameter("searchCondition");
        String searchValue = request.getParameter("searchValue");

        Map<String, String> searchMap = new HashMap<>();
        searchMap.put("searchCondition", searchCondition);
        searchMap.put("searchValue", searchValue);
        log.info("검색조건 확인 : " + searchMap);

        int totalCount = boardService.selectTotalCounte(searchMap);

        int limit = 10;
        int buttonAmount = 5;

        SelectCriteria selectCriteria = null;

        if (searchCondition != null && !"".equals(searchCondition)) {

            selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount,
                    searchCondition, searchValue);
            {

            }
        } else {

            selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
        }
        log.info("selectCriteria 확인 : " + selectCriteria);

        List<AnonyDTO> eiboardList = boardService.selectBoardListe(selectCriteria);
        log.info("boardlist 확인 : " + eiboardList);

        mv.addObject("eiboardlist", eiboardList);
        mv.addObject("selectCriteria", selectCriteria);
        log.info("selectCriteria 확인 : " + selectCriteria);

        log.info("dept값 가져오나 확인 : " + eiboardList);

        mv.setViewName("freelist/anonymous");

        return mv;
    }

    @GetMapping("/ei/insert")
    public String eiboardinsertPage() {

        return "freelist/anonyinsert";
    }

    @PostMapping("/ei/insert")
    public String eiboardinsertPage(@ModelAttribute AnonyDTO anonyinsert, HttpServletRequest request) {

        log.info("anonyinsert : " + anonyinsert);

        int registResult = boardService.anonyinsert(anonyinsert);

        log.info("registResult : " + registResult);

        return "redirect:/board/eiboard/list";

    }

    @GetMapping("ei/detail")
    public String eiboardDetailPage(HttpServletRequest request, Model model) {


        String no = request.getParameter("no");
        log.info("no 값 : " + no);

        AnonyDTO eiboardDetail = boardService.selecteiBoardDetail(no);
        model.addAttribute("eiboard", eiboardDetail);

        return "freelist/anonyDetail";
    }

    @GetMapping("ei/update")
    public String boardeiUpdatePage(HttpServletRequest request, Model model) {

        String no = (request.getParameter("no"));

        AnonyDTO eiboard = boardService.selectboardeiDetail(no);
        model.addAttribute("eiboard", eiboard);



        return "freelist/anonycorrection";
    }

    @PostMapping("ei/update")

    public String boardeiUpdatePag(@ModelAttribute AnonyDTO anonyinsert, HttpServletRequest request) {



        int registResult = boardService.eiboardUpdate(anonyinsert);

        log.info("registResult : " + registResult);

        return "redirect:/board/eiboard/list";


    }

    @GetMapping("ei/delete")

    public String eiboardDelete(@ModelAttribute AnonyDTO anonyinsert, HttpServletRequest request) {

        String no = request.getParameter("no");

        boardService.eiboardDelete(anonyinsert);

        return "redirect:/board/eiboard/list";
    }

        @GetMapping("comment")
        public ModelAndView selectcomment(HttpServletRequest request, ModelAndView mv) {

            String fr_no = request.getParameter("fr_no");

            List<CommentDTO> commentList = boardService.selectComentList(fr_no);

            log.info("commentList 확인 : " + commentList);


            mv.addObject("commentList", commentList);
            mv.setViewName("freelist/boardDetail");


            return mv;
        }


}


