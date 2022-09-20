package com.ohgiraffers.intranet.fileboard.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ohgiraffers.intranet.common.paging.SelectCriteria;
import com.ohgiraffers.intranet.fileboard.model.dto.FileBoardDTO;
import com.ohgiraffers.intranet.fileboard.model.dto.FileFileDTO;

@Mapper
public interface FileMapper {

	List<FileBoardDTO> fileBoardList(SelectCriteria selectCriteria);

	int selectTotalCount(Map<String, String> searchMap);

	int fileFileInsert(FileFileDTO fileFile);

	int fileBoardRegist(FileBoardDTO fileBoard);

	FileBoardDTO fileBoardDetail(int fbNo);

	int incresementFileBoardCount(int no);

	int fileBoardUpdate(FileBoardDTO fileBoard);

	int fileFileDelete(int fbNo);

	int fileFileUpdate(FileFileDTO fileFile);

	int fileBoardDelete(int fbNo);

}
