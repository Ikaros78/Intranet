package com.ohgiraffers.intranet.member.service;

import com.ohgiraffers.intranet.common.exception.member.MemberRegistException;
import com.ohgiraffers.intranet.common.exception.member.MemberUpdateException;
import com.ohgiraffers.intranet.member.model.dao.MemberMapper;
import com.ohgiraffers.intranet.member.model.dto.MemberDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberServiceImpl implements MemberService {

    private final Logger log = LoggerFactory.getLogger((this.getClass()));

    private final PasswordEncoder passwordEncoder;

    private final MemberMapper memberMapper;

    public MemberServiceImpl(PasswordEncoder passwordEncoder, MemberMapper memberMapper) {
        this.passwordEncoder = passwordEncoder;
        this.memberMapper = memberMapper;
    }


    /* 회원가입 진행 메소드 */
    @Transactional
    public void memberRegistInsert(MemberDTO member) throws MemberRegistException{

        log.info("[MemberService] Insert Member : " + member);
        int result = memberMapper.memberRegistInsert(member);

        log.info("[memberService] Insert result : " + ((result > 0) ? "회원가입 성공! " : "회원 가입 실패!"));

        if(!(result > 0 )){
            throw new MemberRegistException("회원 가입에 실패하였습니다. 다시 시도해주세요.");
        }
    }

    /* 아이디 중복 체크를 확인하는 메소드 */
    public boolean selectCheckMember(String mem_id) {

        String checkResult = memberMapper.selectCheckMember(mem_id);

        return checkResult != null? true : false;
    }

    /* 마이페이지 개인정보 수정 메소드 */
    @Transactional
    public void memberUpdate(MemberDTO member) throws MemberUpdateException {

        log.info("[MemberService] Update Member : " + member);
        int result = memberMapper.memberUpdate(member);

        if(!(result > 0)){
            throw new MemberUpdateException("회원 정보 수정에 실패하였습니다");
        }
    }

    public String selectDeptByNum(int mem_num) {

        String deptName = memberMapper.selectDeptByNum(mem_num);

        return deptName;
    }
}
