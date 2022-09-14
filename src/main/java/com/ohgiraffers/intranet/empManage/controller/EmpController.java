package com.ohgiraffers.intranet.empManage.controller;

import com.ohgiraffers.intranet.common.paging.Pagenation;
import com.ohgiraffers.intranet.common.paging.SelectCriteria;
import com.ohgiraffers.intranet.empManage.model.dto.AppointmentDTO;
import com.ohgiraffers.intranet.empManage.model.service.EmpService;
import com.ohgiraffers.intranet.member.model.dto.MemberDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Member;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/emp/*")
public class EmpController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public final EmpService empService;
    @Autowired
    public EmpController(EmpService empService){

        this.empService = empService;
    }

    /* 직원 리스트 조회*/
    @GetMapping("/empList")
    public ModelAndView empManagePage(HttpServletRequest request, ModelAndView mv){

        String currentPage = request.getParameter("currentPage");
        int pageNo = 1;

        if(currentPage != null && !"".equals(currentPage)){
            pageNo = Integer.parseInt(currentPage);
        }

        String searchCondition = request.getParameter("searchCondition");
        String searchValue = request.getParameter("searchValue");

        Map<String, String> searchMap = new HashMap<>();
        searchMap.put("searchCondition", searchCondition);
        searchMap.put("searchValue", searchValue);

        int totalCount = empService.selectEmpTotalCount(searchMap);

        int limit = totalCount;
        int buttonAmount = 5;

        SelectCriteria selectCriteria = null;

        if(searchCondition != null && !"".equals(searchCondition)){

            selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount,
                    searchCondition, searchValue);
        } else {

            selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
        }

        List<MemberDTO> memberList = empService.selectEmpList(selectCriteria);

        mv.addObject("memberList", memberList);
        mv.addObject("selectCriteria", selectCriteria);

        mv.setViewName("empManage/empList");

        return mv;
    }

    /* 인사 발령 리스트 조회*/
    @GetMapping("/hrList")
    public ModelAndView hrManagePage(HttpServletRequest request, ModelAndView mv){

        mv.setViewName("empManage/hrList");

        return mv;
    }

    /* 인사 발령 등록 페이지 */
    @GetMapping("/hrRegist")
    public String hrRegistPage(){

        return "empManage/hrRegist";
    }

    @PostMapping("/hrRegist")
    public String hrRegist(@ModelAttribute AppointmentDTO appointment, HttpServletRequest request, RedirectAttributes rttr){

        String category = request.getParameter("category");
        log.info("값 받아오는지 확인 : " + request.getParameter("category"));
        appointment.setCategory(category);
        log.info("카테고리 값 받아오는지 확인 : " +  appointment.getCategory());

        int mem_num = Integer.parseInt(request.getParameter("mem_num"));
        String bef_rank = request.getParameter("bef_rank");
        String bef_dept = request.getParameter("bef_dept");
        log.info("값 가져오나 확인 : " + mem_num + bef_rank + bef_dept);

        int registResult = empService.appointmentRegist(appointment);

        return "redirect:/emp/hrList";
    }

    /* 인사 발령 등록 멤버 값 받아오기 ajax | 받아올 값 - 사원번호, 직원명, 발령 전 직급(현재직급), 발령 전 부서(현재부서)) */
    @GetMapping(value = "getMemberName", produces = "application/json; charset-UTF-8")
    @ResponseBody
    public MemberDTO getMemberName(HttpServletRequest request){

        log.info("확인용 : " + request.getParameter("mem_num"));
        int mem_num = Integer.parseInt(request.getParameter("mem_num"));

        MemberDTO result = empService.getMemberName(mem_num);

        log.info("확인용2 : " + String.valueOf(result));

        return result;
    }


}
