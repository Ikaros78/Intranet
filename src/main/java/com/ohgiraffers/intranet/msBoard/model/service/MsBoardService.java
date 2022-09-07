package com.ohgiraffers.intranet.msBoard.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ohgiraffers.intranet.common.paging.SelectCriteria;
import com.ohgiraffers.intranet.member.model.dto.DepartmentDTO;
import com.ohgiraffers.intranet.member.model.dto.MemberDTO;
import com.ohgiraffers.intranet.msBoard.model.dto.MsBoardDTO;
import com.ohgiraffers.intranet.msBoard.model.dto.MsMemberListDTO;

public interface MsBoardService {

	List<MsBoardDTO> selectMsRecpBoard(SelectCriteria selectCriteria);

	int selectTotalCount(Map<String, String> searchMap);

	int MsFileInsert(MsBoardDTO msBoard);

	MsBoardDTO selectMsBoardDetail(int msNo);

	List<MsBoardDTO> selectMsSendBoard(SelectCriteria selectCriteria);

	int MsboardInsert(MsBoardDTO msBoardDTO);

	int selectSendTotalCount(Map<String, String> searchMap);

	List<MsMemberListDTO> getMemberList(String dept_name) throws Exception;

	List<DepartmentDTO> getdeptList() throws Exception;

	
	
}