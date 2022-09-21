package com.ohgiraffers.intranet.msBoard.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ohgiraffers.intranet.common.paging.SelectCriteria;
import com.ohgiraffers.intranet.member.model.dto.DepartmentDTO;
import com.ohgiraffers.intranet.msBoard.model.dto.MsBoardDTO;
import com.ohgiraffers.intranet.msBoard.model.dto.MsFileDTO;
import com.ohgiraffers.intranet.msBoard.model.dto.MsMemberListDTO;

@Mapper
public interface MsBoardMapper {

	List<MsBoardDTO> selectMsRecpBoard(Map<String, Object> searchList);

	int selectTotalCount(Map<String, Object> searchMap);

	List<MsBoardDTO> selectMsSendBoard(Map<String, Object> searchList);

	int selectSendTotalCount(Map<String, Object> searchMap);

	List<MsBoardDTO> selectMsAllRecpBoard(Map<String, Object> searchList);

	int selectAllRecpTotalCount(Map<String, Object> searchMap);
	
//////////////////////////////////////////	
	

	int MsboardInsert(MsBoardDTO msBoardDTO,MsFileDTO msFileDTO);

	int MsFileInsert(MsFileDTO msFileDTO);

	int YNChangeMsBoard(int msNo);

	List<MsMemberListDTO> getMemberListt(String data);

	List<DepartmentDTO> getdeptList() throws Exception;

	List<MsMemberListDTO> getMemberListSecond(String name) throws Exception;

	MsBoardDTO selectMsBoardDetail(int msNo);

	List<MsFileDTO> selectMsBoardFileDetail(int msNo);

	void recpNameUpdate(MsBoardDTO msBoardDTO);

	int MsboardInsert(MsBoardDTO msBoardDTO);
	
	void recpYNMsBoard(int i);

	int recpBoardDelete(int msNo);

	void msFileDelete(int msNo);

	void sendYNMsBoard(int msNo);

	int sendBoardDelete(int msNo);

	CharSequence count_receive_note(String payload);

	MsBoardDTO selectSendBoardDetail(int msNo);

	void YNSendChangeMsBoard(int msNo);



}
