package com.ohgiraffers.intranet.msBoard.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ohgiraffers.intranet.common.paging.SelectCriteria;
import com.ohgiraffers.intranet.msBoard.model.dao.MsBoardMapper;
import com.ohgiraffers.intranet.msBoard.model.dto.MsBoardDTO;

@Service
public class MsBoardServiceImpl implements MsBoardService {

	private MsBoardMapper msBoardMapper;

	@Autowired
	private MsBoardServiceImpl(MsBoardMapper msBoardMapper) {

		this.msBoardMapper = msBoardMapper;
	}

	
//	받은 쪽지함 조회용 메소드 
	
	@Override
	public List<MsBoardDTO> selectMsRecpBoard(SelectCriteria selectCriteria) {

		List<MsBoardDTO> msBoardlist = msBoardMapper.selectMsRecpBoard(selectCriteria);

		return msBoardlist;
	}

	
	// 보낸 쪽지함 글 갯수 확인용 메소드
	public int selectTotalCount(Map<String, String> searchMap) {

		int result = msBoardMapper.selectTotalCount(searchMap);

		return result;
	}

	
	public int selectSendTotalCount(Map<String, String> searchMap) {
		
		int result = msBoardMapper.selectSendTotalCount(searchMap);
		
		return result;
	}
	
	
	@Override
	public List<MsBoardDTO> selectMsSendBoard(SelectCriteria selectCriteria) {

		List<MsBoardDTO> msBoardlist = msBoardMapper.selectMsSendBoard(selectCriteria);

		return msBoardlist;
	}
	
	
	// 쪽지함 보내기용 메소드
	
	@Override
	public int MsboardInsert(MsBoardDTO msBoardDTO){
		
		int result = msBoardMapper.MsboardInsert(msBoardDTO);
		
		return result;
	}

	public int MsFileInsert(MsBoardDTO msBoard) {
		
		int result = msBoardMapper.MsFileInsert(msBoard);
		
		return result;
	} 
	
	//  보낸 쪽지함 상세 페이지 조회용 메소드
	@Override
	public MsBoardDTO selectMsBoardDetail(int msNo) {

		MsBoardDTO msDetail = null;

		int result = msBoardMapper.YNChangeMsBoard(msNo);

		if (result > 0) {

			msDetail = msBoardMapper.selectMsBoardDetail(msNo);
		}

		return msDetail;

	}

}
