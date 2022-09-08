package com.ohgiraffers.intranet.board.model.dao;

import com.ohgiraffers.intranet.board.model.dto.FreeinsertDTO;
import com.ohgiraffers.intranet.common.paging.SelectCriteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardMapper {


   

    int boardInsert(FreeinsertDTO freeinsert);


    int selectTotalCount(Map<String, String> searchMap);

    List<FreeinsertDTO> selectBoardList(SelectCriteria selectCriteria);

    int incresementBoardCount(int no);

    FreeinsertDTO selectBoardDetail(int no);
}
