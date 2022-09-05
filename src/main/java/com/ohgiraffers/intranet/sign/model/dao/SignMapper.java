package com.ohgiraffers.intranet.sign.model.dao;

import com.ohgiraffers.intranet.sign.model.dto.SignDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SignMapper {

    int selectTotalWaitingCount(Map<String, Object> searchMap);

    List<SignDTO> selectWaitingList(Map<String, Object> selectCriteria);

    SignDTO selectSignDetail(String signNo);

    int updateSignChecked(Map<String, Object> signMap);
}
