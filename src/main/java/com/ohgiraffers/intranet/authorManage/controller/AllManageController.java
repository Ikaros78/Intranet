package com.ohgiraffers.intranet.authorManage.controller;

import com.ohgiraffers.intranet.authorManage.model.dto.AuthoritDTO;
import com.ohgiraffers.intranet.authorManage.model.dto.AuthoritTypeDTO;
import com.ohgiraffers.intranet.authorManage.model.service.AuthorServiceImpl;
import com.ohgiraffers.intranet.calendar.model.service.CalendarServiceImpl;
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
@RequestMapping("/allManage/*")
public class AllManageController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final AuthorServiceImpl authorService;

    @Autowired
    public AllManageController(AuthorServiceImpl authorService){

        this.authorService = authorService;

    }

    @GetMapping("/list")
    public ModelAndView AllManageList(ModelAndView mv , HttpServletRequest request){

        List<MemberDTO> memberList = authorService.selectMemberListForEmpAndAllManage();

        log.info("[AllManageController] memberList : " + memberList);

        List<AuthoritTypeDTO> authoritTypeList = new ArrayList<>();
        for(int i = 0; i < memberList.size(); i++){

            AuthoritTypeDTO authoritTypeDTO = new AuthoritTypeDTO();

            for(int j = 0; j < memberList.get(i).getAuthorit().size(); j ++){

                if(memberList.get(i).getAuthorit().get(j).getAuCode() != null && memberList.get(i).getAuthorit().get(j).getAuCode().equals("ROLE_HR_EM_AUTH")){
                    authoritTypeDTO.setHr_em_auth("HR_EM_AUTH");
                }
                if(memberList.get(i).getAuthorit().get(j).getAuCode() != null && memberList.get(i).getAuthorit().get(j).getAuCode().equals("ROLE_NT_DB_AUTH")){
                    authoritTypeDTO.setNt_db_auth("NT_DB_AUTH");
                }
                if(memberList.get(i).getAuthorit().get(j).getAuCode() != null && memberList.get(i).getAuthorit().get(j).getAuCode().equals("ROLE_CD_AUTH")){
                    authoritTypeDTO.setCd_auth("CD_AUTH");
                }
            }
            authoritTypeList.add(authoritTypeDTO);
        }

        log.info("List<AuthorityTypeDTO> authorityTypeList : " + authoritTypeList);

        mv.addObject("memberList", memberList);
        mv.addObject("authoritTypeList", authoritTypeList);

        mv.setViewName("/empManage/allManage");

        return mv;
    }

    @PostMapping(value = "/updateList", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String updateAllAuthority(@RequestParam int memNum, @RequestParam boolean nt_db_auth, @RequestParam boolean hr_em_auth,@RequestParam boolean cd_auth){

        System.out.println("memNum = " + memNum + ", nt_db_auth = " + nt_db_auth + ", hr_em_auth = " + hr_em_auth + ", cd_auth = " + cd_auth);

        int result = authorService.deleteAllAuthority(memNum);

        List<AuthoritDTO> authList = new ArrayList<>();
        if(nt_db_auth){
            AuthoritDTO auth = new AuthoritDTO();
            auth.setMemNum(memNum);
            auth.setAuCode("ROLE_NT_DB_AUTH");
            authList.add(auth);
        }

        if(hr_em_auth){
            AuthoritDTO auth = new AuthoritDTO();
            auth.setMemNum(memNum);
            auth.setAuCode("ROLE_HR_EM_AUTH");
            authList.add(auth);
        }

        if(cd_auth){
            AuthoritDTO auth = new AuthoritDTO();
            auth.setMemNum(memNum);
            auth.setAuCode("ROLE_CD_AUTH");
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
