package com.ohgiraffers.intranet.authorManage.controller;

import com.ohgiraffers.intranet.authorManage.model.dto.AuthoritDTO;
import com.ohgiraffers.intranet.authorManage.model.dto.AuthoritTypeDTO;
import com.ohgiraffers.intranet.authorManage.model.service.AuthorService;
import com.ohgiraffers.intranet.member.model.dto.MemberDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
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

        log.info("[EmpManageController] memberList : " + memberList);
        List<AuthoritTypeDTO> authoritTypeList = new ArrayList<>();
        for(int i = 0; i < memberList.size(); i++){

            AuthoritTypeDTO authoritTypeDTO = new AuthoritTypeDTO();
            for(int j = 0; j < memberList.get(i).getAuthorit().size(); j++){

                // System.out.println("memberList check : " + (memberList.get(i).getAuthorit().get(j).getAuCode() != null && memberList.get(i).getAuthorit().get(j).getAuCode().equals("ROLE_EM_ALL")));
                if(memberList.get(i).getAuthorit().get(j).getAuCode() != null && memberList.get(i).getAuthorit().get(j).getAuCode().equals("ROLE_EM_ALL")){
                    authoritTypeDTO.setEm_all("EM_ALL");
                }
                if (memberList.get(i).getAuthorit().get(j).getAuCode() != null && memberList.get(i).getAuthorit().get(j).getAuCode().equals("ROLE_EM_READ")) {
                    authoritTypeDTO.setEm_read("EM_READ");
                }
                if (memberList.get(i).getAuthorit().get(j).getAuCode() != null && memberList.get(i).getAuthorit().get(j).getAuCode().equals("ROLE_HR_ALL")) {
                    authoritTypeDTO.setHr_all("HR_ALL");
                }
                if (memberList.get(i).getAuthorit().get(j).getAuCode() != null && memberList.get(i).getAuthorit().get(j).getAuCode().equals("ROLE_HR_READ")) {
                    authoritTypeDTO.setHr_read("HR_READ");
                }
                if (memberList.get(i).getAuthorit().get(j).getAuCode() != null && memberList.get(i).getAuthorit().get(j).getAuCode().equals("ROLE_NM")) {
                    authoritTypeDTO.setNm("NM");
                }

            }
            authoritTypeList.add(authoritTypeDTO);
        }

        log.info(" List<AuthoritTypeDTO> authoritTypeList : " + authoritTypeList);

        mv.addObject("memberList", memberList);
        mv.addObject("authoritTypeList", authoritTypeList);

        mv.setViewName("/empManage/empManage");

        return mv;
    }

    @PostMapping(value = "/updateList", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String updateBoardAuthority(@RequestParam int memNum, @RequestParam boolean em_all, @RequestParam boolean em_read,@RequestParam boolean hr_all,@RequestParam boolean hr_read,@RequestParam boolean nm){


        System.out.println("memNum , em_all, em_read, hr_all, hr_read, nm = " + memNum + em_all + em_read + hr_all + hr_read + nm);


        int result = authorService.deleteEmpAuthority(memNum);

        List<AuthoritDTO> authList = new ArrayList<>();
        if(em_all){
            AuthoritDTO auth = new AuthoritDTO();
            auth.setMemNum(memNum);
            auth.setAuCode("ROLE_EM_ALL");
            authList.add(auth);
        }

        if(em_read){
            AuthoritDTO auth = new AuthoritDTO();
            auth.setMemNum(memNum);
            auth.setAuCode("ROLE_EM_READ");
            authList.add(auth);
        }

        if(hr_all){
            AuthoritDTO auth = new AuthoritDTO();
            auth.setMemNum(memNum);
            auth.setAuCode("ROLE_HR_ALL");
            authList.add(auth);
        }

        if(hr_read){
            AuthoritDTO auth = new AuthoritDTO();
            auth.setMemNum(memNum);
            auth.setAuCode("ROLE_HR_READ");
            authList.add(auth);
        }

        if(nm){
            AuthoritDTO auth = new AuthoritDTO();
            auth.setMemNum(memNum);
            auth.setAuCode("ROLE_NM");
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
