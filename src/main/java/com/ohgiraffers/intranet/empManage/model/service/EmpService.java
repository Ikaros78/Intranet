package com.ohgiraffers.intranet.empManage.model.service;

import com.ohgiraffers.intranet.common.paging.SelectCriteria;
import com.ohgiraffers.intranet.member.model.dto.MemberDTO;

import java.util.List;
import java.util.Map;

public interface EmpService {

    /* 직원 수 조회*/
    int selectEmpTotalCount(Map<String, String> searchMap);

    /* 직원 리스트 조회 */
    List<MemberDTO> selectEmpList(SelectCriteria selectCriteria);

    /* 인사 발령 등록 시 이름 가져오는 메소드 */
    MemberDTO getMemberName(int mem_num);
}
