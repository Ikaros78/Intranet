package com.ohgiraffers.intranet.member.model.dao;

import com.ohgiraffers.intranet.authorManage.model.dto.AuthoritDTO;
import com.ohgiraffers.intranet.member.model.dto.DepartmentDTO;
import com.ohgiraffers.intranet.member.model.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MemberMapper {

    int memberRegistInsert(MemberDTO member);

//    int updateMember(MemberDTO member); //회원 정보 수정 추후 업데이트 예정 09-01

    /* 시큐리티 세션에서 Id를 확인 */
    MemberDTO findByMemberId(String mem_id);

    /* 일정권한 관리를 위한 회원 리스트 조회용 */
    List<MemberDTO> selectMemberListForCalendarManage(String searchCondition);

    /* 아이디 중복 체크 */
    String selectCheckMember(String mem_id);

    /* 일정권한, 게시판 관리 셀렉트박스용 부서 조회용 */
    List<DepartmentDTO> selectDeptList();

    /* 일정 권한 변경을 위해 먼저 다 삭제해주기*/
    int deleteCalendarAuthority(int memNum);

    /* 일정,게시판 권한 변경을 위해 다시 넣어주기*/
    int insertAuthority(AuthoritDTO authoritDTO);


    /* 부서이름 조회용 메소드 */
    String selectDeptByNum(int mem_num);
//    List<MemberDTO> selectBoardAuthority(String searchValue);

    /* 게시판 권한 관리용 회원리스트 조회 */
    List<MemberDTO> selectMemberListForBoardManage(String searchCondition);

    /* 게시판 권한 관리용 권한 삭제 */
    int deleteBoardAuthority(int memNum);


    List<MemberDTO> selectMemberListForEmpManage();
}
