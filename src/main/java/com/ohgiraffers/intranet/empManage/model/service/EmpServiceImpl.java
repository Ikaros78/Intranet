package com.ohgiraffers.intranet.empManage.model.service;

import com.ohgiraffers.intranet.common.paging.SelectCriteria;
import com.ohgiraffers.intranet.empManage.model.dao.EmpMapper;
import com.ohgiraffers.intranet.empManage.model.dto.AppointmentDTO;
import com.ohgiraffers.intranet.member.model.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service("empService")
public class EmpServiceImpl implements EmpService {

    private EmpMapper empMapper;

    @Autowired
    public EmpServiceImpl(EmpMapper empMapper){
        this.empMapper = empMapper;
    }

    /* 직원 수 조회용 메소드 */
    @Override
    public int selectEmpTotalCount(Map<String, String> searchMap){

        int result = empMapper.selectEmpTotalCount(searchMap);

        return result;
    }

    /* 직원 조회용 메소드 */
    @Override
    public List<MemberDTO> selectEmpList(SelectCriteria selectCriteria) {

        List<MemberDTO> memberList = empMapper.selectEmpList(selectCriteria);

        return memberList;
    }

    /* 인사 발령 등록 시 이름 가져오는 메소드 */
    @Override
    public MemberDTO getMemberName(int mem_num) {

        MemberDTO member = empMapper.getMemberName(mem_num);

        return member;
    }

    /* 인사 발령 테이블 정보 insert 하는 메소드 */
    @Override
    @Transactional
    public int appointmentRegist(AppointmentDTO appointment) {

        return empMapper.appointmentRegist(appointment);
    }

    /* 멤버 테이블에 인사 발령 정보로 update 하는 메소드 */
    @Override
    @Transactional
    public int appointmentUpdate(MemberDTO member) {

        return empMapper.appointmentUpdate(member);
    }

    /* 인사 발령 수 조회 */
    @Override
    public int selectHrListTotalCount(Map<String, String> searchMap) {
        int result = empMapper.selectHrListTotalCount(searchMap);

        return result;
    }


}
