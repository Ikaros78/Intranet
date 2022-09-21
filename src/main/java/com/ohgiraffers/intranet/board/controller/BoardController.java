package com.ohgiraffers.intranet.board.controller;


import com.ohgiraffers.intranet.board.model.dto.*;
import com.ohgiraffers.intranet.board.model.service.BoardService;
import com.ohgiraffers.intranet.common.paging.Pagenation;
import com.ohgiraffers.intranet.common.paging.SelectCriteria;
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

    @GetMapping("/ajax")
    public ModelAndView cajax(ModelAndView mv, HttpServletRequest request) {
        AjaxDTO cajax = new AjaxDTO();
        String atr1 = request.getParameter("ATTRIBUTE1") == null ? "" : request.getParameter("ATTRIBUTE1");
        String atr2 = request.getParameter("ATTRIBUTE2") == null ? "" : request.getParameter("ATTRIBUTE2");
        String atr3 = request.getParameter("ATTRIBUTE3") == null ? "" : request.getParameter("ATTRIBUTE3");
        String atr4 = request.getParameter("ATTRIBUTE4") == null ? "" : request.getParameter("ATTRIBUTE4");
        String atr5 = request.getParameter("ATTRIBUTE5") == null ? "" : request.getParameter("ATTRIBUTE5");
        String atr6 = request.getParameter("ATTRIBUTE6") == null ? "" : request.getParameter("ATTRIBUTE6");
        String a_act = request.getParameter("ACTION") == null ? "" : request.getParameter("ACTION");
        String a_call = request.getParameter("CALLBACK") == null ? "" : request.getParameter("CALLBACK");

        cajax.setATTRIBUTE1(atr1);
        cajax.setATTRIBUTE2(atr2);
        cajax.setATTRIBUTE3(atr3);
        cajax.setATTRIBUTE4(atr4);
        cajax.setATTRIBUTE5(atr5);
        cajax.setATTRIBUTE6(atr6);
        cajax.setA_ACTION(a_act);
        cajax.setA_CALLBACK(a_call);


        log.info("atr1값 : " + cajax.getATTRIBUTE1());
        log.info("atr2값 : " + cajax.getATTRIBUTE2());
        log.info("atr3값 : " + cajax.getATTRIBUTE3());
        log.info("atr4값 : " + cajax.getATTRIBUTE4());
        log.info("atr5값 : " + cajax.getATTRIBUTE5());
        log.info("atr6값 : " + cajax.getATTRIBUTE6());

        int registResult = boardService.cajax(cajax);

        List<SelAjaxDTO> selAjax = null;

        mv.addObject("selAjax", selAjax);
        mv.setViewName("ajax/json");

        return mv;

    }

    @GetMapping("/selajax")
    public ModelAndView selajax(ModelAndView mv, HttpServletRequest request) {
        AjaxDTO cajax = new AjaxDTO();
        String atr1 = request.getParameter("ATTRIBUTE1") == null ? "" : request.getParameter("ATTRIBUTE1");
        String atr2 = request.getParameter("ATTRIBUTE2") == null ? "" : request.getParameter("ATTRIBUTE2");
        String atr3 = request.getParameter("ATTRIBUTE3") == null ? "" : request.getParameter("ATTRIBUTE3");
        String atr4 = request.getParameter("ATTRIBUTE4") == null ? "" : request.getParameter("ATTRIBUTE4");
        String atr5 = request.getParameter("ATTRIBUTE5") == null ? "" : request.getParameter("ATTRIBUTE5");
        String atr6 = request.getParameter("ATTRIBUTE6") == null ? "" : request.getParameter("ATTRIBUTE6");
        String a_act = request.getParameter("ACTION") == null ? "" : request.getParameter("ACTION");
        String a_call = request.getParameter("CALLBACK") == null ? "" : request.getParameter("CALLBACK");

        cajax.setATTRIBUTE1(atr1);
        cajax.setATTRIBUTE2(atr2);
        cajax.setATTRIBUTE3(atr3);
        cajax.setATTRIBUTE4(atr4);
        cajax.setATTRIBUTE5(atr5);
        cajax.setATTRIBUTE6(atr6);
        cajax.setA_ACTION(a_act);
        cajax.setA_CALLBACK(a_call);


        log.info("atr1값 : " + cajax.getATTRIBUTE1());
        log.info("atr2값 : " + cajax.getATTRIBUTE2());
        log.info("atr3값 : " + cajax.getATTRIBUTE3());
        log.info("atr4값 : " + cajax.getATTRIBUTE4());
        log.info("atr5값 : " + cajax.getATTRIBUTE5());
        log.info("atr6값 : " + cajax.getATTRIBUTE6());

        List<SelAjaxDTO> selAjax = boardService.selectAjax(cajax);

        mv.addObject("selAjax", selAjax);
        mv.setViewName("ajax/json");

        return mv;
    }

    @GetMapping("/bldetail")
    public ModelAndView blacklistdetail(ModelAndView mv, HttpServletRequest request) {
        String EmpNo = request.getParameter("empno");
        mv.addObject("EmpNo",EmpNo);
        mv.setViewName("freelist/blacklistdetail");

        return mv;
    }

    @GetMapping("/blmgr")
    public ModelAndView blacklistmanager(ModelAndView mv, HttpServletRequest request) {

        mv.setViewName("freelist/blacklistmgr");

        return mv;
    }

    @GetMapping("/bluser")
    public ModelAndView blacklistuser(ModelAndView mv, HttpServletRequest request) {

        mv.setViewName("freelist/blacklistuser");

        return mv;
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
        mv.addObject( "searchValue", searchValue);
        log.info("selectCriteria 확인 : " + selectCriteria);

        log.info("dept값 가져오나 확인 : " + boardList);

        mv.setViewName("freelist/freelist");

        return mv;
    }

    @GetMapping("/detail")
    public ModelAndView boardDetailPage(HttpServletRequest request, ModelAndView mv) {


        String no = request.getParameter("no");
        log.info("no 값 : " + no);

        FreeinsertDTO boardDetail = boardService.selectBoardDetail(no);
        mv.addObject("board", boardDetail);

        String fr_no = request.getParameter("no");
        log.info("fr_no값 불러오나 확인 : " + fr_no);

        List<CommentDTO> commentList = boardService.selectComentList(fr_no);
        log.info("List값 확인 : " + commentList);
        mv.addObject("no",no);
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
    public String boardDelete(@RequestParam String no, HttpServletRequest request) {

       String num =  request.getParameter("no");

        System.out.println("nononono" + no);

        System.out.println("nononono" + num);

        boardService.boardDelete(no);

        return "redirect:/board/list";
    }

//    @GetMapping("ei/delete")
//
//    public String eiboardDelete(@RequestParam String no, HttpServletRequest request) {
//
//
//        boardService.eiboardDelete(no);
//
//        return "redirect:/board/eiboard/list";
//    }



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
        mv.addObject( "searchValue", searchValue);

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
    public ModelAndView eiboardDetailPage(HttpServletRequest request, ModelAndView mv) {


        String no = request.getParameter("no");
        log.info("no 값 : " + no);

        AnonyDTO eiboardDetail = boardService.selecteiBoardDetail(no);
        mv.addObject("eiboard", eiboardDetail);


        String nb_no = request.getParameter("no");

        List<EiCommentDTO> eicommentList = boardService.selecteiComentList(nb_no);

        log.info("eicommentList 확인 : " + eicommentList);


        mv.addObject("eicommentList", eicommentList);
        mv.setViewName("freelist/anonyDetail");


        return mv;


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

    public String eiboardDelete(@RequestParam String no, HttpServletRequest request) {


        boardService.eiboardDelete(no);

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
//      익명 댓글 리스트 조회
    @PostMapping("/ei/comment")
    public String boardeicomment(@ModelAttribute EiCommentDTO boardeicomment, HttpServletRequest request) {

        String nb_no = request.getParameter("nb_no");
        boardeicomment.setNb_no(nb_no);
        log.info("nb_no값 : " + boardeicomment.getNb_no());

        int registResult = boardService.boardeicomment(boardeicomment);


        return "redirect:/board/ei/detail?no="+nb_no;

    }
//      익명 댓글
    @GetMapping("ei/comment")
    public ModelAndView selecteicomment(HttpServletRequest request, ModelAndView mv) {

        String nb_no = request.getParameter("nb_no");

        List<EiCommentDTO> eicommentList = boardService.selecteiComentList(nb_no);

        log.info("eicommentList 확인 : " + eicommentList);


        mv.addObject("eicommentList", eicommentList);
        mv.setViewName("freelist/anonyDetail");


        return mv;
    }



}


