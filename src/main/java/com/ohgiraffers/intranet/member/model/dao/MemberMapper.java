package com.ohgiraffers.intranet.member.model.dao;

import com.ohgiraffers.intranet.member.model.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    int memberRegistInsert(MemberDTO member);

//    int updateMember(MemberDTO member); //회원 정보 수정 추후 업데이트 예정 09-01

    MemberDTO findByMemberId(String mem_id);

}
