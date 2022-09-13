package com.ohgiraffers.intranet.authorManage.model.service;

import com.ohgiraffers.intranet.authorManage.model.dto.AuthoritDTO;
import com.ohgiraffers.intranet.member.model.dto.MemberDTO;

import java.util.List;
import java.util.Map;

public interface AuthorService {


//    List<MemberDTO> selectBoardAuthority(String searchValue);

//    int deleteBoardAuthority(int memNum);
//
//    int insertBoardAuthoirty(Map<String, Object> map);

    List<MemberDTO> selectMemberListForBoardManage(String searchCondition);

    int deleteBoardAuthority(int memNum);

    int insertAuthority(List<AuthoritDTO> authList);

    List<MemberDTO> selectMemberListForEmpManage();
}
