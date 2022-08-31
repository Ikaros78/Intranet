package com.ohgiraffers.intranet.msBoard.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ohgiraffers.intranet.msBoard.model.dto.MsBoardDTO;

@Mapper
public interface MsBoardMapper {

	List<MsBoardDTO> selectSendMsBoard();

	List<MsBoardDTO> selectRecpMsBoard();

	List<MsBoardDTO> selectAllMsBoard();
	
	
	
}
