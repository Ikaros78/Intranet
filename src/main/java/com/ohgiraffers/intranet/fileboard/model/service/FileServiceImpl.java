package com.ohgiraffers.intranet.fileboard.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ohgiraffers.intranet.common.paging.SelectCriteria;
import com.ohgiraffers.intranet.fileboard.model.dao.FileMapper;
import com.ohgiraffers.intranet.fileboard.model.dto.FileBoardDTO;
import com.ohgiraffers.intranet.fileboard.model.dto.FileFileDTO;
import com.ohgiraffers.intranet.notice.model.dto.NoticeDTO;
import com.ohgiraffers.intranet.notice.model.dto.NoticeFileDTO;

@Service("fileService")
public class FileServiceImpl implements FileService  {

    private FileMapper fileMapper;

    @Autowired
    public FileServiceImpl(FileMapper fileMapper){
        this.fileMapper = fileMapper;
    }

	@Override
	public List<FileBoardDTO> fileBoardList(SelectCriteria selectCriteria) {
		
		List<FileBoardDTO> fileboardList = fileMapper.fileBoardList(selectCriteria);
		
		return fileboardList;
	}

	@Override
	public int selectTotalCount(Map<String, String> searchMap) {
		
		int result = fileMapper.selectTotalCount(searchMap);
		
		return result;
	}

	@Override
	public int fileFileInsert(FileFileDTO fileFile) {
		
		return fileMapper.fileFileInsert(fileFile);
	}

    /* 파일 등록을 위한 메소드 */
    @Override
    @Transactional
    public int fileBoardRegist(FileBoardDTO fileBoard) {
    	
        return fileMapper.fileBoardRegist(fileBoard);
    }
		
	
    /* 상세페이지 조회용 메소드 */
    @Override
    public FileBoardDTO fileBoardDetail(int fbNo) {

    	FileBoardDTO fileBoard = null;

        int result = fileMapper.incresementFileBoardCount(fbNo); //조회수 증가

        if(result > 0){
        	
        	fileBoard = fileMapper.fileBoardDetail(fbNo);
        }
        return fileBoard;
    }

    @Override
    @Transactional
    public int fileBoardUpdate(FileBoardDTO fileBoard) {
    	
        return fileMapper.fileBoardUpdate(fileBoard);
    }

    @Override
    @Transactional
    public void fileFileUpdate(FileFileDTO fileFile) {
    	
    	fileMapper.fileFileUpdate(fileFile);
    }
    
    @Override
    @Transactional
    public int fileFileDelete(int fbNo) {
    	
        return fileMapper.fileFileDelete(fbNo);
    }


    @Override
    @Transactional
    public void fileBoardDelete(FileBoardDTO fileBoard) {
        int result1 = fileMapper.fileFileDelete(fileBoard.getFbNo());
        int result2 = fileMapper.fileBoardDelete(fileBoard.getFbNo());
    }
}
