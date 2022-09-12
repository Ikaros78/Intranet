package com.ohgiraffers.intranet.emplManage.model.dao;

import com.ohgiraffers.intranet.common.paging.SelectCriteria;
import com.ohgiraffers.intranet.member.model.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface EmplMapper {
    int selectEmpTotalCount(Map<String, String> searchMap);

    List<MemberDTO> selectEmpList(SelectCriteria selectCriteria);
}
