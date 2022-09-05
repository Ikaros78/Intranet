package com.ohgiraffers.intranet.msBoard.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ohgiraffers.intranet.common.paging.SelectCriteria;
import com.ohgiraffers.intranet.msBoard.model.dto.MsBoardDTO;

@Mapper
public interface MsBoardMapper {

	List<MsBoardDTO> selectMsRecpBoard(SelectCriteria selectCriteria);

	int selectTotalCount(Map<String, String> searchMap);

	List<MsBoardDTO> selectMsSendBoard(SelectCriteria selectCriteria);

	int selectSendTotalCount(Map<String, String> searchMap);

	int MsFileInsert(MsBoardDTO msBoard);

	MsBoardDTO selectMsBoardDetail(int msNo);

	int YNChangeMsBoard(int msNo);

	int MsboardInsert(MsBoardDTO msBoardDTO);

}
