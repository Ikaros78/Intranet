package com.ohgiraffers.intranet.board.model.service;

import com.ohgiraffers.intranet.board.model.dao.BoardMapper;
import com.ohgiraffers.intranet.board.model.dto.FreeinsertDTO;
import com.ohgiraffers.intranet.common.paging.SelectCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service("freeinsertService")
public class BoardService {

    private final BoardMapper boardMapper;

    public BoardService(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }

    @Transactional
    public int freeinsert(FreeinsertDTO freeinsert) {

        return boardMapper.freeinsert(freeinsert);
    }

    public int selectTotalCount(Map<String, String> searchMap) {

        int result = boardMapper.selectTotalCount(searchMap);

        return result;
    }

    public List<FreeinsertDTO> selectBoardList(SelectCriteria selectCriteria) {

       List<FreeinsertDTO> boardList = boardMapper.selectBoardList(selectCriteria);

       return boardList;
    }
    @Transactional
    public FreeinsertDTO selectBoardDetail(String no) {

        FreeinsertDTO boardDetail = null;

        int result = boardMapper.incresementBoardCount(no); //조회수 증가

        if(result > 0){
            boardDetail = boardMapper.selectBoardDetail(no);
        }
        return boardDetail;
    }

    @Transactional
    public int boardUpdate(FreeinsertDTO board) {
        return boardMapper.boardUpdate(board);
    }


    public FreeinsertDTO selectboardDetail(String no) {

        return boardMapper.selectBoardDetail(no);
    }

    public int singoInsert(int memNo) {

        return boardMapper.singoInsert(memNo);
    }
}
