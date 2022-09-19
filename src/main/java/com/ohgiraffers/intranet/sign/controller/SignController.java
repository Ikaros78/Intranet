package com.ohgiraffers.intranet.sign.controller;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ohgiraffers.intranet.common.exception.sign.SignApproveException;
import com.ohgiraffers.intranet.common.paging.Pagenation;
import com.ohgiraffers.intranet.common.paging.SelectCriteria;
import com.ohgiraffers.intranet.member.model.dto.DepartmentDTO;
import com.ohgiraffers.intranet.member.model.dto.MemberDTO;
import com.ohgiraffers.intranet.member.model.dto.UserImpl;
import com.ohgiraffers.intranet.member.service.MemberService;
import com.ohgiraffers.intranet.sign.model.dto.*;
import com.ohgiraffers.intranet.sign.model.service.SignService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/sign")
public class SignController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final SignService signService;
    private final MemberService memberService;


    @Autowired
    public SignController(SignService signService, MemberService memberService){

        this.signService = signService;
        this.memberService = memberService;
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
        String opinion = request.getParameter("opinion");

        int mem_num = ((UserImpl)user).getMem_num();

        Map<String, Object> signMap = new HashMap<>();
        signMap.put("mem_num", mem_num);
        signMap.put("opinion", opinion);

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

    @GetMapping("/deleteChecked")
    public ModelAndView deleteChecked(ModelAndView mv, HttpServletRequest request, @AuthenticationPrincipal User user, RedirectAttributes rttr) throws SignApproveException {

        String getSignList = request.getParameter("checkArr");
        String[] signNoList = getSignList.split(",");

        int mem_num = ((UserImpl)user).getMem_num();

        Map<String, Object> signMap = new HashMap<>();
        signMap.put("mem_num", mem_num);

        int count = 0;

        for(String sign : signNoList){

            signMap.put("sign", sign);

            int result = signService.deleteSignChecked(signMap);
            count += result;
        }

        log.info("count = " + count);

        if(count == signNoList.length){

            mv.setViewName("redirect:/sign/tempList");
            rttr.addFlashAttribute("message", "일괄삭제에 성공하였습니다.");

        }else{

            throw new SignApproveException("일괄삭제에 실패하였습니다. 다시 시도해주세요.");
        }

        return mv;
    }

    @GetMapping("/registSelect")
    public ModelAndView signRegistSelect(ModelAndView mv, @AuthenticationPrincipal User user, HttpServletRequest request){

        String currentPage = request.getParameter("currentPage");
        int pageNo = 1;

        if(currentPage != null && !"".equals(currentPage)) {
            pageNo = Integer.parseInt(currentPage);
        }

        int mem_num = ((UserImpl)user).getMem_num();

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
    public ModelAndView signRegistForm(ModelAndView mv, HttpServletRequest request, @AuthenticationPrincipal User user){

        UserImpl userInfo = ((UserImpl)user);

        int mem_num = userInfo.getMem_num();

        String formCode = request.getParameter("code");

        SignFormDTO selectForm = signService.selectFormByCode(formCode);

        String deptName = memberService.selectDeptByNum(mem_num);

        Map<String , Object> formMap = new HashMap<>();
        formMap.put("userInfo", userInfo);
        formMap.put("selectForm", selectForm);
        formMap.put("deptName", deptName);

        mv.addObject("formMap", formMap);

        mv.setViewName("sign/signRegistForm");

        return mv;
    }

    @PostMapping("/registForm")
    public ModelAndView signRegistForm(ModelAndView mv, @RequestParam(name="originName", required = false)MultipartFile originName, HttpServletRequest request, @AuthenticationPrincipal User user) throws FileNotFoundException, SignApproveException {

        String writer = request.getParameter("signWriter");
        String formCode = request.getParameter("signFormCode");
        String title = request.getParameter("signTitle");
        String content = request.getParameter("signContent");
        String[] approver = request.getParameterValues("approver");
        String[] receiver = request.getParameterValues("receiver");
        String[] referencer = request.getParameterValues("referencer");

        log.info("writer = " + writer);
        log.info("formCode = " + formCode);
        log.info("title = " + title);
        log.info("content = " + content);

        Map<String, String> insertMap = new HashMap<>();
        insertMap.put("writer",writer);
        insertMap.put("formCode",formCode);
        insertMap.put("title",title);
        insertMap.put("content",content);

        int signResult = signService.registSign(insertMap);

        Map<String, String> approverMap = new HashMap<>();
        Map<String, String> receiverMap = new HashMap<>();
        Map<String, String> referencerMap = new HashMap<>();


        for(int i = 0; i < approver.length; i++){

            approverMap.put("approver", approver[i]);

            if(i == approver.length - 1){

                int registFinal = signService.registFianlApprover(approverMap);
            }else{

                int registApprover = signService.registApprover(approverMap);
            }
        }

        if(receiver != null){

            for(int i = 0; i < receiver.length; i++){

                receiverMap.put("receiver", receiver[i]);

                int registReceiver = signService.registReceiver(receiverMap);

            }
        }

        if(referencer != null){

            for(int i = 0; i < referencer.length; i++){

                referencerMap.put("referencer", referencer[i]);

                int registReferencer = signService.registReferencer(referencerMap);

            }
        }


        SignFileDTO signFile = new SignFileDTO();

        String filePath = ResourceUtils.getURL("src/main/resources").getPath() + "upload";

        String fileUploadDirectory = filePath +	 "/signFile";

        File mkdir = new File(fileUploadDirectory);

        if(!mkdir.exists()){
            mkdir.mkdirs();
        }

        String originFileName = "";
        String ext = "";
        String saveName = "";

        if(originName.getSize() > 0) { //파일 첨부한거 있으면
            //파일명 변경하고
            originFileName = originName.getOriginalFilename();
            ext = originFileName.substring(originFileName.lastIndexOf("."));
            saveName = UUID.randomUUID().toString().replace("-", "") + ext;

            signFile.setOriginName(originFileName);
            signFile.setSaveName(saveName);
            signFile.setSavePath(fileUploadDirectory);

            log.info("signFile 확인 : " + signFile);
            // file insert
            int fileResult = signService.signFileInsert(signFile);

            try {
                originName.transferTo(new File(fileUploadDirectory + "//" + saveName));

            } catch (IOException e) {

                e.printStackTrace();
                new File(fileUploadDirectory + "//" + saveName).delete();
            }

        }

        mv.setViewName("redirect:/sign/requestList");

        return mv;
    }

    @PostMapping("/saveTemp")
    public ModelAndView saveTemp(ModelAndView mv, HttpServletRequest request, @AuthenticationPrincipal User user) throws SignApproveException {

        String writer = request.getParameter("signWriter");
        String formCode = request.getParameter("signFormCode");
        String title = request.getParameter("signTitle");
        String content = request.getParameter("signContent");

        Map<String, String> insertMap = new HashMap<>();
        insertMap.put("writer",writer);
        insertMap.put("formCode",formCode);
        insertMap.put("title",title);
        insertMap.put("content", content);

        int signResult = signService.registTempSign(insertMap);

        mv.setViewName("redirect:/sign/tempList");

        return mv;
    }

    @PostMapping("/reWriteRegistForm")
    public ModelAndView resignRegistForm(ModelAndView mv, HttpServletRequest request, @AuthenticationPrincipal User user){

        UserImpl userInfo = ((UserImpl)user);

        int mem_num = userInfo.getMem_num();

        String signNo = request.getParameter("signNo");

        SignDTO sign = signService.selectSignDetail(signNo);

        String deptName = memberService.selectDeptByNum(mem_num);

        Map<String , Object> formMap = new HashMap<>();
        formMap.put("userInfo", userInfo);
        formMap.put("signDetail", sign);
        formMap.put("deptName", deptName);

        mv.addObject("formMap", formMap);

        mv.setViewName("sign/reWriteRegistForm");

        return mv;
    }

    @PostMapping("/approve")
    public ModelAndView approveSign(ModelAndView mv, HttpServletRequest request, @AuthenticationPrincipal User user, RedirectAttributes rttr) throws SignApproveException {

        int signNo = Integer.parseInt(request.getParameter("signNo"));
        String opinion = request.getParameter("opinion");

        int mem_num = ((UserImpl)user).getMem_num();

        log.info("opinion = " + opinion);
        log.info("signNo = " + signNo);

        Map<String, Object> approve = new HashMap<>();
        approve.put("signNo", signNo);
        approve.put("opinion", opinion);
        approve.put("mem_num", mem_num);

        int result = signService.approveSign(approve);

        mv.setViewName("redirect:/sign/waitingList");
        rttr.addFlashAttribute("message", "결재 완료하였습니다.");

        return mv;
    }

    @PostMapping("/refuse")
    public ModelAndView refuseSign(ModelAndView mv, HttpServletRequest request, @AuthenticationPrincipal User user, RedirectAttributes rttr) throws SignApproveException {

        int signNo = Integer.parseInt(request.getParameter("signNo"));
        String opinion = request.getParameter("opinion");

        int mem_num = ((UserImpl)user).getMem_num();

        Map<String, Object> refuse = new HashMap<>();
        refuse.put("signNo", signNo);
        refuse.put("opinion", opinion);
        refuse.put("mem_num", mem_num);

        int result = signService.refuseSign(refuse);

        mv.setViewName("redirect:/sign/waitingList");
        rttr.addFlashAttribute("message", "반려 완료하였습니다.");

        return mv;
    }

    @PostMapping("/delete")
    public ModelAndView deleteSign(ModelAndView mv,HttpServletRequest request, RedirectAttributes rttr) throws SignApproveException{

        int signNo = Integer.parseInt(request.getParameter("signNo"));

        int result = signService.deleteSign(signNo);

        mv.setViewName("redirect:/sign/tempList");
        rttr.addFlashAttribute("message", "삭제 완료하였습니다.");

        return mv;
    }

    @GetMapping(value = "/addApprover", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ModelAndView addApprover(ModelAndView mv, @AuthenticationPrincipal User user){

        UserImpl userInfo = ((UserImpl)user);

        List<DepartmentDTO> deptList = signService.selectDeptList();

        mv.addObject("deptList", deptList);
        mv.addObject("userInfo", userInfo);

        mv.setViewName("/sign/signpopupApprover");

        return mv;
    }

    @GetMapping(value = "/addReceiver", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ModelAndView addReceiver(ModelAndView mv, @AuthenticationPrincipal User user){

        UserImpl userInfo = ((UserImpl)user);

        List<DepartmentDTO> deptList = signService.selectDeptList();

        mv.addObject("deptList", deptList);
        mv.addObject("userInfo", userInfo);

        mv.setViewName("/sign/signpopupReceiver");

        return mv;
    }

    @GetMapping(value = "/addReferencer", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ModelAndView addReferencer(ModelAndView mv, @AuthenticationPrincipal User user){

        UserImpl userInfo = ((UserImpl)user);

        List<DepartmentDTO> deptList = signService.selectDeptList();

        mv.addObject("deptList", deptList);
        mv.addObject("userInfo", userInfo);

        mv.setViewName("/sign/signpopupReferencer");

        return mv;
    }

    @GetMapping(value = "selectMemByDeptCode", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public String selectMemByDeptCode(HttpServletRequest request){

        String code = request.getParameter("code");

        log.info("code = " + code);

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                .serializeNulls()
                .disableHtmlEscaping()
                .create();

        List<MemberDTO> memberList = signService.selectMemByDeptCode(code);

        return gson.toJson(memberList);
    }

    @GetMapping(value = "selectMemByNum", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public String selectMemByNum(HttpServletRequest request){

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                .serializeNulls()
                .disableHtmlEscaping()
                .create();

        String nums = request.getParameter("selectedMem");

        log.info("num =" + nums);

        String[] numList = nums.split(",");

        int count = 0;

        List<MemberDTO> memberList = new ArrayList<>();

        Map<String, Object> numMap = new HashMap<>();

        for(String num : numList){

            numMap.put("num", num);

            MemberDTO member = signService.selectMemByNum(numMap);

            memberList.add(member);

            count++;
        }

        if(count == memberList.size()){
            return gson.toJson(memberList);
        }

        return null;
    }

    @GetMapping("/requestList")
    public ModelAndView signRequestList(HttpServletRequest request, ModelAndView mv, @AuthenticationPrincipal User user){

        String currentPage = request.getParameter("currentPage");
        int pageNo = 1;

        if(currentPage != null && !"".equals(currentPage)) {
            pageNo = Integer.parseInt(currentPage);
        }

        String searchForm = request.getParameter("searchForm");
        String searchTitle = request.getParameter("searchTitle");
        String searchStartDate = request.getParameter("searchStartDate");
        String searchEndDate = request.getParameter("searchEndDate");
        int mem_num = ((UserImpl)user).getMem_num();

        Map<String, Object> searchMap = new HashMap<>();
        searchMap.put("mem_num", mem_num);
        searchMap.put("searchForm", searchForm);
        searchMap.put("searchTitle", searchTitle);
        searchMap.put("searchStartDate", searchStartDate);
        searchMap.put("searchEndDate", searchEndDate);

        int totalCount = signService.selectTotalRequestCount(searchMap);

        int limit = 10;

        int buttonAmount = 5;

        SelectCriteria selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);

        Map<String, Object> searchList = new HashMap<>();
        searchList.put("mem_num", mem_num);
        searchList.put("selectCriteria", selectCriteria);
        searchList.put("searchForm", searchForm);
        searchList.put("searchTitle", searchTitle);
        searchList.put("searchStartDate", searchStartDate);
        searchList.put("searchEndDate", searchEndDate);

        List<SignDTO> requestList = signService.selectRequestList(searchList);

        mv.addObject("requestList", requestList);
        mv.addObject("selectCriteria", selectCriteria);
        mv.addObject("searchList", searchList);

        mv.setViewName("sign/signRequestList");

        return mv;
    }

    @GetMapping("/requestDetail")
    public ModelAndView signRequestDetail(ModelAndView mv, HttpServletRequest request){

        String signNo = request.getParameter("no");
        SignDTO signDetail = signService.selectSignDetail(signNo);

        mv.addObject("signDetail" , signDetail);

        mv.setViewName("sign/signRequestDetail");

        return mv;
    }

    @GetMapping("/tempList")
    public ModelAndView signTempList(HttpServletRequest request, ModelAndView mv, @AuthenticationPrincipal User user){

        String currentPage = request.getParameter("currentPage");
        int pageNo = 1;

        if(currentPage != null && !"".equals(currentPage)) {
            pageNo = Integer.parseInt(currentPage);
        }

        String searchForm = request.getParameter("searchForm");
        String searchTitle = request.getParameter("searchTitle");
        String searchStartDate = request.getParameter("searchStartDate");
        String searchEndDate = request.getParameter("searchEndDate");
        int mem_num = ((UserImpl)user).getMem_num();

        Map<String, Object> searchMap = new HashMap<>();
        searchMap.put("mem_num", mem_num);
        searchMap.put("searchForm", searchForm);
        searchMap.put("searchTitle", searchTitle);
        searchMap.put("searchStartDate", searchStartDate);
        searchMap.put("searchEndDate", searchEndDate);

        int totalCount = signService.selectTotalTempCount(searchMap);

        int limit = 10;

        int buttonAmount = 5;

        SelectCriteria selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);

        Map<String, Object> searchList = new HashMap<>();
        searchList.put("mem_num", mem_num);
        searchList.put("selectCriteria", selectCriteria);
        searchList.put("searchForm", searchForm);
        searchList.put("searchTitle", searchTitle);
        searchList.put("searchStartDate", searchStartDate);
        searchList.put("searchEndDate", searchEndDate);

        List<SignDTO> tempList = signService.selectTempList(searchList);

        mv.addObject("tempList", tempList);
        mv.addObject("selectCriteria", selectCriteria);
        mv.addObject("searchList", searchList);

        mv.setViewName("sign/signTempList");

        return mv;
    }

    @GetMapping("/tempDetail")
    public ModelAndView signTempDetail(ModelAndView mv, HttpServletRequest request){

        String signNo = request.getParameter("no");
        SignDTO signDetail = signService.selectSignDetail(signNo);

        mv.addObject("signDetail" , signDetail);

        mv.setViewName("sign/signTempDetail");

        return mv;
    }

    @GetMapping("/progressList")
    public ModelAndView signProgressList(HttpServletRequest request, ModelAndView mv, @AuthenticationPrincipal User user){

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

        Map<String, Object> searchMap = new HashMap<>();
        searchMap.put("mem_num", mem_num);
        searchMap.put("searchWriter", searchWriter);
        searchMap.put("searchForm", searchForm);
        searchMap.put("searchTitle", searchTitle);
        searchMap.put("searchStartDate", searchStartDate);
        searchMap.put("searchEndDate", searchEndDate);
        searchMap.put("searchNum", searchNum);

        int totalCount = signService.selectTotalProgressCount(searchMap);

        log.info("totalCount : " + totalCount);

        int limit = 10;

        int buttonAmount = 5;

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

        List<SignDTO> progressList = signService.selectProgressList(searchList);

        mv.addObject("progressList", progressList);
        mv.addObject("selectCriteria", selectCriteria);
        mv.addObject("searchList", searchList);

        mv.setViewName("sign/signProgressList");

        return mv;
    }

    @GetMapping("/progressDetail")
    public ModelAndView signProgressDetail(ModelAndView mv, HttpServletRequest request){

        String signNo = request.getParameter("no");
        SignDTO signDetail = signService.selectSignDetail(signNo);

        log.info("signDetail : " + signDetail);

        mv.addObject("signDetail" , signDetail);

        mv.setViewName("sign/signProgressDetail");

        return mv;
    }

    @GetMapping("/completedList")
    public ModelAndView signCompletedList(HttpServletRequest request, ModelAndView mv, @AuthenticationPrincipal User user){

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

        Map<String, Object> searchMap = new HashMap<>();
        searchMap.put("mem_num", mem_num);
        searchMap.put("searchWriter", searchWriter);
        searchMap.put("searchForm", searchForm);
        searchMap.put("searchTitle", searchTitle);
        searchMap.put("searchStartDate", searchStartDate);
        searchMap.put("searchEndDate", searchEndDate);
        searchMap.put("searchNum", searchNum);

        int totalCompletedCount = signService.selectTotalCompletedCount(searchMap);

        int totalCount = totalCompletedCount;

        log.info("totalCount : " + totalCount);

        int limit = 10;

        int buttonAmount = 5;

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

        List<SignDTO> completedList = signService.selectCompletedList(searchList);

        log.info("completedList : " + completedList);

        mv.addObject("completedList", completedList);
        mv.addObject("selectCriteria", selectCriteria);
        mv.addObject("searchList", searchList);

        mv.setViewName("sign/signCompletedList");

        return mv;
    }

    @GetMapping("/completedDetail")
    public ModelAndView signCompletedDetail(ModelAndView mv, HttpServletRequest request){

        String signNo = request.getParameter("no");
        SignDTO signDetail = signService.selectSignDetail(signNo);

        log.info("signDetail : " + signDetail);

        mv.addObject("signDetail" , signDetail);

        mv.setViewName("sign/signCompletedDetail");

        return mv;
    }

    @GetMapping("/refusedList")
    public ModelAndView signRefusedList(HttpServletRequest request, ModelAndView mv, @AuthenticationPrincipal User user){

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

        Map<String, Object> searchMap = new HashMap<>();
        searchMap.put("mem_num", mem_num);
        searchMap.put("searchWriter", searchWriter);
        searchMap.put("searchForm", searchForm);
        searchMap.put("searchTitle", searchTitle);
        searchMap.put("searchStartDate", searchStartDate);
        searchMap.put("searchEndDate", searchEndDate);
        searchMap.put("searchNum", searchNum);

        int totalRefusedCount = signService.selectTotalRefusedCount(searchMap);

        int totalCount = totalRefusedCount;

        log.info("totalCount : " + totalCount);

        int limit = 10;

        int buttonAmount = 5;

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

        List<SignDTO> refusedList = signService.selectRefusedList(searchList);

        log.info("refusedList : " + refusedList);

        mv.addObject("refusedList", refusedList);
        mv.addObject("selectCriteria", selectCriteria);
        mv.addObject("searchList", searchList);

        mv.setViewName("sign/signRefusedList");

        return mv;
    }

    @GetMapping("/refusedDetail")
    public ModelAndView signRefusedDetail(ModelAndView mv, HttpServletRequest request){

        String signNo = request.getParameter("no");
        SignDTO signDetail = signService.selectSignDetail(signNo);

        log.info("signDetail : " + signDetail);

        mv.addObject("signDetail" , signDetail);

        mv.setViewName("sign/signRefusedDetail");

        return mv;
    }

    @GetMapping("/referenceList")
    public ModelAndView signReferenceList(ModelAndView mv, HttpServletRequest request, @AuthenticationPrincipal User user){

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

        Map<String, Object> searchMap = new HashMap<>();
        searchMap.put("mem_num", mem_num);
        searchMap.put("searchWriter", searchWriter);
        searchMap.put("searchForm", searchForm);
        searchMap.put("searchTitle", searchTitle);
        searchMap.put("searchStartDate", searchStartDate);
        searchMap.put("searchEndDate", searchEndDate);
        searchMap.put("searchNum", searchNum);

        int totalCount = signService.selectTotalReferenceCount(searchMap);

        int limit = 10;

        int buttonAmount = 5;

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

        List<SignDTO> referenceList = signService.selectReferenceList(searchList);

        mv.addObject("referenceList", referenceList);
        mv.addObject("selectCriteria", selectCriteria);
        mv.addObject("searchList", searchList);

        mv.setViewName("sign/signReferenceList");

        return mv;
    }

    @GetMapping("/referenceDetail")
    public ModelAndView signReferenceDetail(ModelAndView mv, HttpServletRequest request){

        String signNo = request.getParameter("no");
        SignDTO signDetail = signService.selectSignDetail(signNo);

        log.info("signDetail : " + signDetail);

        mv.addObject("signDetail" , signDetail);

        mv.setViewName("sign/signReferenceDetail");

        return mv;
    }

    @GetMapping("/receiveList")
    public ModelAndView signReceiveList(ModelAndView mv, HttpServletRequest request, @AuthenticationPrincipal User user){

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

        Map<String, Object> searchMap = new HashMap<>();
        searchMap.put("mem_num", mem_num);
        searchMap.put("searchWriter", searchWriter);
        searchMap.put("searchForm", searchForm);
        searchMap.put("searchTitle", searchTitle);
        searchMap.put("searchStartDate", searchStartDate);
        searchMap.put("searchEndDate", searchEndDate);
        searchMap.put("searchNum", searchNum);


        int totalCount = signService.selectTotalReceiveCount(searchMap);

        int limit = 10;

        int buttonAmount = 5;

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

        List<SignDTO> receiveList = signService.selectReceiveList(searchList);

        mv.addObject("receiveList", receiveList);
        mv.addObject("selectCriteria", selectCriteria);
        mv.addObject("searchList", searchList);

        mv.setViewName("sign/signReceiveList");

        return mv;
    }

    @GetMapping("/receiveDetail")
    public ModelAndView signReceiveDetail(ModelAndView mv, HttpServletRequest request){

        String signNo = request.getParameter("no");
        SignDTO signDetail = signService.selectSignDetail(signNo);

        log.info("signDetail : " + signDetail);

        mv.addObject("signDetail" , signDetail);

        mv.setViewName("sign/signReceiveDetail");

        return mv;
    }
}
