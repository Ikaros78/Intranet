package com.ohgiraffers.intranet.empManage.model.service;

import com.ohgiraffers.intranet.common.paging.SelectCriteria;
import com.ohgiraffers.intranet.empManage.model.dao.EmpMapper;
import com.ohgiraffers.intranet.member.model.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("empService")
public class EmpServiceImpl implements EmpService {

    private EmpMapper empMapper;

    @Autowired
    public EmpServiceImpl(EmpMapper empMapper){
        this.empMapper = empMapper;
    }

    @Override
    public int selectEmpTotalCount(Map<String, String> searchMap){

        int result = empMapper.selectEmpTotalCount(searchMap);

        return result;
    }

    @Override
    public List<MemberDTO> selectEmpList(SelectCriteria selectCriteria) {

        List<MemberDTO> memberList = empMapper.selectEmpList(selectCriteria);

        return memberList;
    }


}
