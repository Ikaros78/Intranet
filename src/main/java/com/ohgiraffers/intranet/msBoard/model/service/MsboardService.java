package com.ohgiraffers.intranet.msBoard.model.service;

import java.util.List;

import com.ohgiraffers.intranet.msBoard.model.dto.MsBoardDTO;

public interface MsboardService {

	List<MsBoardDTO> selectAllMsBoard();

	List<MsBoardDTO> selectSendMsBoard();

	List<MsBoardDTO> selectRecpMsBoard();

	

}
