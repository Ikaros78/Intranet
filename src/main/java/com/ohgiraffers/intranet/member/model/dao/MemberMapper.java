package com.ohgiraffers.intranet.member.model.dao;

import com.ohgiraffers.intranet.member.model.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    int memberRegistInsert(MemberDTO member);

//    int updateMember(MemberDTO member); //마이페이지 정보 수정 추후 작업 예정 09-02

    /* 시큐리티 세션에서 Id를 확인 */
    MemberDTO findByMemberId(String mem_id);

    /* 아이디 중복 체크 */
    String checkMemberById(String mem_id);
}
