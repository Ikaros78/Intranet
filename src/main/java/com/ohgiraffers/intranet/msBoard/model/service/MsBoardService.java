package com.ohgiraffers.intranet.msBoard.model.service;

import java.util.List;
import java.util.Map;

import com.ohgiraffers.intranet.common.paging.SelectCriteria;
import com.ohgiraffers.intranet.member.model.dto.DepartmentDTO;
import com.ohgiraffers.intranet.msBoard.model.dto.MsBoardDTO;
import com.ohgiraffers.intranet.msBoard.model.dto.MsFileDTO;
import com.ohgiraffers.intranet.msBoard.model.dto.MsMemberListDTO;

public interface MsBoardService {

	List<MsBoardDTO> selectMsRecpBoard(Map<String, Object> searchList);

	int selectTotalCount(Map<String, Object> searchMap);

	List<MsBoardDTO> selectMsAllRecpBoard(Map<String, Object> searchList);
	
	int selectAllRecpTotalCount(Map<String, Object> searchMap);

	List<MsBoardDTO> selectMsSendBoard(Map<String, Object> searchList);
	
	int selectSendTotalCount(Map<String, Object> searchMap);
	
	int MsFileInsert(MsFileDTO msFileDTO);

	MsBoardDTO selectMsBoardDetail(int msNo);

	int MsboardInsert(MsBoardDTO msBoardDTO);

	List<MsMemberListDTO> getMemberListt(String data);

	List<DepartmentDTO> getdeptList() throws Exception;

	List<MsMemberListDTO> getMemberListSecond(String name) throws Exception;

	void recpNameUpdate(MsBoardDTO msBoardDTO);

	void recpYNMsBoard(MsBoardDTO msboardDTO);

	void recpBoardDelete(MsBoardDTO msboardDTO);

	void sendYNMsBoard(MsBoardDTO msboardDTO);

	void sendBoardDelete(MsBoardDTO msboardDTO);

	
}