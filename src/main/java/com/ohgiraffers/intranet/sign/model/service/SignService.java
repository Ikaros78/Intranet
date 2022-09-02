package com.ohgiraffers.intranet.sign.model.service;

import com.ohgiraffers.intranet.sign.model.dao.SignMapper;
import com.ohgiraffers.intranet.sign.model.dto.SignDTO;
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
    public int selectTotalWaitingCount(Map<String, String> searchMap) {

        int result = signMapper.selectTotalWaitingCount(searchMap);

        return result;
    }

    /* 결재대기함 전체 조회용 메소드 */
    public List<SignDTO> selectWaitingList(Map<String, Object> selectCriteria) {

        List<SignDTO> waitingList = signMapper.selectWaitingList(selectCriteria);

        return waitingList;
    }
}
