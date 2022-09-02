package com.ohgiraffers.intranet.notice.model.service;

import com.ohgiraffers.intranet.common.paging.SelectCriteria;
import com.ohgiraffers.intranet.notice.model.dao.NoticeMapper;
import com.ohgiraffers.intranet.notice.model.dto.NoticeDTO;
import com.ohgiraffers.intranet.notice.model.dto.NoticeFileDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("noticeService")
public class NoticeService {

    private final NoticeMapper noticeMapper;

    public NoticeService(NoticeMapper noticeMapper){

        this.noticeMapper = noticeMapper;
    }

    /* 공지사항 등록을 위한 메소드 */
    public int noticeRegist(NoticeDTO notice) {

        return noticeMapper.noticeRegist(notice);
    }

    /* 공지사항 파일 등록을 위한 메소드 */
    public int noticeFileInsert(NoticeFileDTO noticeFile) {

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
}
