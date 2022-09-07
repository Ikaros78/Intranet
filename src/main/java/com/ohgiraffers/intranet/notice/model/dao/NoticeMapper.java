package com.ohgiraffers.intranet.notice.model.dao;

import com.ohgiraffers.intranet.common.paging.SelectCriteria;
import com.ohgiraffers.intranet.notice.model.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface NoticeMapper {


    int noticeRegist(NoticeDTO notice);

    int fileRegist(NoticeFileDTO noticeFile);

    int noticeFileUpdate(NoticeFileDTO noticeFile);

    int selectTotalCount(Map<String, String> searchMap);

    List<NoticeDTO> selectNoticeList(SelectCriteria selectCriteria);

    int incresementNoticeCount(int no);

    NoticeDTO selectNoticeDetail(int no);

    int deleteFile(int no);

    int noticeUpdate(NoticeDTO notice);

    int noticeDelete(int no);

    int newsRegist(NewsDTO news);

    int newsFileRegist(NewsFileDTO newsFile);

    int selectNewsTotalCount(Map<String, String> searchMap);

    List<NewsDTO> selectNewsList(SelectCriteria selectCriteria);

    int incresementNewsCount(int no);

    NewsDTO selectNewsDetail(int no);

    int newsUpdate(NewsDTO news);

    int deleteNewsFile(int no);

    int newsDelete(int no);

    void newsFileUpdate(NewsFileDTO newsFile);

    int galleryRegist(GalleryDTO gallery);

    int galleryFileRegist(GalleryFileDTO galleryFileDTO);

    List<GalleryDTO> selectAllGalleryList();

    int selectGalleryTotalCount(Map<String, String> searchMap);

    List<GalleryDTO> selectGalleryList(SelectCriteria selectCriteria);
}
