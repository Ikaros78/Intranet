package com.ohgiraffers.intranet.member.service;

import com.ohgiraffers.intranet.common.exception.member.MemberRegistException;
import com.ohgiraffers.intranet.common.exception.member.MemberUpdateException;
import com.ohgiraffers.intranet.member.model.dto.MemberDTO;

public interface MemberService {

    public void memberRegistInsert(MemberDTO member) throws MemberRegistException;

    boolean selectCheckMember(String mem_id);

    public void memberUpdate(MemberDTO member) throws MemberUpdateException;

//    /* 해당 인원의 부서를 가져오는 메소드 */
//    public String selectDeptByNum(int mem_num) {
//
//        String deptName = memberMapper.selectDeptByNum(mem_num);
//
//        return deptName;
//    }
}
