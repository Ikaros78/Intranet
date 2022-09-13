package com.ohgiraffers.intranet.sign.model.dto;

import com.ohgiraffers.intranet.member.model.dto.DepartmentDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class ReceiverInfoDTO {

    private int mem_num;
    private String mem_id;
    private String mem_pw;
    private String receiverName;
    private String mem_phone;
    private java.sql.Date mem_joinDate;
    private java.sql.Date mem_birth;
    private String mem_email;
    private String mem_address;
    private String dept_rank;
    private String dept_code;

}
