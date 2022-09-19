package com.ohgiraffers.intranet.deptNotice.model.service;

import com.ohgiraffers.intranet.deptNotice.model.dto.DbDeptDTO;
import com.ohgiraffers.intranet.notice.model.dto.NoticeDTO;

import java.util.List;
import java.util.Map;

public interface DeptNoticeService {
   

    int selectTotalCount(Map<String, String> searchMap);

    List<DbDeptDTO> selectDeptNoticeList(Map<String, Object> finalMap);

    DbDeptDTO selectDeptNoticeDetail(int no);

    int deptNoticeRegist(DbDeptDTO deptNotice);

    int deptNoticeDelete(DbDeptDTO deptNotice);

    int deptNoticeUpdate(DbDeptDTO deptNotice);
}
