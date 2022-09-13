package com.ohgiraffers.intranet.calendar.controller;

import com.ohgiraffers.intranet.calendar.model.service.CalendarServiceImpl;
import com.ohgiraffers.intranet.member.model.dto.DepartmentDTO;
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

//    @PostMapping("/updateList")
//    public String UpdateCalendarAuthority(@ModelAttribute AuthoritDTO authorit, HttpServletRequest request){
//
//        String name = request.getParameter("name");
//        String dept = request.getParameter("dept");
//
//        Map<String, String> memInfo = new HashMap<>();
//        memInfo.put("name", name);
//        memInfo.put("dept", dept);
//        memInfo.put("authorit", String.valueOf(authorit)); // 이게 맞나.. 흐으으음
//
//
//        calendarService.updateAuthority(memInfo);
//
//        return "redirect:/calendar/cd_calendarManage";
//
//    }





}
