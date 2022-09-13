package com.ohgiraffers.intranet.notice.model.service;

import com.ohgiraffers.intranet.common.paging.SelectCriteria;
import com.ohgiraffers.intranet.notice.model.dto.*;

import java.util.List;
import java.util.Map;

public interface NoticeService {

    /* 공지사항 등록을 위한 메소드 */
    int noticeRegist(NoticeDTO notice);

    /* 공지사항 파일 등록을 위한 메소드 */
    int noticeFileInsert(NoticeFileDTO noticeFile);

    /* 공지사항 게시글 전체 갯수 조회용 메소드 */
    int selectTotalCount(Map<String, String> searchMap);

    /* 공지사항 검색을 위한 메소드 */
    List<NoticeDTO> selectNoticeList(SelectCriteria selectCriteria);

    /* 공지사항 상세페이지 조회용 메소드 */
    NoticeDTO selectNoticeDetail(int no);

    /* 공지사항 수정용 메소드 */
    int noticeUpdate(NoticeDTO notice);

    /* 공지사항 파일 삭제용 메소드 */
    int noticeFileDelete(int no);

    /* 공지사항 파일 다시 넣기 메소드 */
    void noticeFileUpdate(NoticeFileDTO noticeFile);

    /* 공지사항 삭제용 메소드 */
    void noticeDelete(NoticeDTO notice);

    /* 사내소식 등록용 메소드 */
    int newsRegist(NewsDTO news);

    /* 사내소식 파일 등록용 메소드 */
    int newsFileInsert(NewsFileDTO newsFile);

    /* 사내소식 전체 게시글 수 조회용 메소드 */
    int selectNewsTotalCount(Map<String, String> searchMap);

    /* 사내소식 검색 게시글 조회용 메소드 */
    List<NewsDTO> selectNewsList(SelectCriteria selectCriteria);

    /* 사내소식 상세보기용 메소드 */
    NewsDTO selectNewsDetail(int no);

    /* 사내소식 수정용 메소드 */
    int newsUpdate(NewsDTO news);

    /* 사내 소식 파일 삭제용 메소드 */
    int newsFileDelete(int no);

    /* 사내 소식 파일 수정용 메소드 */
    void newsFileUpdate(NewsFileDTO newsFile);

    /* 사내소식 삭제용 메소드*/
    void newsDelete(NewsDTO news);

    /* 갤러리 게시판 등록용 메소드 */
    void galleryRegist(GalleryDTO gallery);

    /* 갤러리 전체 게시글 수 조회 */
    int selectGalleryTotalCount(Map<String, String> searchMap);

    /* 갤러리 검색 게시글 조회용 메소드 */
    List<GalleryDTO> selectGalleryList(SelectCriteria selectCriteria);

    /* 갤러리 상세조회용 메소드(게시글)*/
    GalleryDTO selectGalleryDetail(int no);

    /* 갤러리 상세조회용 메소드(파일)*/
    List<GalleryFileDTO> selectGalleryFile(int no);

    /* 갤러리 업데이트용 메소드 */
    void galleryUpdate(GalleryDTO gallery);

    /* 갤러리 삭제용 메소드 */
    void galleryDelete(GalleryDTO gallery);
}
