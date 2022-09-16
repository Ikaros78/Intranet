package com.ohgiraffers.intranet.authorManage.model.service;

import com.ohgiraffers.intranet.authorManage.model.dao.AuthorMapper;
import com.ohgiraffers.intranet.authorManage.model.dto.AuthoritDTO;
import com.ohgiraffers.intranet.member.model.dao.MemberMapper;
import com.ohgiraffers.intranet.member.model.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService{

    private final AuthorMapper authorMapper;
    private final MemberMapper memberMapper;

    @Autowired
    public AuthorServiceImpl(AuthorMapper authorMapper,MemberMapper memberMapper) {

        this.authorMapper = authorMapper;
        this.memberMapper = memberMapper;
    }

    /* 게시판 권한 관리를 위해 회원 조회용 */
    @Override
    public List<MemberDTO>  selectMemberListForCalendarAndBoardManage(String searchCondition) {

        List<MemberDTO> memberList = memberMapper.selectMemberListForCalendarAndBoardManage(searchCondition);

        return memberList;
    }

    /* 게시판 권한 일단 삭제용 */
    @Transactional
    @Override
    public int deleteBoardAuthority(int memNum) {

        int result = memberMapper.deleteBoardAuthority(memNum);

        return result;
    }

    /* 게시판 권한 관리 추가*/
    @Transactional
    @Override
    public int insertAuthority(List<AuthoritDTO> authList) {

        int result = 0;
        if(authList.size() > 0){
            for (AuthoritDTO authoritDTO : authList) {
                result =  memberMapper.insertAuthority(authoritDTO);
            }
        }
        

        return result;

    }

    /*인사,접근 권한 관리를 위해 인사부 직원들 리스트 조회 */
    @Override
    public List<MemberDTO> selectMemberListForEmpAndAllManage() {

        List<MemberDTO> memberList = memberMapper.selectMemberListForEmpAndAllManage();

        return memberList;
    }


    /* 인사 권한 일단 삭제 */
    @Transactional
    @Override
    public int deleteEmpAuthority(int memNum)  {

        int result = memberMapper.deleteEmpAuthority(memNum);


        return result;
    }

    /* 접근 권한 일단 삭제 */
    @Transactional
    @Override
    public int deleteAllAuthority(int memNum) {

        int result = memberMapper.deleteAllAuthority(memNum);

        return result;
    }


}
