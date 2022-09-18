package com.ohgiraffers.intranet.empManage.model.dao;

import com.ohgiraffers.intranet.common.paging.SelectCriteria;
import com.ohgiraffers.intranet.empManage.model.dto.AppointmentDTO;
import com.ohgiraffers.intranet.member.model.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {

    /* 직원 수 조회*/
    int selectEmpTotalCount(Map<String, String> searchMap);

    /* 직원 리스트 조회 */
    List<MemberDTO> selectEmpList(SelectCriteria selectCriteria);

    /* 인사 발령 등록 시 직원 정보 가져오는 메소드 */
    MemberDTO getMemberName(int mem_num);

    /* 인사 발령 테이블 정보 insert 하는 메소드 */
    int appointmentRegist(AppointmentDTO appointment);

    /* 멤버 테이블에 인사 발령 정보로 update 하는 메소드 */
    int appointmentUpdate(MemberDTO member);

    /* 인사 발령 수 조회 */
    int selectHrListTotalCount(Map<String, String> searchMap);

    /* 인사 발령 리스트 조회 */
    List<AppointmentDTO> selectHrList(SelectCriteria selectCriteria);

    /* 인사 발령 삭제(값 되돌리기) */
    int hrDelete(int appointment);

    /* 멤버 인사 발령 정보로 업데이트 */
    int updateMember(int mem_num, String dept_rank, String dept_code);

    /* 직원 상세 정보 조회 */
    MemberDTO selectMemberDetail(int no);

    /* 직원 정보 수정 - 관리자 */
    int empUpdate(MemberDTO member);

    /* 바뀌기 전 직원 번호 DB 저장 */
    int numModify(MemberDTO member);

    /* 이전 직원 번호 삭제 */
    int numDelete(MemberDTO member);
}
