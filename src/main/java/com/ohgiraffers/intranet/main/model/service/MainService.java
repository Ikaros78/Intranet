package com.ohgiraffers.intranet.main.model.service;

import com.ohgiraffers.intranet.member.model.dao.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MainService {

    private final MemberMapper memberMapper;

    @Autowired
    public MainService(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }


    public int selectMemNumById(String mem_id) {

        int mem_num = memberMapper.selectMemNumById(mem_id);

        return mem_num;
    }
}
