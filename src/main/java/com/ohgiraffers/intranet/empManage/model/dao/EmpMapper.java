package com.ohgiraffers.intranet.empManage.model.dao;

import com.ohgiraffers.intranet.common.paging.SelectCriteria;
import com.ohgiraffers.intranet.member.model.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {
    int selectEmpTotalCount(Map<String, String> searchMap);

    List<MemberDTO> selectEmpList(SelectCriteria selectCriteria);

    MemberDTO getMemberName(int mem_num);
}
