package com.ohgiraffers.intranet.notice.model.service;

import com.ohgiraffers.intranet.common.paging.SelectCriteria;
import com.ohgiraffers.intranet.notice.model.dao.NoticeMapper;
import com.ohgiraffers.intranet.notice.model.dto.NewsDTO;
import com.ohgiraffers.intranet.notice.model.dto.NoticeDTO;
import com.ohgiraffers.intranet.notice.model.dto.NoticeFileDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service("noticeService")
public class NoticeService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final NoticeMapper noticeMapper;

    public NoticeService(NoticeMapper noticeMapper){

        this.noticeMapper = noticeMapper;
    }

    /* 공지사항 등록을 위한 메소드 */
    @Transactional
    public int noticeRegist(NoticeDTO notice) {

        int noticeResult = noticeMapper.noticeRegist(notice);
        log.info("notice값 들어갔나 확인 : " + noticeResult);

        return noticeResult;
    }

    /* 공지사항 파일 등록을 위한 메소드 */
    @Transactional
    public int noticeFileInsert(NoticeFileDTO noticeFile) {

        NoticeDTO notice = new NoticeDTO();
        noticeFile.setNtNo(notice.getNo());
        log.info("noticeFile setNtNo값 : " + noticeFile.getNtNo());

        return noticeMapper.fileRegist(noticeFile);
    }

    /* 게시글 전체 갯수 조회용 메소드 */
    public int selectTotalCount(Map<String, String> searchMap) {

        int result = noticeMapper.selectTotalCount(searchMap);

        return result;
    }

    /* 공지사항 검색을 위한 메소드 */
    public List<NoticeDTO> selectNoticeList(SelectCriteria selectCriteria) {

        List<NoticeDTO> noticeList = noticeMapper.selectNoticeList(selectCriteria);

        return noticeList;
    }


    /* 공지사항 상세페이지 조회용 메소드 */
    public NoticeDTO selectNoticeDetail(int no) {

        NoticeDTO noticeDetail = null;

        int result = noticeMapper.incresementNoticeCount(no); //조회수 증가

        if(result > 0){
            noticeDetail = noticeMapper.selectNoticeDetail(no);
        }
        return noticeDetail;
    }

    /* 공지사항 수정용 메소드 */
    @Transactional
    public int noticeUpdate(NoticeDTO notice) {

        int result = noticeMapper.noticeUpdate(notice);

        // 파일이 존재할 경우 기존 파일 삭제 후 게시글 및 파일 등록
        if (notice.getFile() != null) {
            log.info("기존 파일 존재함...");
            int deleteFileResult = noticeMapper.deleteFile(notice.getFile().getNtNo());

            //삭제 완료되었을 경우
            if (deleteFileResult > 0) {
                NoticeFileDTO noticeFile = notice.getFile();
                noticeFile.setNtNo(notice.getFile().getNtNo());
                //파일 다시 넣어줌
                int insertFileResult = noticeMapper.fileRegist(noticeFile);
            }
        }
        return noticeMapper.noticeUpdate(notice);
    }

    /* 공지사항 삭제용 메소드 */
    @Transactional
    public void noticeDelete(NoticeDTO notice) {

        int result1 = noticeMapper.deleteFile(notice.getNo());
        log.info("파일삭제 안되나.." + result1);
        int result2 = noticeMapper.noticeDelete(notice.getNo());
    }

    @Transactional
    public int newsRegist(NewsDTO news) {

        return noticeMapper.newsRegist(news);
    }
}
