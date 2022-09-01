package com.ohgiraffers.intranet.calendar.model.service;

import com.ohgiraffers.intranet.common.paging.SelectCriteria;
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
    public List<MemberDTO> selectMemberListForCalendarManage(SelectCriteria selectCriteria) {
        List<MemberDTO> memberList = null;

        memberList = memberMapper.selectMemberListForCalendarManage(selectCriteria);

        return  memberList;

    }
    /* 서비스 확인용 */
/* 서비스 확인용 */
}
