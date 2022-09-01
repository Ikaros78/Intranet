package com.ohgiraffers.intranet.notice.model.dao;

import com.ohgiraffers.intranet.notice.model.dto.NoticeDTO;
import com.ohgiraffers.intranet.notice.model.dto.NoticeFileDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {


    int noticeRegist(NoticeDTO notice);

    int fileRegist(NoticeFileDTO noticeFile);

    List<NoticeDTO> selectAllNoticeList();
}
