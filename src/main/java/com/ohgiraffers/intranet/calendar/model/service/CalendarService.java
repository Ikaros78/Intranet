package com.ohgiraffers.intranet.calendar.model.service;

import com.ohgiraffers.intranet.calendar.model.dto.CalendarDTO;
import com.ohgiraffers.intranet.member.model.dto.DepartmentDTO;
import com.ohgiraffers.intranet.member.model.dto.MemberDTO;

import java.util.List;

public interface CalendarService {

    List<CalendarDTO> findAllCal(String type);

    List<CalendarDTO> findAllSc();

    int insertList(CalendarDTO calendar);

    CalendarDTO selectCdDetail(String id);

    int updateList(CalendarDTO calendar);

    void cdDelete(String id);

    List<MemberDTO> selectMemberListForCalendarManage(String searchCondition);

    List<DepartmentDTO> selectDeptList();
}
