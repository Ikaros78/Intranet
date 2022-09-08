package com.ohgiraffers.intranet.sign.model.service;

import com.ohgiraffers.intranet.common.exception.sign.SignApproveException;
import com.ohgiraffers.intranet.sign.model.dao.SignMapper;
import com.ohgiraffers.intranet.sign.model.dto.SignDTO;
import com.ohgiraffers.intranet.sign.model.dto.SignFormDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SignService {

    private SignMapper signMapper;

    public SignService(SignMapper signMapper){

        this.signMapper = signMapper;
    }

    /* 결재대기함 전체 갯수 조회용 메소드 */
    public int selectTotalWaitingCount(Map<String, Object> searchMap) {

        int result = signMapper.selectTotalWaitingCount(searchMap);

        return result;
    }

    /* 결재대기함 전체 조회용 메소드 */
    public List<SignDTO> selectWaitingList(Map<String, Object> selectCriteria) {

        List<SignDTO> waitingList = signMapper.selectWaitingList(selectCriteria);

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
}
