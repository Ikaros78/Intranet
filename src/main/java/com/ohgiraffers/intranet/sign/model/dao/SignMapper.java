package com.ohgiraffers.intranet.sign.model.dao;

import com.ohgiraffers.intranet.common.paging.SelectCriteria;
import com.ohgiraffers.intranet.sign.model.dto.SignDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SignMapper {
    int selectTotalWaitingCount(Map<String, String> searchMap);

    List<SignDTO> selectWaitingList(SelectCriteria selectCriteria);
}
