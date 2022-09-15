package com.ohgiraffers.intranet.msBoard.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ohgiraffers.intranet.common.paging.SelectCriteria;
import com.ohgiraffers.intranet.member.model.dto.DepartmentDTO;
import com.ohgiraffers.intranet.msBoard.model.dao.MsBoardMapper;
import com.ohgiraffers.intranet.msBoard.model.dto.MsBoardDTO;
import com.ohgiraffers.intranet.msBoard.model.dto.MsFileDTO;
import com.ohgiraffers.intranet.msBoard.model.dto.MsMemberListDTO;

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

	// 받은 쪽지함 글 갯수 확인용 메소드
	public int selectTotalCount(Map<String, String> searchMap) {

		int result = msBoardMapper.selectTotalCount(searchMap);

		return result;
	}

//	보낸 쪽지함 조회용 메소드
	@Override
	public List<MsBoardDTO> selectMsSendBoard(SelectCriteria selectCriteria) {

		List<MsBoardDTO> msBoardlist = msBoardMapper.selectMsSendBoard(selectCriteria);

		return msBoardlist;
	}

	// 보낸 쪽지함 글 갯수 확인용 메소드
	@Override
	public int selectSendTotalCount(Map<String, String> searchMap) {

		int result = msBoardMapper.selectSendTotalCount(searchMap);

		return result;
	}

//	받은 쪽지함 전체 조회용 메소드
	@Override
	public List<MsBoardDTO> selectMsAllRecpBoard(SelectCriteria selectCriteria) {

		List<MsBoardDTO> msBoardlist = msBoardMapper.selectMsAllRecpBoard(selectCriteria);

		return msBoardlist;
	}

// 받은 쪽지함 전체 글 갯수 확인용 메소드
	@Override
	public int selectAllRecpTotalCount(Map<String, String> searchMap) {

		int result = msBoardMapper.selectAllRecpTotalCount(searchMap);

		return result;
	}

	// 쪽지함 보내기용 메소드


	public int MsboardInsert(MsBoardDTO msBoardDTO) {

		int result = msBoardMapper.MsboardInsert(msBoardDTO);

		return result;
	}


	public int MsFileInsert(MsFileDTO msFileDTO) {

		int result = msBoardMapper.MsFileInsert(msFileDTO);

		return result;
	}

	// 보낸 쪽지함 상세 페이지 조회용 메소드
	@Override
	public MsBoardDTO selectMsBoardDetail(int msNo) {
		
		MsBoardDTO msDetail = msBoardMapper.selectMsBoardDetail(msNo);
		// 지금 파일 이름이 다르니까 
		
		System.out.println("msDetail : " + msDetail);
		
		if (msDetail.equals(msDetail)) {

			 msBoardMapper.YNChangeMsBoard(msNo);
		}

		return msDetail;

	}

	// ajax 용 비동기 1
	@Override
	public List<MsMemberListDTO> getMemberList(String dept_name) throws Exception {

		List<MsMemberListDTO> memberList = msBoardMapper.getMemberList(dept_name);

		return memberList;

	}

	// ajax 용 비동기 3
	@Override
	public List<MsMemberListDTO> getMemberListSecond(String name) throws Exception {

		List<MsMemberListDTO> memberList = msBoardMapper.getMemberListSecond(name);

		return memberList;

	}

	// ajax 용 비동기 2
	@Override
	public List<DepartmentDTO> getdeptList() throws Exception {

		List<DepartmentDTO> deptList = msBoardMapper.getdeptList();

		return deptList;
	}

	// 삭제용 메소드

//	public int recpBoardDelete(int msNo) {
//
//		msBoardMapper.recpYNMsBoard(msNo);
//
//		int result = msBoardMapper.recpBoardDelete(msNo);
//
//		if (result > 0) {
//
//			msBoardMapper.msFileDelete(msNo);
//			
//		}
//
//		return result;
//	}
//	
//	public int sendBoardDelete(int msNo) {
//
//		msBoardMapper.sendYNMsBoard(msNo);
//
//		int result = msBoardMapper.sendBoardDelete(msNo);
//
//		if (result > 0) {
//
//			msBoardMapper.msFileDelete(msNo);
//		}
//
//		return result;
//	}
	
	
//	//update recp 
//	@Transactional
//	public void recpYNMsBoard(MsBoardDTO msBoard) {
//		
//		 msBoardMapper.recpYNMsBoard(msBoard.getMsNo());
//	}
//	
//	//update send
//	@Transactional
//	public void sendYNMsBoard(MsBoardDTO msBoard) {
//		
//		 msBoardMapper.recpYNMsBoard(msBoard.getMsNo());
//	}
//	
//	
//	@Transactional
//	public void recpBoardDelete(MsBoardDTO msBoard) {
//
//		int result = msBoardMapper.sendBoardDelete(msBoard.getMsNo());
//
//		if (result > 0) {
//
//			msBoardMapper.msFileDelete(msBoard.getMsNo());
//		}
//
//		
//	}
//
//	@Transactional
//	public void sendBoardDelete(MsBoardDTO msBoard) {
//
//		int result = msBoardMapper.sendBoardDelete(msBoard.getMsNo());
//
//		if (result > 0) {
//
//			msBoardMapper.msFileDelete(msBoard.getMsNo());
//		}
//		
//	}


}
