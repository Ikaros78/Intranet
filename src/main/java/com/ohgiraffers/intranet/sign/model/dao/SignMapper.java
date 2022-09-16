package com.ohgiraffers.intranet.sign.model.dao;

import com.ohgiraffers.intranet.member.model.dto.MemberDTO;
import com.ohgiraffers.intranet.sign.model.dto.SignDTO;
import com.ohgiraffers.intranet.sign.model.dto.SignFileDTO;
import com.ohgiraffers.intranet.sign.model.dto.SignFormDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SignMapper {

    int selectTotalWaitingCount(Map<String, Object> searchMap);

    List<SignDTO> selectWaitingList(Map<String, Object> searchList);

    SignDTO selectSignDetail(String signNo);

    int updateSignChecked(Map<String, Object> signMap);

    List<SignDTO> selectRecentForm(int mem_num);

    int selectTotalFormCount(Map<String, Object> searchMap);

    List<SignFormDTO> selectAllForm(Map<String, Object> searchList);

    SignFormDTO selectFormByCode(String formCode);

    int approveSign(Map<String, Object> approve);

    int refuseSign(Map<String, Object> refuse);

    int selectTotalCompletedCount(Map<String, Object> searchMap);

    List<SignDTO> selectCompletedList(Map<String, Object> searchList);

    int selectTotalMyCompletedCount(Map<String, Object> searchMap);

    List<SignDTO> selectMyCompletedList(Map<String, Object> searchList);

    int selectTotalRefusedCount(Map<String, Object> searchMap);

    List<SignDTO> selectRefusedList(Map<String, Object> searchList);

    int selectTotalMyRefusedCount(Map<String, Object> searchMap);

    List<SignDTO> selectMyRefusedList(Map<String, Object> searchList);

    int selectTotalProgressCount(Map<String, Object> searchMap);

    int selectTotalMyProgressCount(Map<String, Object> searchMap);

    List<SignDTO> selectProgressList(Map<String, Object> searchList);

    List<SignDTO> selectMyProgressList(Map<String, Object> searchList);

    int selectTotalTempCount(Map<String, Object> searchMap);

    List<SignDTO> selectTempList(Map<String, Object> searchList);

    int deleteSignChecked(Map<String, Object> signMap);

    int selectTotalRequestCount(Map<String, Object> searchMap);

    List<SignDTO> selectRequestList(Map<String, Object> searchList);

    List<MemberDTO> selectMemByDeptCode(String code);

    MemberDTO selectMemByNum(Map<String, Object> numMap);

    int registSign(Map<String, String> insertMap);

    int registApprover(Map<String, String> approver);

    int registLastApprover(Map<String, String> approver);

    int registReceiver(Map<String, String> receiver);

    int registReferencer(Map<String, String> referencer);

    int registSignFile(SignFileDTO signFile);
}
