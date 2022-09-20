package com.ohgiraffers.intranet.sign.model.service;

import com.ohgiraffers.intranet.common.exception.sign.SignApproveException;
import com.ohgiraffers.intranet.member.model.dao.MemberMapper;
import com.ohgiraffers.intranet.member.model.dto.DepartmentDTO;
import com.ohgiraffers.intranet.member.model.dto.MemberDTO;
import com.ohgiraffers.intranet.sign.model.dao.SignMapper;
import com.ohgiraffers.intranet.sign.model.dto.SignDTO;
import com.ohgiraffers.intranet.sign.model.dto.SignFileDTO;
import com.ohgiraffers.intranet.sign.model.dto.SignFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SignServiceImpl implements SignService {

    private final SignMapper signMapper;
    private final MemberMapper memberMapper;

    @Autowired
    public SignServiceImpl(SignMapper signMapper, MemberMapper memberMapper){

        this.signMapper = signMapper;
        this.memberMapper = memberMapper;
    }

    /* 결재대기함 전체 갯수 조회용 메소드 */
    @Override
    public int selectTotalWaitingCount(Map<String, Object> searchMap) {

        int result = signMapper.selectTotalWaitingCount(searchMap);

        return result;
    }

    /* 결재대기함 전체 조회용 메소드 */
    @Override
    public List<SignDTO> selectWaitingList(Map<String, Object> searchList) {

        List<SignDTO> waitingList = signMapper.selectWaitingList(searchList);

        return waitingList;
    }

    /* 전자결재 상세조회용 메소드 */
    @Override
    public SignDTO selectSignDetail(String signNo) {

        SignDTO signResult = signMapper.selectSignDetail(signNo);

        return signResult;
    }

    /* 전자결재 일괄결재용 메소드 */
    @Override
    public int updateSignChecked(Map<String, Object> signMap) throws SignApproveException {

        int result = signMapper.updateSignChecked(signMap);

        if(!(result > 0)){
            throw new SignApproveException("결재에 실패하였습니다. 다시 시도해주세요.");
        }

        return result;
    }

    /* 최근 기안한 양식명 불러오기용 메소드 */
    @Override
    public List<SignDTO> selectRecentForm(int mem_num) {

        List<SignDTO> recentForm = signMapper.selectRecentForm(mem_num);

        return recentForm;
    }

    /* 전체 양식 갯수 조회용 메소드 */
    @Override
    public int selectTotalFormCount(Map<String, Object> searchMap) {

        int result = signMapper.selectTotalFormCount(searchMap);

        return result;
    }

    /* 양식 전체 조회용 메소드 */
    @Override
    public List<SignFormDTO> selectAllForm(Map<String, Object> searchList) {

        List<SignFormDTO> formList = signMapper.selectAllForm(searchList);

        return formList;
    }

    /* formCode로 하나의 form 조회용 메소드 */
    @Override
    public SignFormDTO selectFormByCode(String formCode) {

        SignFormDTO selectForm = signMapper.selectFormByCode(formCode);

        return selectForm;
    }

    /* 결재용 메소드 */
    @Override
    public int approveSign(Map<String, Object> approve) throws SignApproveException {

        int result = signMapper.approveSign(approve);

        if(!(result > 0)){
            throw new SignApproveException("결재에 실패하였습니다. 다시 시도해주세요.");
        }

        return result;

    }

    /* 결재 반려용 메소드 */
    @Override
    public int refuseSign(Map<String, Object> refuse) throws SignApproveException {

        int result = signMapper.refuseSign(refuse);

        if(!(result > 0)){
            throw new SignApproveException("결재에 실패하였습니다. 다시 시도해주세요.");
        }

        return result;
    }

    /* 결재완료함 전체 갯수 조회용 메소드*/
    @Override
    public int selectTotalCompletedCount(Map<String, Object> searchMap) {

        int result = signMapper.selectTotalCompletedCount(searchMap);

        return result;
    }

    /* 결재완료함 조회용 메소드 */
    @Override
    public List<SignDTO> selectCompletedList(Map<String, Object> searchList) {

        List<SignDTO> completedList = signMapper.selectCompletedList(searchList);

        return completedList;
    }

    /* 반려문서함 전체 갯수 조회용 메소드 */
    @Override
    public int selectTotalRefusedCount(Map<String, Object> searchMap) {

        int result = signMapper.selectTotalRefusedCount(searchMap);

        return result;
    }

    /* 반려문서함 조회용 메소드 */
    @Override
    public List<SignDTO> selectRefusedList(Map<String, Object> searchList) {

        List<SignDTO> refusedList = signMapper.selectRefusedList(searchList);

        return refusedList;
    }


    /* 부서 전체 조회용 메소드 */
    @Override
    public List<DepartmentDTO> selectDeptList() {

        List<DepartmentDTO> deptList = memberMapper.selectDeptList();

        return deptList;
    }

    /* 결재진행함 전체 갯수 조회용 메소드 */
    @Override
    public int selectTotalProgressCount(Map<String, Object> searchMap) {

        int progressResult = signMapper.selectTotalProgressCount(searchMap);

        int result = progressResult;

        return result;
    }

    /* 진행중인 결재 조회용 메소드 */
    @Override
    public List<SignDTO> selectProgressList(Map<String, Object> searchList) {

        List<SignDTO> progressList = signMapper.selectProgressList(searchList);

        return progressList;
    }

    /* 임시저장함 전체 갯수 조회용 메소드 */
    @Override
    public int selectTotalTempCount(Map<String, Object> searchMap) {

        int result = signMapper.selectTotalTempCount(searchMap);

        return result;
    }

    /* 임시저장함 전체 조회용 메소드 */
    @Override
    public List<SignDTO> selectTempList(Map<String, Object> searchList) {

        List<SignDTO> tempList = signMapper.selectTempList(searchList);

        return tempList;
    }

    /* 선택된 임시저장 결재 일괄삭제용 메소드 */
    @Override
    public int deleteSignChecked(Map<String, Object> signMap) throws SignApproveException {

        int result = signMapper.deleteSignChecked(signMap);

        if(!(result > 0)){
            throw new SignApproveException("결재에 실패하였습니다. 다시 시도해주세요.");
        }

        return result;
    }


    /* 결재요청함 전체 갯수 조회용 메소드 */
    @Override
    public int selectTotalRequestCount(Map<String, Object> searchMap) {

        int result = signMapper.selectTotalRequestCount(searchMap);

        return result;
    }

    /* 결재요청함 전체 조회용 메소드 */
    @Override
    public List<SignDTO> selectRequestList(Map<String, Object> searchList) {

        List<SignDTO> requestList = signMapper.selectRequestList(searchList);

        return requestList;
    }

    /* 부서코드로 부서인원 조회용 메소드 */
    @Override
    public List<MemberDTO> selectMemByDeptCode(String code) {

        List<MemberDTO> memberList = signMapper.selectMemByDeptCode(code);

        return memberList;
    }

    /* 멤버번호로 인원 조회용 메소드 */
    @Override
    public MemberDTO selectMemByNum(Map<String, Object> numMap) {

        MemberDTO member = signMapper.selectMemByNum(numMap);

        return member;
    }

    /* 결재 등록용 메소드 */
    @Override
    public int registSign(Map<String, String> insertMap) throws SignApproveException {

        int result = signMapper.registSign(insertMap);

        if(!(result > 0)){
            throw new SignApproveException("결재등록에 실패하였습니다. 다시 시도해주세요.");
        }

        return result;
    }

    /* 결재선 등록용 메소드 */
    @Override
    public int registApprover(Map<String, String> approver) throws SignApproveException {

        int result = signMapper.registApprover(approver);

        if(!(result > 0)){
            throw new SignApproveException("결재자 등록에 실패하였습니다. 다시 시도해주세요.");
        }

        return result;
    }
    /* 결재선 최종결재자 등록용 메소드 */
    @Override
    public int registFianlApprover(Map<String, String> approver) throws SignApproveException {

        int result = signMapper.registLastApprover(approver);

        if(!(result > 0)){
            throw new SignApproveException("결재자 등록에 실패하였습니다. 다시 시도해주세요.");
        }

        return result;
    }

    /* 수신처 등록용 메소드 */
    @Override
    public int registReceiver(Map<String, String> receiver) throws SignApproveException {

        int result = signMapper.registReceiver(receiver);

        if(!(result > 0)){
            throw new SignApproveException("수신처 등록에 실패하였습니다. 다시 시도해주세요.");
        }

        return result;
    }

    /* 참조자 등록용 메소드 */
    @Override
    public int registReferencer(Map<String, String> referencer) throws SignApproveException {

        int result = signMapper.registReferencer(referencer);

        if(!(result > 0)){
            throw new SignApproveException("수신처 등록에 실패하였습니다. 다시 시도해주세요.");
        }

        return result;
    }

    /* 첨부파일 등록용 메소드 */
    @Override
    public int signFileInsert(SignFileDTO signFile) throws SignApproveException {

        int result = signMapper.registSignFile(signFile);

        if(!(result > 0)){
            throw new SignApproveException("첨부파일 등록에 실패하였습니다. 다시 시도해주세요.");
        }

        return result;
    }

    /* 결재 임시저장용 메소드 */
    @Override
    public int registTempSign(Map<String, String> insertMap) throws SignApproveException {

        int result = signMapper.registTempSign(insertMap);

        if(!(result > 0)){
            throw new SignApproveException("결재임시저장에 실패하였습니다. 다시 시도해주세요.");
        }

        return result;
    }

    /* 임시저장한 결재 삭제용 메소드 */
    @Override
    public int deleteSign(int signNo) throws SignApproveException {

        int result = signMapper.deleteSign(signNo);

        if(!(result > 0)){
            throw new SignApproveException("기안 삭제에 실패하였습니다. 다시 시도해주세요.");
        }

        return result;
    }

    /* 참조/열람문서함 전체 갯수 조회용 메소드 */
    @Override
    public int selectTotalReferenceCount(Map<String, Object> searchMap) {

        int result = signMapper.selectTotalReferenceCount(searchMap);

        return result;
    }

    /* 참조/열람문서함 전체 조회용 메소드 */
    @Override
    public List<SignDTO> selectReferenceList(Map<String, Object> searchList) {

        List<SignDTO> referenceList = signMapper.selectReferenceList(searchList);

        return referenceList;
    }

    /* 수신함 전체 갯수 조회용 메소드 */
    @Override
    public int selectTotalReceiveCount(Map<String, Object> searchMap) {

        int result = signMapper.selectTotalReceiveCount(searchMap);

        return result;
    }

    /* 수신함 전체 조회용 메소드 */
    @Override
    public List<SignDTO> selectReceiveList(Map<String, Object> searchList) {

        List<SignDTO> receiveList = signMapper.selectReceiveList(searchList);

        return receiveList;
    }

    /* 열람권한 추가용 메소드 */
    @Override
    public int addReader(Map<String, Object> numMap) {

        int result = signMapper.addReader(numMap);

        return result;
    }


}
