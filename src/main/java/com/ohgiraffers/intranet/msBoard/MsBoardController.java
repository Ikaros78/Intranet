package com.ohgiraffers.intranet.msBoard;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ohgiraffers.intranet.msBoard.model.dto.MsBoardDTO;
import com.ohgiraffers.intranet.msBoard.model.service.MsboardService;

@Controller
@RequestMapping("/")
public class MsBoardController {

	public final MsboardService msBoardService;
	
	@Autowired
	public MsBoardController(MsboardService msBoardService) {
		
		this.msBoardService = msBoardService;
		
	}
	
	@GetMapping("/all/msList")
	public ModelAndView selectAllMsBoard(ModelAndView mv) {
		
		List<MsBoardDTO> boardList = msBoardService.selectAllMsBoard();
	
		mv.addObject("boardList", boardList);
		mv.setViewName("");
		
		return mv;
	}
	
	@GetMapping("/send/msList")
	public ModelAndView selectSendMsBoard(ModelAndView mv) {
		
		List<MsBoardDTO> boardList = msBoardService.selectSendMsBoard();
	
		mv.addObject("boardList", boardList);
		mv.setViewName("");
		
		return mv;
	}
	
	@GetMapping("/recp/msList")
	public ModelAndView selectRecpMsBoard(ModelAndView mv) {
		
		List<MsBoardDTO> boardList = msBoardService.selectRecpMsBoard();
		
		mv.addObject("boardList",boardList);
		mv.setViewName("");
		
		return mv;
	}
	@GetMapping("/msInsert")
	public void Msboardsend() {}
	

	
}
