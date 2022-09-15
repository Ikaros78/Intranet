package com.ohgiraffers.intranet.calendar.model.service;


import com.ohgiraffers.intranet.authorManage.model.dto.AuthoritDTO;
import com.ohgiraffers.intranet.calendar.model.dto.CalendarDTO;
import com.ohgiraffers.intranet.member.model.dto.DepartmentDTO;
import com.ohgiraffers.intranet.member.model.dto.MemberDTO;

import java.util.List;
import java.util.Map;

public interface CalendarService {

    List<CalendarDTO> findAllCal(Map<String, String> maps);

    List<CalendarDTO> findAllSc();

    int insertList(CalendarDTO calendar);

    CalendarDTO selectCdDetail(String id);

    int updateList(CalendarDTO calendar);

    void cdDelete(String id);

    /*내꺼===========================================================================================*/
    List<MemberDTO> selectMemberListForCalendarAndBoardManage(String searchCondition);

    List<DepartmentDTO> selectDeptList();

    int deleteCalendarAuthority(int memNum);

    int insertAuthority(List<AuthoritDTO> authList);



}
