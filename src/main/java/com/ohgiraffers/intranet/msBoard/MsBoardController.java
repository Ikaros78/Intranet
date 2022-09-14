package com.ohgiraffers.intranet.msBoard;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ohgiraffers.intranet.common.paging.Pagenation;
import com.ohgiraffers.intranet.common.paging.SelectCriteria;
import com.ohgiraffers.intranet.member.model.dto.DepartmentDTO;
import com.ohgiraffers.intranet.msBoard.model.dto.MsBoardDTO;
import com.ohgiraffers.intranet.msBoard.model.dto.MsFileDTO;
import com.ohgiraffers.intranet.msBoard.model.dto.MsMemberListDTO;
import com.ohgiraffers.intranet.msBoard.model.service.MsBoardService;
import com.ohgiraffers.intranet.notice.model.dto.GalleryFileDTO;
import com.ohgiraffers.intranet.notice.model.dto.NoticeDTO;

@Controller
@RequestMapping(value = { "/ms/*" })
public class MsBoardController {

	public final MsBoardService msBoardService;
	
	@Autowired
	public MsBoardController(MsBoardService msBoardService) {

		this.msBoardService = msBoardService;

	}

//	받은 쪽지함 Controller

	@GetMapping(value = "/recp")
	public ModelAndView selectMsRecpBoard(ModelAndView mv, HttpServletRequest request) {

		String currentPage = request.getParameter("currentPage");
		int pageNo = 1;

		if (currentPage != null && !"".equals(currentPage)) {
			pageNo = Integer.parseInt(currentPage);
		}

		String searchCondition = request.getParameter("searchCondition");
		String searchValue = request.getParameter("searchValue");

		Map<String, String> searchMap = new HashMap<>();
		searchMap.put("searchCondition", searchCondition);
		searchMap.put("searchValue", searchValue);

		int totalCount = msBoardService.selectTotalCount(searchMap);

		/* 한 페이지에 보여 줄 게시물 수 */
		int limit = 10; // 얘도 파라미터로 전달받아도 된다.

		/* 한 번에 보여질 페이징 버튼의 갯수 */
		int buttonAmount = 5;

		/* 페이징 처리를 위한 로직 호출 후 페이징 처리에 관한 정보를 담고 있는 인스턴스를 반환받는다. */
		SelectCriteria selectCriteria = null;

		if (searchCondition != null && !"".equals(searchCondition)) {
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, searchCondition,
					searchValue);
		} else {
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
		}

		List<MsBoardDTO> boardList = msBoardService.selectMsRecpBoard(selectCriteria);

		System.out.println("boardList + "  + boardList);
		
		mv.addObject("boardList", boardList);
		mv.addObject("selectCriteria", selectCriteria);
		mv.setViewName("message/messageRecpBox");

		return mv;
	}

//	보낸 쪽지함 Controller

	@GetMapping(value = "/send")
	public ModelAndView selectMsSendBoard(ModelAndView mv, HttpServletRequest request) {

		String currentPage = request.getParameter("currentPage");
		int pageNo = 1;

		if (currentPage != null && !"".equals(currentPage)) {
			pageNo = Integer.parseInt(currentPage);
		}

		String searchCondition = request.getParameter("searchCondition");
		String searchValue = request.getParameter("searchValue");

		Map<String, String> searchMap = new HashMap<>();
		searchMap.put("searchCondition", searchCondition);
		searchMap.put("searchValue", searchValue);

		int totalCount = msBoardService.selectSendTotalCount(searchMap);

		/* 한 페이지에 보여 줄 게시물 수 */
		int limit = 10; // 얘도 파라미터로 전달받아도 된다.

		/* 한 번에 보여질 페이징 버튼의 갯수 */
		int buttonAmount = 5;

		/* 페이징 처리를 위한 로직 호출 후 페이징 처리에 관한 정보를 담고 있는 인스턴스를 반환받는다. */
		SelectCriteria selectCriteria = null;

		if (searchCondition != null && !"".equals(searchCondition)) {
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, searchCondition,
					searchValue);
		} else {
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
		}

		List<MsBoardDTO> boardList = msBoardService.selectMsSendBoard(selectCriteria);

		mv.addObject("boardList", boardList);
		mv.addObject("selectCriteria", selectCriteria);
		mv.setViewName("message/messageSendBox");

		return mv;
	}

	// 받은쪽지함 전체 검색

	@GetMapping(value = "/allrecp")
	public ModelAndView selectMsAllRecpBoard(ModelAndView mv, HttpServletRequest request) {

		String currentPage = request.getParameter("currentPage");
		int pageNo = 1;

		if (currentPage != null && !"".equals(currentPage)) {
			pageNo = Integer.parseInt(currentPage);
		}

		String searchCondition = request.getParameter("searchCondition");
		String searchValue = request.getParameter("searchValue");

		Map<String, String> searchMap = new HashMap<>();
		searchMap.put("searchCondition", searchCondition);
		searchMap.put("searchValue", searchValue);

		int totalCount = msBoardService.selectAllRecpTotalCount(searchMap);

		/* 한 페이지에 보여 줄 게시물 수 */
		int limit = 10; // 얘도 파라미터로 전달받아도 된다.

		/* 한 번에 보여질 페이징 버튼의 갯수 */
		int buttonAmount = 5;

		/* 페이징 처리를 위한 로직 호출 후 페이징 처리에 관한 정보를 담고 있는 인스턴스를 반환받는다. */
		SelectCriteria selectCriteria = null;

		if (searchCondition != null && !"".equals(searchCondition)) {
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, searchCondition,
					searchValue);
		} else {
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
		}

		List<MsBoardDTO> boardList = msBoardService.selectMsAllRecpBoard(selectCriteria);

		mv.addObject("boardList", boardList);
		mv.addObject("selectCriteria", selectCriteria);
		mv.setViewName("message/messageAllBox");

		return mv;
	}

//	쪽지 보내기 Controller

	@GetMapping("/msinsert")
	public String MsboardInsert() {

		return "message/messageSend";
	}
	
	@PostMapping("/msinsert")
	public String MsboardInsert(@RequestParam List<MultipartFile> msfile, @ModelAttribute MsBoardDTO msBoardDTO,
			HttpServletRequest request, Model model) throws FileNotFoundException {

		msBoardDTO.getMemNum();
		msBoardDTO.getMsTitle();
		msBoardDTO.getContents();
		msBoardDTO.getSendDate();
		msBoardDTO.getRecpNum();
		msBoardDTO.getSendNum();
		msBoardDTO.getSendName();

		msBoardService.MsboardInsert(msBoardDTO);

		// 저장 경로 설정
		String filePath = ResourceUtils.getURL("src/main/resources").getPath() + "upload";
		String fileUploadDirectory = filePath + "/msFile";

		File mkdir = new File(fileUploadDirectory);
		if (!mkdir.exists()) {

			mkdir.mkdir();
		}

		String originFileName = "";
		String ext = "";
		String savedName = "";

		System.out.println("===============" + msfile);

		// 리스트로 받아준 파일들을 이름 설정 해서 돌려주고
		try {

			for (int i = 0; i < msfile.size(); i++) {

				originFileName = msfile.get(i).getOriginalFilename();
				ext = originFileName.substring(originFileName.lastIndexOf("."));
				savedName = UUID.randomUUID().toString().replace("-", "") + ext;

				MsFileDTO msFileDTO = new MsFileDTO();
				msFileDTO.setOriginName(originFileName);
				msFileDTO.setSaveName(savedName);
				msFileDTO.setSavePath(fileUploadDirectory);
				
				int result = msBoardService.MsFileInsert(msFileDTO);
				
				if(result != 0 ) {
			
					msfile.get(i).transferTo(new File(fileUploadDirectory + "\\" + savedName));
				}
			}

			model.addAttribute("message", "파일 업로드 성공!");

		} catch (Exception e) {

			for (int i = 0; i < msfile.size(); i++) {

				originFileName = msfile.get(i).getOriginalFilename();
				ext = originFileName.substring(originFileName.lastIndexOf("."));
				savedName = UUID.randomUUID().toString().replace("-", "") + ext;

				new File(fileUploadDirectory + "\\" + savedName).delete();
				;

			}

			model.addAttribute("message", "파일 업로드 실패!");
		}

		return "redirect:/ms/recp";
	}

	// 쪽지 상세보기 컨트롤러

	@GetMapping("/msdetail")
	public String selectMsBoardDetail(HttpServletRequest request, Model model) {

		int msNo = Integer.parseInt(request.getParameter("msNo"));

		MsBoardDTO boardDetail = msBoardService.selectMsBoardDetail(msNo);
		
		model.addAttribute("board",boardDetail);
		
		return "message/messageDetail";
	}

	@GetMapping(value = "getdeptList", produces = "application/json; charset-UTF-8")
	@ResponseBody
	public List<DepartmentDTO> getdeptList() throws Exception {

		List<DepartmentDTO> result = msBoardService.getdeptList();

		System.out.println(result);

		return result;
	}

	@GetMapping(value = "getMemberList", produces = "application/json; charset-UTF-8")
	@ResponseBody
	private List<MsMemberListDTO> getMemberList(HttpServletRequest request) throws Exception {

		String dept_name = request.getParameter("data");
		System.out.println("deptName 확인 : " + dept_name);
		List<MsMemberListDTO> result = msBoardService.getMemberList(dept_name);

		return result;
	}

	@GetMapping(value = "getMemberListSecond", produces = "application/json; charset-UTF-8")
	@ResponseBody
	private List<MsMemberListDTO> getMemberListSecond(HttpServletRequest request) throws Exception {

		String name = request.getParameter("data");

		List<MsMemberListDTO> result = msBoardService.getMemberListSecond(name);

		return result;
	}

	
//	
//    @GetMapping("/recpDelete")
//    public String recpBoardDelete(@ModelAttribute MsBoardDTO msBoard, HttpServletRequest request){
//
//        int msNo = Integer.parseInt(request.getParameter("msNo"));
//
//        //요기다가 update 문 작성해서 삭제여부 변경해준 다음에 
//        msBoardService.recpYNMsBoard(msBoard); // 값
//        
//        // 받은사람 삭제 여부 및 보낸사람 삭제여부가 둘다 Y 이면 삭제를 진행하고  
//        msBoardService.recpBoardDelete(msNo);
//
//        return "redirect:/ms/recp";
//    }
//    
//    @GetMapping("/sendDelete")
//    public String sendBoardDelete(@ModelAttribute MsBoardDTO msBoard, HttpServletRequest request){
//    	
//    	int msNo = Integer.parseInt(request.getParameter("msNo"));
//    	
//    	msBoardService.sendYNMsBoard(msBoard);
//    	
//    	msBoardService.sendBoardDelete(msBoard);
//    	
//    	return "redirect:/ms/send";
//    }
//	
	
}
