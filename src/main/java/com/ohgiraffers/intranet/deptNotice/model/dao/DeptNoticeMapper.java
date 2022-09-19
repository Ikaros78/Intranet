package com.ohgiraffers.intranet.deptNotice.model.dao;

import com.ohgiraffers.intranet.deptNotice.model.dto.DbDeptDTO;
import com.ohgiraffers.intranet.notice.model.dto.NoticeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DeptNoticeMapper {

    int selectTotalCount(Map<String, String> searchMap);

    List<DbDeptDTO> selectDeptNoticeList(Map<String, Object> finalMap);

    DbDeptDTO selectDeptNoticeDetail(int no);

    int incresementNoticeCount(int no);

    int deptNoticeRegist(DbDeptDTO deptNotice);

    int deptNoticeDelete(DbDeptDTO deptNotice);

    int deptNoticeUpdate(DbDeptDTO deptNotice);
}
