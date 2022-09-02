package com.ohgiraffers.intranet.board.model.dao;

import com.ohgiraffers.intranet.board.model.dto.FreeinsertDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardMapper {
    int boardInsert(FreeinsertDTO freeinsert);


}
