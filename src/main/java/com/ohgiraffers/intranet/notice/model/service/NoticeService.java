package com.ohgiraffers.intranet.notice.model.service;

import com.ohgiraffers.intranet.notice.model.dao.NoticeMapper;
import com.ohgiraffers.intranet.notice.model.dto.NoticeDTO;
import com.ohgiraffers.intranet.notice.model.dto.NoticeFileDTO;
import org.springframework.stereotype.Service;

@Service("noticeService")
public class NoticeService {

    private final NoticeMapper noticeMapper;

    public NoticeService(NoticeMapper noticeMapper){

        this.noticeMapper = noticeMapper;
    }


    public int noticeRegist(NoticeDTO notice) {

        return noticeMapper.noticeRegist(notice);
    }

    public int noticeFileInsert(NoticeFileDTO noticeFile) {

        return noticeMapper.fileRegist(noticeFile);
    }
}
