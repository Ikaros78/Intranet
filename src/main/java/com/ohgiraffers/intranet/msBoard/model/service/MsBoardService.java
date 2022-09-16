package com.ohgiraffers.intranet.msBoard.model.service;

import java.util.List;
import java.util.Map;

import com.ohgiraffers.intranet.common.paging.SelectCriteria;
import com.ohgiraffers.intranet.member.model.dto.DepartmentDTO;
import com.ohgiraffers.intranet.msBoard.model.dto.MsBoardDTO;
import com.ohgiraffers.intranet.msBoard.model.dto.MsFileDTO;
import com.ohgiraffers.intranet.msBoard.model.dto.MsMemberListDTO;

public interface MsBoardService {

	List<MsBoardDTO> selectMsRecpBoard(SelectCriteria selectCriteria);

	int selectTotalCount(Map<String, String> searchMap);

	List<MsBoardDTO> selectMsAllRecpBoard(SelectCriteria selectCriteria);
	
	int selectAllRecpTotalCount(Map<String, String> searchMap);

	List<MsBoardDTO> selectMsSendBoard(SelectCriteria selectCriteria);
	
	int selectSendTotalCount(Map<String, String> searchMap);
	
	int MsFileInsert(MsFileDTO msFileDTO);

	MsBoardDTO selectMsBoardDetail(int msNo);

	int MsboardInsert(MsBoardDTO msBoardDTO);

	List<MsMemberListDTO> getMemberListt(String data);

	List<DepartmentDTO> getdeptList() throws Exception;

	List<MsMemberListDTO> getMemberListSecond(String name) throws Exception;

	void recpNameUpdate(MsBoardDTO msBoardDTO);



//	void recpYNMsBoard(List<MsBoardDTO> msBoard);
//
//	void recpBoardDelete(List<MsBoardDTO> msBoard);

//	void recpBoardDelete(MsBoardDTO msBoard);
//
//	void sendBoardDelete(MsBoardDTO msBoard);
////
//	int sendBoardDelete(int msNo);
//
//	int recpBoardDelete(int msNo);
//	
//	int recpYNMsBoard(int msNo);
//
//	int sendYNMsBoard(int msBoard);


	
}