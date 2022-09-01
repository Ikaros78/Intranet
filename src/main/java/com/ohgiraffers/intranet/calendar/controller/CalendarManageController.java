package com.ohgiraffers.intranet.calendar.controller;

import com.ohgiraffers.intranet.calendar.model.service.CalendarService;
import com.ohgiraffers.intranet.common.paging.SelectCriteria;
import com.ohgiraffers.intranet.member.model.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/calendarManage/*")
public class CalendarManageController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final CalendarService calendarService;

    @Autowired
    public CalendarManageController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    @GetMapping("/list")
    public ModelAndView CalendarManageList(ModelAndView mv ,HttpServletRequest request){

        String searchCondition = request.getParameter("searchCondition");
        String searchValue = request.getParameter("");

        SelectCriteria selectCriteria = null;

        List<MemberDTO> memberList = calendarService.selectMemberListForCalendarManage(selectCriteria);

        log.info("[CalendarManageController] memberList : " + memberList);

        mv.addObject("memberList", memberList);
        mv.addObject("selectCriteria", selectCriteria);
        log.info("[CalendarManageController] selectCriteria : " + selectCriteria);
        mv.setViewName("calendar/cd_calendarManage");

        return mv;
    }
}
