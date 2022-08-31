package com.ohgiraffers.intranet.member.model.dao;

import com.ohgiraffers.intranet.member.model.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    int memberRegistInsert(MemberDTO member);

    int updateMember(MemberDTO member);

    MemberDTO findByMemberId(String mem_id);

}
