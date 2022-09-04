package com.ohgiraffers.intranet.notice.model.dao;

import com.ohgiraffers.intranet.common.paging.SelectCriteria;
import com.ohgiraffers.intranet.notice.model.dto.NewsDTO;
import com.ohgiraffers.intranet.notice.model.dto.NoticeDTO;
import com.ohgiraffers.intranet.notice.model.dto.NoticeFileDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface NoticeMapper {


    int noticeRegist(NoticeDTO notice);

    int fileRegist(NoticeFileDTO noticeFile);

    int selectTotalCount(Map<String, String> searchMap);

    List<NoticeDTO> selectNoticeList(SelectCriteria selectCriteria);

    int incresementNoticeCount(int no);

    NoticeDTO selectNoticeDetail(int no);

    int deleteFile(int no);

    int noticeUpdate(NoticeDTO notice);

    int noticeDelete(int no);

    int newsRegist(NewsDTO news);
}
