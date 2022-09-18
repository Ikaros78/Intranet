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
    public FileBoardDTO fileBoardDetail(int no) {

    	FileBoardDTO fileBoard = null;

        int result = fileMapper.incresementFileBoardCount(no); //조회수 증가

        if(result > 0){
        	
        	fileBoard = fileMapper.fileBoardDetail(no);
        }
        return fileBoard;
    }
//
//    /* 공지사항 수정용 메소드 */
//    @Override
//    @Transactional
//    public int noticeUpdate(NoticeDTO notice) {
//        return noticeMapper.noticeUpdate(notice);
//    }
//
//    /* 공지사항 파일 삭제용 메소드 */
//    @Override
//    @Transactional
//    public int noticeFileDelete(int no) {
//        return noticeMapper.deleteFile(no);
//    }
//
//    /* 공지사항 파일 다시 넣기 메소드 */
//    @Override
//    @Transactional
//    public void noticeFileUpdate(NoticeFileDTO noticeFile) {
//        noticeMapper.noticeFileUpdate(noticeFile);
//    }
//
//    /* 공지사항 삭제용 메소드 */
//    @Override
//    @Transactional
//    public void noticeDelete(NoticeDTO notice) {
//        int result1 = noticeMapper.deleteFile(notice.getNo());
//        log.info("파일삭제 안되나.." + result1);
//        int result2 = noticeMapper.noticeDelete(notice.getNo());
//    }
	
	
//	@Override
//	public void fileBoardDelete(FileBoardDTO fileboard) {
////		int result1 = fileMapper.fileFileInsert();
//				
//		int result2 = fileMapper.fileBoardDelete(fileboard.getFbNo());		
//				
//	}
//
//	@Override
//	public FileBoardDTO fileBoardDetail(int no) {
//		return null;
//	}
//
//	@Override
//	public int fileBoardRegist(FileBoardDTO fileboard) {
//		return 0;
//	}
    
}
