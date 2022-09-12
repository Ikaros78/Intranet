package com.ohgiraffers.intranet.emplManage.model.service;

import com.ohgiraffers.intranet.common.paging.SelectCriteria;
import com.ohgiraffers.intranet.emplManage.model.dao.EmplMapper;
import com.ohgiraffers.intranet.member.model.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EmplServiceImpl implements EmplService {

    private EmplMapper emplMapper;

    @Autowired
    private EmplServiceImpl(EmplMapper emplMapper){
        this.emplMapper = emplMapper;
    }

    @Override
    public int selectEmpTotalCount(Map<String, String> searchMap){

        int result = emplMapper.selectEmpTotalCount(searchMap);

        return result;
    }

    @Override
    public List<MemberDTO> selectEmpList(SelectCriteria selectCriteria) {

        List<MemberDTO> memberList = emplMapper.selectEmpList(selectCriteria);

        return memberList;
    }


}
