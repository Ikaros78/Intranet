package com.ohgiraffers.intranet.authorManage.model.service;

import com.ohgiraffers.intranet.authorManage.model.dto.AuthoritDTO;
import com.ohgiraffers.intranet.common.exception.authority.AuthorityUpdateException;
import com.ohgiraffers.intranet.member.model.dto.MemberDTO;

import java.util.List;

public interface AuthorService {


    int deleteBoardAuthority(int memNum);

    int insertAuthority(List<AuthoritDTO> authList);

    List<MemberDTO> selectMemberListForCalendarAndBoardManage(String searchCondition);

    /*인사,접근 권한 관리를 위해 인사부 직원들 리스트 조회 */
    List<MemberDTO> selectMemberListForEmpAndAllManage();

    int deleteEmpAuthority(int memNum);

    int deleteAllAuthority(int memNum);
}
