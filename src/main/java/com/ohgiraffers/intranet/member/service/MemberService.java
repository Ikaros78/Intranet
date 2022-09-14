package com.ohgiraffers.intranet.member.service;

import com.ohgiraffers.intranet.common.exception.member.MemberRegistException;
import com.ohgiraffers.intranet.common.exception.member.MemberUpdateException;
import com.ohgiraffers.intranet.member.model.dto.MemberDTO;

public interface MemberService {

    public void memberRegistInsert(MemberDTO member) throws MemberRegistException;

    boolean selectCheckMember(String mem_id);

    public void memberUpdate(MemberDTO member) throws MemberUpdateException;

}
