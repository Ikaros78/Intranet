package com.ohgiraffers.intranet.board.controller;


import com.ohgiraffers.intranet.board.model.dto.FreeinsertDTO;
import com.ohgiraffers.intranet.board.model.service.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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

    @PostMapping("/board/insert")
    public String freelist(@ModelAttribute FreeinsertDTO freeinsert, HttpServletRequest request) {

        freeinsert.getTitle();
        freeinsert.getContents();
        log.info("free insrt : " + freeinsert);

        int registResult = boardService.freeinsert(freeinsert);
        return "";

    }

}


