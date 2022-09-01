package com.ohgiraffers.intranet.member.model.dao;

import com.ohgiraffers.intranet.common.paging.SelectCriteria;
import com.ohgiraffers.intranet.member.model.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {

    int memberRegistInsert(MemberDTO member);

//    int updateMember(MemberDTO member); //회원 정보 수정 추후 업데이트 예정 09-01

    MemberDTO findByMemberId(String mem_id);

    /* 일정권한 관리를 위한 회원 리스트 조회용 */
    List<MemberDTO> selectMemberListForCalendarManage(SelectCriteria selectCriteria);
}
