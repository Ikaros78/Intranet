package com.ohgiraffers.intranet.calendar.model.service;

import com.ohgiraffers.intranet.authorManage.model.dto.AuthoritDTO;
import com.ohgiraffers.intranet.member.model.dao.MemberMapper;
import com.ohgiraffers.intranet.member.model.dto.MemberDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalendarService {

    private final MemberMapper memberMapper;

    public CalendarService(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    /* 일정권한관리를 위해 memberList를 불러오기 위한 메소드*/
    public List<MemberDTO> selectMemberListForCalendarManage() {

        List<MemberDTO> memberList = memberMapper.selectMemberListForCalendarManage();

        return  memberList;

    }

//    /* 일정권한관리를 위해 memberList의 권한을 불러오기 위한 메소드*/
//    public List<AuthoritDTO> selectMemberAuthoritForCalendarManage() {
//
//        List<AuthoritDTO> memberAuthorit = memberMapper.selectMemberAuthoritForCalendarManage();
//
//        return memberAuthorit;
//    }
    /* 서비스 확인용 */
/* 서비스 확인용 */
}
