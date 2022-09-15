package com.ohgiraffers.intranet.calendar.controller;

import com.ohgiraffers.intranet.authorManage.model.dto.AuthoritDTO;
import com.ohgiraffers.intranet.calendar.model.service.CalendarServiceImpl;
import com.ohgiraffers.intranet.member.model.dto.DepartmentDTO;
import com.ohgiraffers.intranet.member.model.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/calendarManage/*")
public class CalendarManageController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final CalendarServiceImpl calendarService;

    @Autowired
    public CalendarManageController(CalendarServiceImpl calendarService) {
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

    @PostMapping(value = "/updateList", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String updateCalendarAuthority(@RequestParam int memNum, @RequestParam boolean cd_all, @RequestParam boolean cd_dept){

        System.out.println("memNum + cd_all + cd_dept = " + memNum + cd_all + cd_dept);

        int result = calendarService.deleteCalendarAuthority(memNum);

        List<AuthoritDTO> authList = new ArrayList<>();
        if(cd_all){
            AuthoritDTO auth = new AuthoritDTO();
            auth.setMemNum(memNum);
            auth.setAuCode("ROLE_CD_ALL");
            authList.add(auth);         
        }
        
        if(cd_dept){
            AuthoritDTO auth = new AuthoritDTO();
            auth.setMemNum(memNum);
            auth.setAuCode("ROLE_CD_DEPT");
            authList.add(auth);
        }

        System.out.println("authList = " + authList);

        int result2 = calendarService.insertAuthority(authList);

        String data = "";

        if( result2 > 0 || result > 0){
             data = "success";
        } else{
             data = "fail";
        }

        return data;
    }

    /* userImpl을 통해 로그인 시 세션에 들고온 해당 계정의 정보를 들고 오는 방법입니다. */





}
