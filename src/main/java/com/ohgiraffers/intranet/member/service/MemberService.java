package com.ohgiraffers.intranet.member.service;

import com.ohgiraffers.intranet.common.exception.member.MemberRegistException;
import com.ohgiraffers.intranet.member.model.dao.MemberDAO;
import com.ohgiraffers.intranet.member.model.dto.MemberDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {

    private final Logger log = LoggerFactory.getLogger((this.getClass()));

    private final PasswordEncoder passwordEncoder;

    private final MemberDAO memberDAO;

    public MemberService(PasswordEncoder passwordEncoder, MemberDAO memberDAO) {
        this.passwordEncoder = passwordEncoder;
        this.memberDAO = memberDAO;
    }

    @Transactional
    public void memberRegistInsert(MemberDTO member) throws MemberRegistException{

        log.info("[MemberService] Insert Member : " + member);
        int result = memberDAO.insertMember(member);

        log.info("[memberService] Insert result : " + ((result > 0) ? "회원가입 성공! " : "회원 가입 실패!"));

        if(!(result > 0 )){
            throw new MemberRegistException("회원 가입에 실패하였습니다. 다시 시도해주세요.");

        }

    }
}
