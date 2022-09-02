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

	@Override
	public List<MsBoardDTO> selectMsRecpBoard(SelectCriteria selectCriteria) {

		List<MsBoardDTO> msBoardlist = msBoardMapper.selectMsRecpBoard(selectCriteria);

		return msBoardlist;
	}

	public int selectTotalCount(Map<String, String> searchMap) {

		int result = msBoardMapper.selectTotalCount(searchMap);

		return result;
	}

	@Override
	public int MsFileInsert(MsBoardDTO msBoard) {
		// TODO Auto-generated method stub
		return 0;
	}

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
