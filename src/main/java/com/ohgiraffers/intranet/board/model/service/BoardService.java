package com.ohgiraffers.intranet.board.model.service;

import com.ohgiraffers.intranet.board.model.dao.BoardMapper;
import com.ohgiraffers.intranet.board.model.dto.BoardDTO;
import com.ohgiraffers.intranet.board.model.dto.FreeinsertDTO;
import com.ohgiraffers.intranet.common.paging.SelectCriteria;
import com.ohgiraffers.intranet.notice.model.dto.NoticeDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("freeinsertService")
public class BoardService {

    private final BoardMapper boardMapper;

    public BoardService(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }

    public int freeinsert(FreeinsertDTO freeinsert) {

        return boardMapper.boardInsert(freeinsert);
    }

    public int selectTotalCount(Map<String, String> searchMap) {

        int result = boardMapper.selectTotalCount(searchMap);

        return result;
    }

    public List<FreeinsertDTO> selectBoardList(SelectCriteria selectCriteria) {

       List<FreeinsertDTO> boardList = boardMapper.selectBoardList(selectCriteria);

       return boardList;
    }
}
