package com.ohgiraffers.intranet.fileboard.model.service;

import java.util.List;
import java.util.Map;

import com.ohgiraffers.intranet.common.paging.SelectCriteria;
import com.ohgiraffers.intranet.fileboard.model.dto.FileBoardDTO;
import com.ohgiraffers.intranet.fileboard.model.dto.FileFileDTO;

public interface FileService {

	List<FileBoardDTO> fileBoardList(SelectCriteria selectCriteria);

	int selectTotalCount(Map<String, String> searchMap);

	int fileFileInsert(FileFileDTO fileFile);

	int fileBoardRegist(FileBoardDTO fileboard);

	FileBoardDTO fileBoardDetail(int fbNo);
//
//	void fileBoardDelete(FileBoardDTO fileboard);


//	int fileBoardRegist(FileBoardDTO fileboard);


}
