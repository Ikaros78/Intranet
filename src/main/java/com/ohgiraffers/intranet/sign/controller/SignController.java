package com.ohgiraffers.intranet.sign.controller;

import com.ohgiraffers.intranet.common.exception.sign.SignApproveException;
import com.ohgiraffers.intranet.common.paging.Pagenation;
import com.ohgiraffers.intranet.common.paging.SelectCriteria;
import com.ohgiraffers.intranet.member.model.dto.UserImpl;
import com.ohgiraffers.intranet.sign.model.dto.SignDTO;
import com.ohgiraffers.intranet.sign.model.dto.SignFormDTO;
import com.ohgiraffers.intranet.sign.model.service.SignService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sign/*")
public class SignController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final SignService signService;

    @Autowired
    public SignController(SignService signService){

        this.signService = signService;
    }

    @GetMapping(value = {"/main", "/waitingList"})
    public ModelAndView signWaitingList(HttpServletRequest request, ModelAndView mv, @AuthenticationPrincipal User user){

        /*
         * 목록보기를 눌렀을 시 가장 처음에 보여지는 페이지는 1페이지이다.
         * 파라미터로 전달되는 페이지가 있는 경우 currentPage는 파라미터로 전달받은 페이지 수 이다.
         */
        String currentPage = request.getParameter("currentPage");
        int pageNo = 1;

        if(currentPage != null && !"".equals(currentPage)) {
            pageNo = Integer.parseInt(currentPage);
        }

        String searchWriter = request.getParameter("searchWriter");
        String searchForm = request.getParameter("searchForm");
        String searchTitle = request.getParameter("searchTitle");
        String searchStartDate = request.getParameter("searchStartDate");
        String searchEndDate = request.getParameter("searchEndDate");
        String searchNum = request.getParameter("searchNum");
        int mem_num = ((UserImpl)user).getMem_num();

        log.info("memId = " + mem_num);

        Map<String, Object> searchMap = new HashMap<>();
        searchMap.put("mem_num", mem_num);
        searchMap.put("searchWriter", searchWriter);
        searchMap.put("searchForm", searchForm);
        searchMap.put("searchTitle", searchTitle);
        searchMap.put("searchStartDate", searchStartDate);
        searchMap.put("searchEndDate", searchEndDate);
        searchMap.put("searchNum", searchNum);

        /*
         * 전체 게시물 수가 필요하다.
         * 데이터베이스에서 먼저 전체 게시물 수를 조회해올 것이다.
         * 검색조건이 있는 경우 검색 조건에 맞는 전체 게시물 수를 조회한다.
         */
        int totalCount = signService.selectTotalWaitingCount(searchMap);

        System.out.println(totalCount);

        /* 한 페이지에 보여 줄 게시물 수 */
        int limit = 10;		//얘도 파라미터로 전달받아도 된다.

        /* 한 번에 보여질 페이징 버튼의 갯수 */
        int buttonAmount = 5;

        /* 페이징 처리를 위한 로직 호출 후 페이징 처리에 관한 정보를 담고 있는 인스턴스를 반환받는다. */
        SelectCriteria selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);

        Map<String, Object> searchList = new HashMap<>();
        searchList.put("mem_num", mem_num);
        searchList.put("selectCriteria", selectCriteria);
        searchList.put("searchWriter", searchWriter);
        searchList.put("searchForm", searchForm);
        searchList.put("searchTitle", searchTitle);
        searchList.put("searchStartDate", searchStartDate);
        searchList.put("searchEndDate", searchEndDate);
        searchList.put("searchNum", searchNum);

        System.out.println("searchList = " + searchList);

        /* 조회해 온다 */
        List<SignDTO> waitingList = signService.selectWaitingList(searchList);

        System.out.println("waitingList = " + waitingList);
        
        mv.addObject("waitingList", waitingList);
        mv.addObject("selectCriteria", selectCriteria);
        mv.addObject("searchList", searchList);

        mv.setViewName("sign/signWaitingList");

        return mv;
    }

    @GetMapping("/waitingDetail")
    public ModelAndView signWaitingDetail(ModelAndView mv, HttpServletRequest request){

        String signNo = request.getParameter("no");
        SignDTO signDetail = signService.selectSignDetail(signNo);

        log.info("signDetail : " + signDetail);

        mv.addObject("signDetail" , signDetail);

        mv.setViewName("sign/signWaitingDetail");

        return mv;
    }

    @GetMapping("/signChecked")
    public ModelAndView signChecked(ModelAndView mv, HttpServletRequest request, @AuthenticationPrincipal User user, RedirectAttributes rttr) throws SignApproveException {

        String getSignList = request.getParameter("checkArr");
        String[] signNoList = getSignList.split(",");

        int mem_num = ((UserImpl)user).getMem_num();

        Map<String, Object> signMap = new HashMap<>();
        signMap.put("mem_num", mem_num);

        int count = 0;

        for(String sign : signNoList){

            signMap.put("sign", sign);

            int result = signService.updateSignChecked(signMap);
            count += result;
        }

        log.info("count = " + count);

        if(count == signNoList.length){

            mv.setViewName("redirect:/sign/waitingList");
            rttr.addFlashAttribute("message", "일괄 결재에 성공하였습니다.");

        }else{

            throw new SignApproveException("일괄결재에 실패하였습니다. 다시 시도해주세요.");
        }

        return mv;
    }

    @GetMapping("/registSelect")
    public ModelAndView signRegistSelect(ModelAndView mv, @AuthenticationPrincipal User user, HttpServletRequest request){

        int mem_num = ((UserImpl)user).getMem_num();

        String currentPage = request.getParameter("currentPage");
        int pageNo = 1;

        if(currentPage != null && !"".equals(currentPage)) {
            pageNo = Integer.parseInt(currentPage);
        }

        String searchName = request.getParameter("searchName");

        Map<String, Object> searchMap = new HashMap<>();
        searchMap.put("searchName", searchName);

        int totalCount = signService.selectTotalFormCount(searchMap);

        int limit = 10;

        int buttonAmount = 5;

        SelectCriteria selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);

        Map<String, Object> searchList = new HashMap<>();
        searchList.put("selectCriteria", selectCriteria);
        searchList.put("searchName", searchName);

        List<SignFormDTO> formList = signService.selectAllForm(searchList);

        List<SignDTO> recentForm = signService.selectRecentForm(mem_num);

        mv.addObject("totalCount", totalCount);

        mv.addObject("formList", formList);

        mv.addObject("selectCriteria", selectCriteria);

        mv.addObject("searchList", searchList);

        mv.addObject("recentForm", recentForm);

        mv.setViewName("sign/signRegistSelectForm");

        return mv;
    }

    @GetMapping("/registForm")
    public ModelAndView signRegistForm(ModelAndView mv){

        mv.setViewName("sign/signRegistForm");

        return mv;
    }

    @GetMapping("/requestList")
    public ModelAndView signRequestList(ModelAndView mv){

        mv.setViewName("sign/signRequestList");

        return mv;
    }

    @GetMapping("/tempList")
    public ModelAndView signTempList(ModelAndView mv){

        mv.setViewName("sign/signTempList");

        return mv;
    }

    @GetMapping("/progressList")
    public ModelAndView signProgressList(ModelAndView mv){

        mv.setViewName("sign/signProgressList");

        return mv;
    }

    @GetMapping("/completedList")
    public ModelAndView signCompletedList(ModelAndView mv){

        mv.setViewName("sign/signCompletedList");

        return mv;
    }

    @GetMapping("/refusedList")
    public ModelAndView signRefusedList(ModelAndView mv){

        mv.setViewName("sign/signRefusedList");

        return mv;
    }

    @GetMapping("/referenceList")
    public ModelAndView signReferenceList(ModelAndView mv){

        mv.setViewName("sign/signReferenceList");

        return mv;
    }

    @GetMapping("/teamList")
    public ModelAndView signTeamList(ModelAndView mv){

        mv.setViewName("sign/signTeamList");

        return mv;
    }
}
