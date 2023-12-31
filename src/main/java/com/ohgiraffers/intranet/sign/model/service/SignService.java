package com.ohgiraffers.intranet.sign.model.service;

import com.ohgiraffers.intranet.common.exception.sign.SignApproveException;
import com.ohgiraffers.intranet.member.model.dto.DepartmentDTO;
import com.ohgiraffers.intranet.member.model.dto.MemberDTO;
import com.ohgiraffers.intranet.sign.model.dto.SignDTO;
import com.ohgiraffers.intranet.sign.model.dto.SignFileDTO;
import com.ohgiraffers.intranet.sign.model.dto.SignFormDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface SignService {

    int selectTotalWaitingCount(Map<String, Object> searchMap);
    List<SignDTO> selectWaitingList(Map<String, Object> searchList);
    SignDTO selectSignDetail(String signNo);
    int updateSignChecked(Map<String, Object> signMap) throws SignApproveException;
    List<SignDTO> selectRecentForm(int mem_num);
    int selectTotalFormCount(Map<String, Object> searchMap);
    List<SignFormDTO> selectAllForm(Map<String, Object> searchList);
    SignFormDTO selectFormByCode(String formCode);
    int approveSign(Map<String, Object> approve) throws SignApproveException;
    int refuseSign(Map<String, Object> refuse) throws SignApproveException;
    int selectTotalCompletedCount(Map<String, Object> searchMap);
    List<SignDTO> selectCompletedList(Map<String, Object> searchList);
    int selectTotalRefusedCount(Map<String, Object> searchMap);
    List<SignDTO> selectRefusedList(Map<String, Object> searchList);
    List<DepartmentDTO> selectDeptList();
    int selectTotalProgressCount(Map<String, Object> searchMap);
    List<SignDTO> selectProgressList(Map<String, Object> searchList);
    int selectTotalTempCount(Map<String, Object> searchMap);
    List<SignDTO> selectTempList(Map<String, Object> searchList);
    int deleteSignChecked(Map<String, Object> signMap) throws SignApproveException;
    int selectTotalRequestCount(Map<String, Object> searchMap);
    List<SignDTO> selectRequestList(Map<String, Object> searchList);
    List<MemberDTO> selectMemByDeptCode(String code);
    MemberDTO selectMemByNum(Map<String, Object> num);
    int registSign(Map<String, String> insertMap) throws SignApproveException;
    int registApprover(Map<String, String> approver) throws SignApproveException;
    int registFianlApprover(Map<String, String> approver) throws SignApproveException;
    int registReceiver(Map<String, String> receiver) throws SignApproveException;
    int registReferencer(Map<String, String> referencer) throws SignApproveException;
    int signFileInsert(SignFileDTO signFile) throws SignApproveException;
    int registTempSign(Map<String, String> insertMap) throws SignApproveException;
    int deleteSign(int signNo) throws SignApproveException;
    int selectTotalReferenceCount(Map<String, Object> searchMap);
    List<SignDTO> selectReferenceList(Map<String, Object> searchList);
    int selectTotalReceiveCount(Map<String, Object> searchMap);
    List<SignDTO> selectReceiveList(Map<String, Object> searchList);
    int addReader(Map<String, Object> numMap);
}
