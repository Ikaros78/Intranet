package com.ohgiraffers.intranet.sign.model.service;

import com.ohgiraffers.intranet.common.exception.sign.SignApproveException;
import com.ohgiraffers.intranet.member.model.dao.MemberMapper;
import com.ohgiraffers.intranet.member.model.dto.DepartmentDTO;
import com.ohgiraffers.intranet.member.model.dto.MemberDTO;
import com.ohgiraffers.intranet.sign.model.dao.SignMapper;
import com.ohgiraffers.intranet.sign.model.dto.SignDTO;
import com.ohgiraffers.intranet.sign.model.dto.SignFormDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SignService {

    private SignMapper signMapper;

    private MemberMapper memberMapper;

    public SignService(SignMapper signMapper, MemberMapper memberMapper){

        this.signMapper = signMapper;
        this.memberMapper = memberMapper;
    }

    /* 결재대기함 전체 갯수 조회용 메소드 */
    public int selectTotalWaitingCount(Map<String, Object> searchMap) {

        int result = signMapper.selectTotalWaitingCount(searchMap);

        return result;
    }

    /* 결재대기함 전체 조회용 메소드 */
    public List<SignDTO> selectWaitingList(Map<String, Object> searchList) {

        List<SignDTO> waitingList = signMapper.selectWaitingList(searchList);

        return waitingList;
    }

    /* 전자결재 상세조회용 메소드 */
    public SignDTO selectSignDetail(String signNo) {

        SignDTO signResult = signMapper.selectSignDetail(signNo);

        return signResult;
    }

    /* 전자결재 일괄결재용 메소드 */
    public int updateSignChecked(Map<String, Object> signMap) throws SignApproveException {

        int result = signMapper.updateSignChecked(signMap);

        if(!(result > 0)){
            throw new SignApproveException("결재에 실패하였습니다. 다시 시도해주세요.");
        }

        return result;
    }

    /* 최근 기안한 양식명 불러오기용 메소드 */
    public List<SignDTO> selectRecentForm(int mem_num) {

        List<SignDTO> recentForm = signMapper.selectRecentForm(mem_num);
        
        return recentForm;
    }

    /* 전체 양식 갯수 조회용 메소드 */
    public int selectTotalFormCount(Map<String, Object> searchMap) {

        int result = signMapper.selectTotalFormCount(searchMap);

        return result;
    }

    /* 양식 전체 조회용 메소드 */
    public List<SignFormDTO> selectAllForm(Map<String, Object> searchList) {

        List<SignFormDTO> formList = signMapper.selectAllForm(searchList);

        return formList;
    }

    /* formCode로 하나의 form 조회용 메소드 */
    public SignFormDTO selectFormByCode(String formCode) {

        SignFormDTO selectForm = signMapper.selectFormByCode(formCode);

        return selectForm;
    }

    /* 결재용 메소드 */
    public int approveSign(Map<String, Object> approve) throws SignApproveException {

        int result = signMapper.approveSign(approve);

        if(!(result > 0)){
            throw new SignApproveException("결재에 실패하였습니다. 다시 시도해주세요.");
        }

        return result;

    }

    /* 결재 반려용 메소드 */
    public int refuseSign(Map<String, Object> refuse) throws SignApproveException {

        int result = signMapper.refuseSign(refuse);

        if(!(result > 0)){
            throw new SignApproveException("결재에 실패하였습니다. 다시 시도해주세요.");
        }

        return result;
    }

    /* 결재완료함 전체 갯수 조회용 메소드*/
    public int selectTotalCompletedCount(Map<String, Object> searchMap) {

        int result = signMapper.selectTotalCompletedCount(searchMap);

        return result;
    }

    /* 결재완료함 조회용 메소드 */
    public List<SignDTO> selectCompletedList(Map<String, Object> searchList) {

        List<SignDTO> completedList = signMapper.selectCompletedList(searchList);

        return completedList;
    }

    /* 자신이 기안하고 완료된 결재 갯수 조회용 메소드 */
    public int selectTotalMyCompletedCount(Map<String, Object> searchMap) {

        int result = signMapper.selectTotalMyCompletedCount(searchMap);

        return result;
    }

    /* 자신이 기안하고 완료된 결재 조회용 메소드 */
    public List<SignDTO> selectMyCompletedList(Map<String, Object> searchList) {

        List<SignDTO> completedList = signMapper.selectMyCompletedList(searchList);

        return completedList;
    }

    /* 반려문서함 전체 갯수 조회용 메소드 */
    public int selectTotalRefusedCount(Map<String, Object> searchMap) {

        int result = signMapper.selectTotalRefusedCount(searchMap);

        return result;
    }

    /* 반려문서함 조회용 메소드 */
    public List<SignDTO> selectRefusedList(Map<String, Object> searchList) {

        List<SignDTO> refusedList = signMapper.selectRefusedList(searchList);

        return refusedList;
    }

    /* 자신이 기안하고 반려된 결재 갯수 조회용 메소드 */
    public int selectTotalMyRefusedCount(Map<String, Object> searchMap) {

        int result = signMapper.selectTotalMyRefusedCount(searchMap);

        return result;
    }

    /* 자신이 기안하고 반려된 결재 조회용 메소드 */
    public List<SignDTO> selectMyRefusedList(Map<String, Object> searchList) {

        List<SignDTO> myRefusedList = signMapper.selectMyRefusedList(searchList);

        return myRefusedList;
    }


    /* 부서 전체 조회용 메소드 */
    public List<DepartmentDTO> selectDeptList() {

        List<DepartmentDTO> deptList = memberMapper.selectDeptList();

        return deptList;
    }

    /* 사원 전체 조회용 메소드 */
    public List<MemberDTO> selectAllMember() {

        List<MemberDTO> memberList = signMapper.selectAllMember();

        return memberList;
    }

    /* 결재진행함 전체 갯수 조회용 메소드 */
    public int selectTotalProgressCount(Map<String, Object> searchMap) {

        int progressResult = signMapper.selectTotalProgressCount(searchMap);

        int myProgressResult = signMapper.selectTotalMyProgressCount(searchMap);

        int result = progressResult + myProgressResult;

        return result;
    }

    /* 진행중인 결재 조회용 메소드 */
    public List<SignDTO> selectProgressList(Map<String, Object> searchList) {

        List<SignDTO> progressList = signMapper.selectProgressList(searchList);

        List<SignDTO> myProgressList = signMapper.selectMyProgressList(searchList);

        progressList.addAll(myProgressList);

        return progressList;
    }

    /* 임시저장함 전체 갯수 조회용 메소드 */
    public int selectTotalTempCount(Map<String, Object> searchMap) {

        int result = signMapper.selectTotalTempCount(searchMap);

        return result;
    }

    /* 임시저장함 전체 조회용 메소드 */
    public List<SignDTO> selectTempList(Map<String, Object> searchList) {

        List<SignDTO> tempList = signMapper.selectTempList(searchList);

        return tempList;
    }

    /* 선택된 임시저장 결재 일괄삭제용 메소드 */
    public int deleteSignChecked(Map<String, Object> signMap) throws SignApproveException {

        int result = signMapper.deleteSignChecked(signMap);

        if(!(result > 0)){
            throw new SignApproveException("결재에 실패하였습니다. 다시 시도해주세요.");
        }

        return result;
    }


}
