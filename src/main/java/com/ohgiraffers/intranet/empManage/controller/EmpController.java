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
        mv.addObject("searchValue", searchValue);

        mv.setViewName("empManage/empList");

        return mv;
    }

    /* 인사 발령 리스트 조회*/
    @GetMapping("/hrList")
    public ModelAndView hrManagePage(HttpServletRequest request, ModelAndView mv){

        String currentPage = request.getParameter("currentPage");
        int pageNo = 1;

        if(currentPage != null && !"".equals(currentPage)){
            pageNo = Integer.parseInt(currentPage);
        }

        String searchCondition = request.getParameter("searchCondition");
        String searchValue = request.getParameter("searchValue");

        if(searchValue != null){
            searchCondition = "writer";
        }

        Map<String, String> searchMap = new HashMap<>();
        searchMap.put("searchCondition", searchCondition);
        searchMap.put("searchValue", searchValue);
        log.info("검색조건 확인 : " + searchMap);

        int totalCount = empService.selectHrListTotalCount(searchMap);

        int limit = 10;
        int buttonAmount = 5;

        SelectCriteria selectCriteria = null;

        if(searchCondition != null && !"".equals(searchCondition)){
            selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, searchCondition, searchValue);
        } else {
            selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
        }
        log.info("selectCriteria 확인 : " + selectCriteria);

        List<AppointmentDTO> appointList = empService.selectHrList(selectCriteria);

        mv.addObject("appointList", appointList);
        mv.addObject("selectCriteria", selectCriteria);
        mv.addObject("searchValue", searchValue);

        mv.setViewName("empManage/hrList");

        return mv;
    }

    /* 인사 발령 등록 페이지 */
    @GetMapping("/hrRegist")
    public String hrRegistPage(){

        return "empManage/hrRegist";
    }

    @PostMapping("/hrRegist")
    public String hrRegist(@ModelAttribute AppointmentDTO appointment, HttpServletRequest request){

        String bef_rank = request.getParameter("bef_rank");
        String bef_name = request.getParameter("bef_name");
        String bef_code = request.getParameter("bef_code");
        log.info("bef_rank 값 확인 : " + bef_rank);
        log.info("bef_name 값 확인 : " + bef_name);
        log.info("bef_code 값 확인 : " + bef_code);

        String dept_rank = request.getParameter("dept_rank");
        String dept_code = request.getParameter("dept_code");
        log.info("dept_rank값이랑 dept_code값 확인 : " + dept_rank + " " + dept_code);

        int mem_num = Integer.parseInt(request.getParameter("mem_num"));
        log.info("mem_num값 확인 : " + mem_num);

        int registResult = empService.appointmentRegist(appointment); // 인사 발령 테이블에 값 등록 먼저 하기
        if(registResult > 0){

            MemberDTO member = new MemberDTO();
            member.setDept_code(dept_code);
            member.setDept_rank(dept_rank);
            member.setMem_num(mem_num);

            int updateResult = empService.appointmentUpdate(member); // 멤버 테이블에 변경된 값 넣기

            if(updateResult > 0){
                log.info("인사 발령 관련 정보 등록 성공");
            }
        } else {
            
            log.info("인사 발령 관련 정보 등록 실패");
        }

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

    /* 인사 발령 취소 */
    @GetMapping("/hrDelete")
    public String hrDelete(HttpServletRequest request){

        int no = Integer.parseInt(request.getParameter("no")); // 발령 전 직급 테이블 넘버
        int mem_num = Integer.parseInt(request.getParameter("mem_num"));
        String dept_rank = request.getParameter("bef_rank");
        String dept_code = request.getParameter("dept_code");

        log.info("값 가져오나요 : " + no + " " + mem_num + dept_rank + dept_code);

        int result = empService.updateMember(mem_num, dept_rank, dept_code);

        if (result > 0){

            int result2 = empService.hrDelete(no);
        }

        return "redirect:/emp/hrList";
    }

    /* 직원 상세 정보 조회 */
    @GetMapping("/empDetail")
    public String hrDetailPage(HttpServletRequest request, Model model){

        int no = Integer.parseInt((request.getParameter("no")));

        MemberDTO empDetail = empService.selectMemberDetail(no);
        log.info("empDetail값 확인 : " + empDetail);
        model.addAttribute("emp", empDetail);

        return "empManage/empDetail";
    }

    /* 직원 정보 수정 - 관리자 */
    @GetMapping("/empUpdate")
    public String empUpdatePage(HttpServletRequest request, Model model){
        int no = Integer.parseInt((request.getParameter("no")));

        MemberDTO empDetail = empService.selectMemberDetail(no);
        log.info("empDetail값 확인 : " + empDetail);
        model.addAttribute("emp", empDetail);

        return "empManage/empUpdate";
    }

    @PostMapping("/empUpdate")
    public String empUpdate(@ModelAttribute MemberDTO member, HttpServletRequest request){

        int bef_num = Integer.parseInt(request.getParameter("bef_num"));
        log.info("전 number값 받아오는지 확인 : " + bef_num);

//        int modifyResult = empService.numModify(member); // 이전 직원번호 테이블에 먼저 넣어줌
//        log.info("modifyResult : " + modifyResult);

//        if(modifyResult > 0) {
            int updateResult = empService.empUpdate(member); // 정보 업데이트
//
//            if(updateResult > 0){
//                int deleteResult = empService.numDelete(member); // 이전 직원번호 삭제
//
//            }
//        } else {
//
            log.info("직원 정보 수정에 실패하였습니다.");
//        }


        return "redirect:/emp/empList";
    }

}
