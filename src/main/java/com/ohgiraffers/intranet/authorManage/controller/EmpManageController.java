package com.ohgiraffers.intranet.authorManage.controller;

import com.ohgiraffers.intranet.authorManage.model.service.AuthorService;
import com.ohgiraffers.intranet.calendar.model.service.CalendarService;
import com.ohgiraffers.intranet.member.model.dto.MemberDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/empManage/*")
public class EmpManageController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final AuthorService authorService;

    @Autowired
    public EmpManageController(AuthorService authorService){

        this.authorService = authorService;

    }


    @GetMapping("/list")
    public ModelAndView EmpManageController(ModelAndView mv){

        List<MemberDTO> memberList = authorService.selectMemberListForEmpManage();

        mv.addObject("memberList", memberList);
        mv.setViewName("/emplManage/empManage");

        return mv;
    }

}
