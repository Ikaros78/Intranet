package com.ohgiraffers.intranet.deptNotice.model.service;

import com.ohgiraffers.intranet.deptNotice.model.dao.DeptNoticeMapper;
import com.ohgiraffers.intranet.deptNotice.model.dto.DbDeptDTO;
import com.ohgiraffers.intranet.notice.model.dao.NoticeMapper;
import com.ohgiraffers.intranet.notice.model.dto.NoticeDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service("deptNoticeService")
public class DeptNoticeServiceImpl implements DeptNoticeService{

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private DeptNoticeMapper deptNoticeMapper;
    private NoticeMapper noticeMapper;

    @Autowired
    public DeptNoticeServiceImpl(DeptNoticeMapper deptNoticeMapper, NoticeMapper noticeMapper){
        this.deptNoticeMapper = deptNoticeMapper;
        this.noticeMapper = noticeMapper;
    }

    @Override
    public int selectTotalCount(Map<String, String> searchMap) {

        int result = deptNoticeMapper.selectTotalCount(searchMap);

        return result;
    }

    @Override
    public List<DbDeptDTO> selectDeptNoticeList(Map<String, Object> finalMap) {

        List<DbDeptDTO> deptNoticeList = deptNoticeMapper.selectDeptNoticeList(finalMap);

        return deptNoticeList;
    }

    @Override
    public DbDeptDTO selectDeptNoticeDetail(int no) {

        DbDeptDTO deptNoticeDetail = null;


        int result = deptNoticeMapper.incresementNoticeCount(no);

        if(result > 0){

            deptNoticeDetail = deptNoticeMapper.selectDeptNoticeDetail(no);
        }

        return deptNoticeDetail;
    }

    @Override
    @Transactional
    public int deptNoticeRegist(DbDeptDTO deptNotice) {

        int result = deptNoticeMapper.deptNoticeRegist(deptNotice);

        return result;
    }

    @Transactional
    @Override
    public int deptNoticeDelete(DbDeptDTO deptNotice) {

        int result = deptNoticeMapper.deptNoticeDelete(deptNotice);

        return result;
    }

    @Transactional
    @Override
    public int deptNoticeUpdate(DbDeptDTO deptNotice) {

        int result = deptNoticeMapper.deptNoticeUpdate(deptNotice);

        return result;
    }


}
