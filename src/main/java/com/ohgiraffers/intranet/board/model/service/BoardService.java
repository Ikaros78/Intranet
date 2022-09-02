package com.ohgiraffers.intranet.board.model.service;

import com.ohgiraffers.intranet.board.model.dao.BoardMapper;
import com.ohgiraffers.intranet.board.model.dto.FreeinsertDTO;
import org.springframework.stereotype.Service;

@Service("freeinsertService")
public class BoardService {

    private final BoardMapper boardMapper;

    public BoardService(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }

    public int freeinsert(FreeinsertDTO freeinsert) {

        return boardMapper.boardInsert(freeinsert);
    }
}
