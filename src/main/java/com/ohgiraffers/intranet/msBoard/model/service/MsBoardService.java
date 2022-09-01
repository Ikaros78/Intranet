package com.ohgiraffers.intranet.msBoard.model.service;

import java.util.List;
import java.util.Map;

import com.ohgiraffers.intranet.common.paging.SelectCriteria;
import com.ohgiraffers.intranet.msBoard.model.dto.MsBoardDTO;

public interface MsBoardService {

	List<MsBoardDTO> selectMsRecpBoard(SelectCriteria selectCriteria);

	int selectTotalCount(Map<String, String> searchMap);

	int MsFileInsert(MsBoardDTO msBoard);
}
