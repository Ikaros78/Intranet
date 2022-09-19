package com.ohgiraffers.intranet.fileboard.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ohgiraffers.intranet.common.paging.Pagenation;
import com.ohgiraffers.intranet.common.paging.SelectCriteria;
import com.ohgiraffers.intranet.fileboard.model.dto.FileBoardDTO;
import com.ohgiraffers.intranet.fileboard.model.dto.FileFileDTO;
import com.ohgiraffers.intranet.fileboard.model.service.FileService;
import com.ohgiraffers.intranet.notice.model.dto.NoticeDTO;
import com.ohgiraffers.intranet.notice.model.dto.NoticeFileDTO;
import com.ohgiraffers.intranet.notice.model.service.NoticeService;

@Controller
@RequestMapping(value = { "/file/*" })
public class FileController {

	private final FileService fileService;

	@Autowired
	public FileController(FileService fileService) {

		this.fileService = fileService;
	}

	@GetMapping("/fileList")
	public ModelAndView fileBoardList(HttpServletRequest request, ModelAndView mv) {

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

		int totalCount = fileService.selectTotalCount(searchMap);

		int limit = 10;
		int buttonAmount = 5;

		SelectCriteria selectCriteria = null;

		if (searchCondition != null && !"".equals(searchCondition)) {

			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, searchCondition,
					searchValue);
		} else {

			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
		}

		List<FileBoardDTO> fileList = fileService.fileBoardList(selectCriteria);

		mv.addObject("fileList", fileList);
		mv.addObject("selectCriteria", selectCriteria);
		mv.setViewName("fileBoard/fileList");

		return mv;
	}

	@GetMapping("/fileRegist")
	public String fileRegistPage() {

		return "fileBoard/fileRegist";
	}

	@PostMapping("/fileRegist")
	public String fileBoardRegist(@ModelAttribute FileBoardDTO fileboard, HttpServletRequest request,
			@RequestParam(name = "originName", required = false) MultipartFile originName)
			throws FileNotFoundException {

		// notice insert
		int registResult = fileService.fileBoardRegist(fileboard);

		FileFileDTO fileFile = new FileFileDTO();

		String filePath = ResourceUtils.getURL("src/main/resources").getPath() + "upload";

		String fileUploadDirectory = filePath + "/fileBoardFile";

		File mkdir = new File(fileUploadDirectory);

		if (!mkdir.exists()) {
			mkdir.mkdirs();
		}

		String originFileName = "";
		String ext = "";
		String saveName = "";

		if (originName.getSize() > 0) { // 파일 첨부한거 있으면
			// 파일명 변경하고
			originFileName = originName.getOriginalFilename();
			ext = originFileName.substring(originFileName.lastIndexOf("."));
			saveName = UUID.randomUUID().toString().replace("-", "") + ext;

			fileFile.setFfOriginName(originFileName);
			fileFile.setFfSaveName(saveName);
			fileFile.setFfSavePath(fileUploadDirectory);

			// file insert
			int fileResult = fileService.fileFileInsert(fileFile);

			try {
				originName.transferTo(new File(fileUploadDirectory + "//" + saveName));

			} catch (IOException e) {

				e.printStackTrace();
				new File(fileUploadDirectory + "//" + saveName).delete();
			}

		}

		return "redirect:/file/fileList";

	}

	@GetMapping("/fileDetail")
	public String fileBoardDetail(HttpServletRequest request, Model model) {

		int fbNo = Integer.parseInt(request.getParameter("fbNo"));

		System.out.println("nonononono" + fbNo);

		FileBoardDTO fileDetail = fileService.fileBoardDetail(fbNo);
		model.addAttribute("Detail", fileDetail);

		return "fileBoard/fileDetail";
	}

	@GetMapping("/fileUpdate")
	public String fileBoardUpdatepage(HttpServletRequest request, Model model) {

		int fbNo = Integer.parseInt(request.getParameter("no"));

		FileBoardDTO fileUpdate = fileService.fileBoardDetail(fbNo);
		model.addAttribute("fileUpdate", fileUpdate);

		return "fileBoard/fileUpdate";
	}

	@PostMapping("/fileUpdate")
	public String fileBoardUpdate(@ModelAttribute FileBoardDTO fileBoard, RedirectAttributes rttr,
			@RequestParam(name = "originName", required = false) MultipartFile originName)
			throws FileNotFoundException {

		FileFileDTO fileFile = new FileFileDTO();

		String filePath = ResourceUtils.getURL("src/main/resources").getPath() + "upload";

		String fileUploadDirectory = filePath + "/noticeFile";

		File mkdir = new File(fileUploadDirectory);

		if (!mkdir.exists()) {
			mkdir.mkdirs();
		}

		String originFileName = "";
		String ext = "";
		String saveName = "";

		if (originName.getSize() > 0) { // 파일 첨부한거 있으면
			// 파일명 변경하고
			originFileName = originName.getOriginalFilename();
			ext = originFileName.substring(originFileName.lastIndexOf("."));
			saveName = UUID.randomUUID().toString().replace("-", "") + ext;

			int result = fileService.fileBoardUpdate(fileBoard);

			if (result > 0) {

				if (fileBoard.getFile() != null) {

					int result2 = fileService.fileFileDelete(fileBoard.getFbNo());

					if (result2 > 0) {

						fileFile.setFbNo(fileBoard.getFbNo());
						fileFile.setFfOriginName(originFileName);
						fileFile.setFfSaveName(saveName);
						fileFile.setFfSavePath(fileUploadDirectory);

						fileService.fileFileUpdate(fileFile);
					}

				} else {

					fileFile.setFbNo(fileBoard.getFbNo());
					fileFile.setFfOriginName(originFileName);
					fileFile.setFfSaveName(saveName);
					fileFile.setFfSavePath(fileUploadDirectory);

					fileService.fileFileUpdate(fileFile);
				}

			}

			// file insert

			try {
				originName.transferTo(new File(fileUploadDirectory + "//" + saveName));
			} catch (IOException e) {

				e.printStackTrace();
				new File(fileUploadDirectory + "//" + saveName).delete();
			}

		}

		rttr.addFlashAttribute("message", "수정이 완료되었습니다");

		return "redirect:/file/fileList";
	}

	@GetMapping("/fileDelete")
	public String fileBoardDelete(@ModelAttribute FileBoardDTO fileboard, HttpServletRequest request) {

		int no = Integer.parseInt(request.getParameter("no"));

		fileService.fileBoardDelete(fileboard);

		return "redirect:/file/fileList";
	}

}
