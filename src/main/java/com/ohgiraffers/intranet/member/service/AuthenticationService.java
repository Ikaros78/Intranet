package com.ohgiraffers.intranet.member.service;

import com.ohgiraffers.intranet.authorManage.model.dto.AuthoritDTO;
import com.ohgiraffers.intranet.member.model.dao.MemberMapper;
import com.ohgiraffers.intranet.member.model.dto.Au_AuthorizationDTO;
import com.ohgiraffers.intranet.member.model.dto.MemberDTO;
import com.ohgiraffers.intranet.member.model.dto.UserImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthenticationService implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger((this.getClass()));
    private final MemberMapper memberMapper;

    public AuthenticationService(MemberMapper memberMapper) {this.memberMapper = memberMapper;}

    @Override
    public UserDetails loadUserByUsername(String mem_id) throws UsernameNotFoundException {

        // Authentication 인증 -> 참이라는 근거가 있는것에 대해 확인하거나 확증. 사용자의 신분 확인.
        // Authorization 인가 -> 리소스에 대한 접근 권한 및 정책 지정.
        // Authority 권한 -> 권리가 미치는 범위.

        log.info("[인증 서비스] ================================ ");
        log.info("[인증 서비스] mem_id: " + mem_id);

        MemberDTO member = memberMapper.findByMemberId(mem_id);
        log.info("[인증 서비스] member : " + member);

        if(member == null){
            throw new UsernameNotFoundException("확인되지 않은 회원입니다.");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();

        if(member.getAuthorit() != null){
            List<AuthoritDTO> authorList = member.getAuthorit();

            for(int i = 0; i < authorList.size(); i++) {

                List<Au_AuthorizationDTO> au_authorization = authorList.get(i).getAu_authorization();
                authorities.add(new SimpleGrantedAuthority(au_authorization.toString()));
            }
        }

        UserImpl user = new UserImpl(member.getMem_id(),member.getMem_pw(), authorities);
        user.setDetails(member);

        return user;

    }
}
