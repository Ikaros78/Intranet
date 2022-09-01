package com.ohgiraffers.intranet.msBoard;

import java.lang.ProcessBuilder.Redirect;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ohgiraffers.intranet.common.paging.Pagenation;
import com.ohgiraffers.intranet.common.paging.SelectCriteria;
import com.ohgiraffers.intranet.msBoard.model.dto.MsBoardDTO;
import com.ohgiraffers.intranet.msBoard.model.service.MsBoardService;

@Controller
@RequestMapping("/ms")
public class MsBoardController {

	public final MsBoardService msBoardService;
	
	@Autowired
	public MsBoardController(MsBoardService msBoardService) {
		
		this.msBoardService = msBoardService;
		
	}
	
	@GetMapping(value = "/recp")
	public ModelAndView selectMsRecpBoard(ModelAndView mv, HttpServletRequest request) {
		
		   String currentPage = request.getParameter("currentPage");
	        int pageNo = 1;

	        if(currentPage != null && !"".equals(currentPage)) {
	            pageNo = Integer.parseInt(currentPage);
	        }

	        String searchCondition = request.getParameter("searchCondition");
	        String searchValue = request.getParameter("searchValue");

	        Map<String, String> searchMap = new HashMap<>();
	        searchMap.put("searchCondition", searchCondition);
	        searchMap.put("searchValue", searchValue);

	        int totalCount = msBoardService.selectTotalCount(searchMap);

	        /* 한 페이지에 보여 줄 게시물 수 */
	        int limit = 10;		//얘도 파라미터로 전달받아도 된다.

	        /* 한 번에 보여질 페이징 버튼의 갯수 */
	        int buttonAmount = 5;

	        /* 페이징 처리를 위한 로직 호출 후 페이징 처리에 관한 정보를 담고 있는 인스턴스를 반환받는다. */
	        SelectCriteria selectCriteria = null;

	        if(searchCondition != null && !"".equals(searchCondition)) {
	            selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, searchCondition, searchValue);
	        } else {
	            selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
	        }

		
		List<MsBoardDTO> boardList = msBoardService.selectMsRecpBoard(selectCriteria);
	
		mv.addObject("boardList", boardList);
		mv.addObject("selectCriteria", selectCriteria);
		mv.setViewName("message/messageRecpBox");
		
		return mv;
	}
	
	
	@GetMapping("/msinsert")
	public String MsboardInsert() {
		
		return "message/messageSend";
	}
	
	public String MsboardInsert(@ModelAttribute MsBoardDTO msboardDTO, RedirectAttributes rttr) {
		
//		msBoardService.MsboardInsert(msboardDTO);
		
		return "";
	}
	

	
}
