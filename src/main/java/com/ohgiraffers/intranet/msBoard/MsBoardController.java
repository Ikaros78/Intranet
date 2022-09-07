package com.ohgiraffers.intranet.msBoard;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ohgiraffers.intranet.common.paging.Pagenation;
import com.ohgiraffers.intranet.common.paging.SelectCriteria;
import com.ohgiraffers.intranet.member.model.dto.DepartmentDTO;
import com.ohgiraffers.intranet.msBoard.model.dto.MsBoardDTO;
import com.ohgiraffers.intranet.msBoard.model.dto.MsMemberListDTO;
import com.ohgiraffers.intranet.msBoard.model.service.MsBoardService;

@Controller
@RequestMapping("/ms")
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
	
//	쪽지 보내기 Controller

	@GetMapping("/msinsert")
	public String MsboardInsert() {

		return "message/messageSend";
	}

	@PostMapping("/msinsert")
	public String MsboardInsert(@RequestParam List<MultipartFile> msfile,@ModelAttribute MsBoardDTO msBoardDTO, HttpServletRequest request, Model model) {
		
		msBoardDTO.getMsTitle();
		msBoardDTO.getContents();
		msBoardDTO.getSendName();
		msBoardDTO.getRecpName();
		msBoardDTO.getSendDate();
		msBoardDTO.getMemNum();
//		msBoardDTO.getRecpNum();
		
		int result = msBoardService.MsboardInsert(msBoardDTO);
		
		
		String multiFileDescription = request.getParameter("multiFileDescription");
		String root = request.getSession().getServletContext().getRealPath("file/msfile");
		String filePath = root + "\\uploadFiles";
		
		File mkdir = new File(filePath);
		if(!mkdir.exists()) {
			
			mkdir.mkdir();
		}
		
		List<Map<String, String>> files = new ArrayList<>();
		for(int i = 0; i <msfile.size(); i++) {
			
			String originFileName = msfile.get(i).getOriginalFilename();
			String ext = originFileName.substring(originFileName.lastIndexOf("."));
			String savedName = UUID.randomUUID().toString().replace("-","") + ext;
			
			Map<String, String> file = new HashMap<>();
			file.put("originFileName", originFileName);
			file.put("savedName", savedName);
			file.put("filePath", filePath);
			
			files.add(file);
			
		}
		try {
			
			for(int i = 0; i < msfile.size(); i++) {
				Map<String, String> file = files.get(i);
				msfile.get(i).transferTo(new File(filePath + "\\" + file.get("savedName")));
			}
		
			model.addAttribute("message", "파일 업로드 성공!");
		
		
		}catch(Exception e){
			for(int i = 0; i < msfile.size(); i++) {
				
				Map<String, String> file = files.get(i);
				new File(filePath + "\\" + file.get("savedName")).delete();
			}
			
			model.addAttribute("message", "파일 업로드 실패!");
		}	
		
		return "redirect:/ms/recp";
	}

	//쪽지 상세보기 컨트롤러
	
	@GetMapping("/msdetail")
	public String selectMsBoardDetail(HttpServletRequest request, Model model) {

		int msNo = Integer.valueOf(request.getParameter("msNo"));

		System.out.println("msNo : +  " + msNo);

		MsBoardDTO boardDetail = msBoardService.selectMsBoardDetail(msNo);

		model.addAttribute("boardDetail", boardDetail);

		return "/message/messageDetail";
	}	

	@GetMapping(value="getdeptList",produces = "application/json; charset-UTF-8")
	@ResponseBody
	public List<DepartmentDTO> getdeptList() throws Exception {
		
		List<DepartmentDTO> result = msBoardService.getdeptList();
		
		System.out.println(result);
		
		return result;
	}

	@GetMapping(value="getMemberList", produces = "application/json; charset-UTF-8")
	@ResponseBody
	private List<MsMemberListDTO> getMemberList(HttpServletRequest request) throws Exception {
		
		String dept_name = request.getParameter("data");
		
		List<MsMemberListDTO> result = msBoardService.getMemberList(dept_name);
	    
		
		return result;
	}
	
	
	
	

}
