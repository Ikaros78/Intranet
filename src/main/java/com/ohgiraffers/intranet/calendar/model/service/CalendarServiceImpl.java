package com.ohgiraffers.intranet.calendar.model.service;
import com.ohgiraffers.intranet.calendar.model.dao.CalendarMapper;
import com.ohgiraffers.intranet.calendar.model.dto.CalendarDTO;
import com.ohgiraffers.intranet.member.model.dao.MemberMapper;
import com.ohgiraffers.intranet.member.model.dto.DepartmentDTO;
import com.ohgiraffers.intranet.member.model.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalendarServiceImpl implements CalendarService{

    private CalendarMapper calendarMapper;
    private final MemberMapper memberMapper;

    @Autowired
    public CalendarServiceImpl(CalendarMapper calendarMapper, MemberMapper memberMapper) {

        this.memberMapper = memberMapper;
        this.calendarMapper = calendarMapper;
    }

//    /* DB 캘린더 전체 조회 */
//    public List<CalendarDTO> selectAllCalendar() {
//
//        return calendarMapper.selectAllCalendar();
//    }

    /* 캘린더 전체 조회용 메소드 */
    @Override
    public List<CalendarDTO> findAllCal(String type) {

        return calendarMapper.findAllCal(type);
    }
    @Override
    /* 캘린더 ajax 전체 조회용 메소드 */
    public List<CalendarDTO> findAllSc() {

        return calendarMapper.findAllSc();
    }

    /* 캘린더 일정추가 버튼에서 insert  */
    @Override
    public int insertList(CalendarDTO calendar) {

        int result = calendarMapper.insertList(calendar);

        if(result > 0) {

            System.out.println("성공");
        }

        return result;
    }

    /* 캘린더 상세조회 */
    @Override
    public CalendarDTO selectCdDetail(String id) {

        CalendarDTO cdDetail = calendarMapper.selectCdDetail(id);

        return cdDetail;
    }
    /* 캘린더 수정용 메소드 */
    @Override
    public int updateList(CalendarDTO calendar) {

        int result = calendarMapper.updateList(calendar);

        if(result > 0 ) {

            System.out.println("성공");
        }

        return result;
    }

    /* 캘린더 삭제용 메소드 */
    @Override
    public void cdDelete(String id) {

        int result = calendarMapper.cdDelete(id);

    }

    /* 일정권한관리를 위해 memberList를 불러오기 위한 메소드*/
    @Override
    public List<MemberDTO> selectMemberListForCalendarManage(String searchCondition) {

        List<MemberDTO> memberList = memberMapper.selectMemberListForCalendarManage(searchCondition);

        return  memberList;

    }

    /* 일정권한관리를 위해 departmentList를 불러오기 위한 메소드 */
    @Override
    public List<DepartmentDTO> selectDeptList() {

        List<DepartmentDTO> deptList = memberMapper.selectDeptList();

        return deptList;
    }










    /* 서비스 확인용 */
/* 서비스 확인용 */
}
