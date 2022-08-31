package com.ohgiraffers.intranet.member.service;

import com.ohgiraffers.intranet.member.model.dao.MemberDAO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService{

    private MemberDAO memberDAO;

    public MemberServiceImpl(MemberDAO memberDAO){

        this.memberDAO = memberDAO;
    }

    /* 사용자가 입력한  */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
