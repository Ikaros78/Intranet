package com.ohgiraffers.intranet.member.service;

import com.ohgiraffers.intranet.member.model.dao.MemberDAO;
import com.ohgiraffers.intranet.member.model.dto.MemberDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger((this.getClass()));
    private final MemberDAO memberDao;

    public AuthenticationService(MemberDAO memberDao) {this.memberDao = memberDao;}

    @Override
    public UserDetails loadUserByUsername(String mem_id) throws UsernameNotFoundException {

        // Authentication 인증 -> 참이라는 근거가 있는것에 대해 확인하거나 확증. 사용자의 신분 확인.
        // Authorization 인가 -> 리소스에 대한 접근 권한 및 정책 지정.
        // Authority 권한 -> 권리가 미치는 범위.

        MemberDTO member = memberDao.findByMemberId(mem_id);
        log.info("[인증 서비스] ================================ ");
        log.info("[인증 서비스] mem_id: " + mem_id);

        if(member == null){
            throw new UsernameNotFoundException("확인되지 않은 회원입니다.");
        }

        return member;
    }
}
