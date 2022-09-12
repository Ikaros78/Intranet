package com.ohgiraffers.intranet.emplManage.model.service;

import com.ohgiraffers.intranet.common.paging.SelectCriteria;
import com.ohgiraffers.intranet.member.model.dto.MemberDTO;

import java.util.List;
import java.util.Map;

public interface EmplService {
    int selectEmpTotalCount(Map<String, String> searchMap);

    List<MemberDTO> selectEmpList(SelectCriteria selectCriteria);
}
