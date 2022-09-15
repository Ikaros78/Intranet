package com.ohgiraffers.intranet.empManage.model.service;

import com.ohgiraffers.intranet.common.paging.SelectCriteria;
import com.ohgiraffers.intranet.empManage.model.dto.AppointmentDTO;
import com.ohgiraffers.intranet.member.model.dto.MemberDTO;

import java.util.List;
import java.util.Map;

public interface EmpService {

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
}
