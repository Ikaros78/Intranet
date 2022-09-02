package com.ohgiraffers.intranet.calendar.controller;

import com.ohgiraffers.intranet.authorManage.model.dto.AuthoritDTO;
import com.ohgiraffers.intranet.calendar.model.service.CalendarService;
import com.ohgiraffers.intranet.common.paging.SelectCriteria;
import com.ohgiraffers.intranet.member.model.dto.DepartmentDTO;
import com.ohgiraffers.intranet.member.model.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        List<MemberDTO> memberList = calendarService.selectMemberListForCalendarManage(searchCondition);
        List<DepartmentDTO> deptList = calendarService.selectDeptList();


        log.info("[CalendarManageController] memberList : " + memberList);
        log.info("[CalendarManageController] deptList : " + deptList);

        mv.addObject("memberList", memberList);
        mv.addObject("deptList", deptList);

        mv.setViewName("calendar/cd_calendarManage");

        return mv;
    }





}
