package com.ohgiraffers.intranet.calendar.model.service;

public class CalendarService {

    private CalendarMapper calendarMapper;

    @Autowired
    public CalendarService(CalendarMapper calendarMapper) {

        this.calendarMapper = calendarMapper;
    }

//    /* DB 캘린더 전체 조회 */
//    public List<CalendarDTO> selectAllCalendar() {
//
//        return calendarMapper.selectAllCalendar();
//    }

    public List<CalendarDTO> findAllCal() {

        return calendarMapper.findAllCal();
    }

    public List<CalendarDTO> findAllSc() {

        return calendarMapper.findAllSc();
    }

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
