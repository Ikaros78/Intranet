package com.ohgiraffers.intranet.sign.model.service;

import com.ohgiraffers.intranet.sign.model.dao.SignMapper;
import org.springframework.stereotype.Service;

@Service
public class SignService {

    private SignMapper signMapper;

    public SignService(SignMapper signMapper){

        this.signMapper = signMapper;
    }
}
