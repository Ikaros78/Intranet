package com.ohgiraffers.intranet.sign.model.dao;

import com.ohgiraffers.intranet.sign.model.dto.SignDTO;
import com.ohgiraffers.intranet.sign.model.dto.SignFormDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SignMapper {

    int selectTotalWaitingCount(Map<String, Object> searchMap);

    List<SignDTO> selectWaitingList(Map<String, Object> selectCriteria);

    SignDTO selectSignDetail(String signNo);

    int updateSignChecked(Map<String, Object> signMap);

    List<SignDTO> selectRecentForm(int mem_num);

    int selectTotalFormCount(Map<String, Object> searchMap);

    List<SignFormDTO> selectAllForm(Map<String, Object> searchList);

    SignFormDTO selectFormByCode(String formCode);

    int approveSign(Map<String, Object> approve);

    int refuseSign(Map<String, Object> refuse);
}
