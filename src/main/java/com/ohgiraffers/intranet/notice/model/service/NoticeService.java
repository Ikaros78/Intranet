package com.ohgiraffers.intranet.notice.model.service;

import com.ohgiraffers.intranet.common.paging.SelectCriteria;
import com.ohgiraffers.intranet.notice.model.dao.NoticeMapper;
import com.ohgiraffers.intranet.notice.model.dto.NewsDTO;
import com.ohgiraffers.intranet.notice.model.dto.NewsFileDTO;
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

        return noticeMapper.noticeRegist(notice);
    }

    /* 공지사항 파일 등록을 위한 메소드 */
    @Transactional
    public int noticeFileInsert(NoticeFileDTO noticeFile) {

        return noticeMapper.fileRegist(noticeFile);
    }

    /* 공지사항 게시글 전체 갯수 조회용 메소드 */
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

        return noticeMapper.noticeUpdate(notice);
    }

    /* 공지사항 파일 다시 넣기 메소드 */
    public void noticeFileUpdate(NoticeFileDTO noticeFile) {

        noticeMapper.noticeFileUpdate(noticeFile);
    }

    /* 공지사항 파일 삭제용 메소드 */
    public int noticeFileDelete(int no) {

        return noticeMapper.deleteFile(no);
    }

    /* 공지사항 삭제용 메소드 */
    @Transactional
    public void noticeDelete(NoticeDTO notice) {

        int result1 = noticeMapper.deleteFile(notice.getNo());
        log.info("파일삭제 안되나.." + result1);
        int result2 = noticeMapper.noticeDelete(notice.getNo());
    }

    /* 사내소식 등록용 메소드 */
    @Transactional
    public int newsRegist(NewsDTO news) {

        return noticeMapper.newsRegist(news);
    }

    /* 사내소식 파일 등록용 메소드 */
    @Transactional
    public int newsFileInsert(NewsFileDTO newsFile) {

        return noticeMapper.newsFileRegist(newsFile);
    }



    /* 사내소식 전체 게시글 수 조회용 메소드 */
    public int selectNewsTotalCount(Map<String, String> searchMap) {

        int result = noticeMapper.selectNewsTotalCount(searchMap);

        return result;
    }

    /* 사내소식 검색 게시글 수 조회용 메소드 */
    public List<NewsDTO> selectNewsList(SelectCriteria selectCriteria) {

        List<NewsDTO> newsList = noticeMapper.selectNewsList(selectCriteria);

        return newsList;
    }

    /* 사내소식 상세보기용 메소드 */
    public NewsDTO selectNewsDetail(int no) {

        NewsDTO newsDetail = null;

        int result = noticeMapper.incresementNewsCount(no); //조회수 증가

        if(result > 0){
            newsDetail = noticeMapper.selectNewsDetail(no);
        }
        return newsDetail;
    }

    /* 사내소식 수정용 메소드 */
    @Transactional
    public int newsUpdate(NewsDTO news) {

        int result = noticeMapper.newsUpdate(news);

        // 파일이 존재할 경우 기존 파일 삭제 후 게시글 및 파일 등록
        if (news.getFile() != null) {
            log.info("기존 파일 존재함...");
            int deleteFileResult = noticeMapper.deleteFile(news.getFile().getNwNo());

            //삭제 완료되었을 경우
            if (deleteFileResult > 0) {
                NewsFileDTO newsFile = news.getFile();
                newsFile.setNwNo(news.getFile().getNwNo());
                //파일 다시 넣어줌
                int insertFileResult = noticeMapper.newsFileRegist(newsFile);
            }
        }
        return noticeMapper.newsUpdate(news);
    }

    /* 사내소식 삭제용 메소드*/
    @Transactional
    public void newsDelete(NewsDTO news) {

        int result1 = noticeMapper.deleteNewsFile(news.getNo());
        int result2 = noticeMapper.newsDelete(news.getNo());
    }



}
