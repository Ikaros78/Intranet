package com.ohgiraffers.intranet.msBoard.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ohgiraffers.intranet.common.paging.SelectCriteria;
import com.ohgiraffers.intranet.msBoard.model.dto.MsBoardDTO;

@Mapper
public interface MsBoardMapper {

	List<MsBoardDTO> selectMsRecpBoard(SelectCriteria selectCriteria);

	List<MsBoardDTO> selectMsSendBoard(SelectCriteria selectCriteria);

	int MsFileInsert(MsBoardDTO msBoard);

	int selectTotalCount(Map<String, String> searchMap);

	MsBoardDTO selectMsBoardDetail(int msNo);

	int YNChangeMsBoard(int msNo);


	
	
}
