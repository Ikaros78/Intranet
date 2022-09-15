package com.ohgiraffers.intranet.authorManage.controller;

import com.ohgiraffers.intranet.authorManage.model.dto.AuthoritDTO;
import com.ohgiraffers.intranet.authorManage.model.dto.AuthoritTypeDTO;
import com.ohgiraffers.intranet.authorManage.model.service.AuthorService;

import com.ohgiraffers.intranet.authorManage.model.service.AuthorServiceImpl;
import com.ohgiraffers.intranet.calendar.model.service.CalendarServiceImpl;
import com.ohgiraffers.intranet.member.model.dto.DepartmentDTO;
import com.ohgiraffers.intranet.member.model.dto.MemberDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/boardManage/*")
public class BoardManageController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final AuthorServiceImpl authorService;
    private final CalendarServiceImpl calendarService;

    @Autowired
    public BoardManageController(AuthorServiceImpl authorService, CalendarServiceImpl calendarService){

        this.authorService = authorService;
        this.calendarService = calendarService;
    }

    @GetMapping("/list")
    public ModelAndView BoardManageList(ModelAndView mv , HttpServletRequest request){

        String searchCondition = request.getParameter("searchCondition");

        List<MemberDTO> memberList = authorService.selectMemberListForCalendarAndBoardManage(searchCondition);
        List<DepartmentDTO> deptList = calendarService.selectDeptList();

        List<AuthoritTypeDTO> authoritTypeList = new ArrayList<>();

        for(int i = 0 ; i < memberList.size(); i ++){

            AuthoritTypeDTO authoritTypeDTO = new AuthoritTypeDTO();

            for(int j = 0 ; j < memberList.get(i).getAuthorit().size(); j ++){

                if(memberList.get(i).getAuthorit().get(j).getAuCode() != null && memberList.get(i).getAuthorit().get(j).getAuCode().equals("ROLE_NT_ALL")){

                    authoritTypeDTO.setNt_all("NT_ALL");
                }
                if(memberList.get(i).getAuthorit().get(j).getAuCode() != null && memberList.get(i).getAuthorit().get(j).getAuCode().equals("ROLE_DB_ALL")){

                    authoritTypeDTO.setDb_all("DB_ALL");
                }
            }
            authoritTypeList.add(authoritTypeDTO);
        }

        log.info("[BoardManageController] memberList : " + memberList);
        log.info("[BoardManageController] deptList : " + deptList);
        log.info("[BoardManageController] authoritTypeList : " + authoritTypeList);

        mv.addObject("memberList", memberList);
        mv.addObject("deptList", deptList);
        mv.addObject("authoritTypeList",authoritTypeList);

        mv.setViewName("/empManage/boardManage");

        return mv;

    }

    @PostMapping(value = "/updateList", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String updateBoardAuthority(@RequestParam int memNum, @RequestParam boolean db_all, @RequestParam boolean nt_all){

        System.out.println("memNum + cd_all + cd_dept = " + memNum + db_all + nt_all);

        int result = authorService.deleteBoardAuthority(memNum);

        List<AuthoritDTO> authList = new ArrayList<>();
        if(db_all){
            AuthoritDTO auth = new AuthoritDTO();
            auth.setMemNum(memNum);
            auth.setAuCode("ROLE_DB_ALL");
            authList.add(auth);
        }

        if(nt_all){
            AuthoritDTO auth = new AuthoritDTO();
            auth.setMemNum(memNum);
            auth.setAuCode("ROLE_NT_ALL");
            authList.add(auth);
        }

        System.out.println("authList = " + authList);

        int result2 = authorService.insertAuthority(authList);

        String data = "";

        if( result2 > 0 || result > 0){
            data = "success";
        } else{
            data = "fail";
        }

        return data;
    }



}
