package com.ohgiraffers.intranet.empManage.controller;

import com.ohgiraffers.intranet.common.paging.Pagenation;
import com.ohgiraffers.intranet.common.paging.SelectCriteria;
import com.ohgiraffers.intranet.empManage.model.service.EmpService;
import com.ohgiraffers.intranet.member.model.dto.MemberDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/emp/*")
public class EmpController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public final EmpService empService;
    public EmpController(EmpService empService){

        this.empService = empService;
    }

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

    @GetMapping("/hrList")
    public ModelAndView hrManagePage(HttpServletRequest request, ModelAndView mv){

        mv.setViewName("empManage/hrList");

        return mv;
    }


}
