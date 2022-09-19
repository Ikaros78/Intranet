package com.ohgiraffers.intranet.deptNotice.controller;

import com.ohgiraffers.intranet.common.paging.Pagenation;
import com.ohgiraffers.intranet.common.paging.SelectCriteria;
import com.ohgiraffers.intranet.deptNotice.model.dto.DbDeptDTO;
import com.ohgiraffers.intranet.deptNotice.model.service.DeptNoticeService;
import com.ohgiraffers.intranet.deptNotice.model.service.DeptNoticeServiceImpl;
import com.ohgiraffers.intranet.member.model.dto.UserImpl;
import com.ohgiraffers.intranet.notice.model.dto.NoticeDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/deptNotice/*")
public class DeptNoticeController {

    private final DeptNoticeServiceImpl deptNoticeService;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public DeptNoticeController(DeptNoticeServiceImpl deptNoticeService){
        this.deptNoticeService = deptNoticeService;
    }

    @GetMapping("/list")
    public ModelAndView DeptNoticeList(ModelAndView mv, HttpServletRequest request ){

        String currentPage = request.getParameter("currentPage");
        int pageNo = 1;

        if(currentPage != null && !"".equals(currentPage)){
            pageNo = Integer.parseInt(currentPage);
        }

        Authentication session = SecurityContextHolder.getContext().getAuthentication();
        UserImpl user = (UserImpl) session.getPrincipal();
        String deptCode = user.getDept_code();

        log.info("session = " + session);
        log.info("deptCode = " + deptCode);

        String searchCondition = request.getParameter("searchCondition");
        String searchValue = request.getParameter("searchValue");

        log.info("searchCondition = " + searchCondition);

        Map<String, String> searchMap = new HashMap<>();
        searchMap.put("searchCondition", searchCondition);
        searchMap.put("searchValue", searchValue);
        searchMap.put("deptCode",deptCode);

        log.info("searchMap = " + searchMap);

        int totalCount = deptNoticeService.selectTotalCount(searchMap);

        System.out.println("totalCount = " + totalCount);
        int limit = 10;
        int buttonAmount = 5;

        SelectCriteria selectCriteria = null;

        if(searchCondition != null && !"".equals(searchCondition)){

            selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount,
                    searchCondition, searchValue);
        } else {

            selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
        }


        Map<String, Object> finalMap = new HashMap<>();
        finalMap.put("selectCriteria",selectCriteria);
        finalMap.put("deptCode",deptCode);

        log.info("finalMap = " +  finalMap);

        List<DbDeptDTO> deptNoticeList = deptNoticeService.selectDeptNoticeList(finalMap);
        log.info("deptNoticeList = " +  deptNoticeList);

        mv.addObject("deptNoticeList", deptNoticeList);
        mv.addObject("selectCriteria",selectCriteria);

        mv.setViewName("deptNotice/deptNoticeList");

        return mv;
    }

    @GetMapping("/detail")
    public String deptNoticeDetailPage(HttpServletRequest request, Model model){

        int no = Integer.parseInt(request.getParameter("no"));
        log.info("no 값 대체 머야 : " + request.getParameter("no"));

        DbDeptDTO deptNotice = deptNoticeService.selectDeptNoticeDetail(no);
        log.info("deptNotice = " + deptNotice);
        model.addAttribute("deptNotice", deptNotice);

        return "deptNotice/deptNoticeDetail";
    }

    @GetMapping("/regist")
    public String deptNoticeRegistPage(){

        return "deptNotice/deptNoticeRegist";
    }

    @PostMapping("/regist")
    public String deptNoticeRegist(@ModelAttribute DbDeptDTO deptNotice, HttpServletRequest request){

        log.info("deptNotice : " + deptNotice);
//        int deptNoticeRegist = deptNoticeService.deptNoticeRegist(deptNotice);

        int deptNoticeReistResult = deptNoticeService.deptNoticeRegist(deptNotice);

        System.out.println("deptNoticeReistResult = " + deptNoticeReistResult);

        return "redirect:/deptNotice/list";
    }

    @GetMapping("/delete")
    public String deptNoticeDelete(@ModelAttribute DbDeptDTO deptNotice,HttpServletRequest request){

        int db_no = Integer.parseInt(request.getParameter("db_no"));
        log.info("삭제 위한 no 들고왔슈 : " + db_no );

        int deptNoticeDeleteResult  = deptNoticeService.deptNoticeDelete(deptNotice);

        return  "redirect:/deptNotice/list";
    }

    @GetMapping("/update")
    public String deptNoticeUpdatePage(HttpServletRequest request, Model model){

        int no = Integer.parseInt(request.getParameter("db_no"));
        System.out.println(" 수정 하기 위한 no = " + no);

        DbDeptDTO deptNotice = deptNoticeService.selectDeptNoticeDetail(no);
        model.addAttribute("deptNotice",deptNotice);

        return "deptNotice/deptNoticeUpdate";

    }

    @PostMapping("/update")
    public String deptNoticeUpdate(@ModelAttribute DbDeptDTO deptNotice, RedirectAttributes rttr){

        log.info(" 수정 deptNotice : " + deptNotice);
        
        int deptNoticeUpdate = deptNoticeService.deptNoticeUpdate(deptNotice);

        System.out.println("deptNoticeUpdate = " + deptNoticeUpdate);

        rttr.addFlashAttribute("message", "수정 완료!");

        return "redirect:/deptNotice/list";
    }
}
