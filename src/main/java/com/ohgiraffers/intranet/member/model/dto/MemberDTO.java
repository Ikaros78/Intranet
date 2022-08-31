package com.ohgiraffers.intranet.member.model.dto;

import com.ohgiraffers.intranet.authorManage.model.dto.AuthoritDTO;
import lombok.*;

import java.sql.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {

    private int mem_num;
    private String mem_id;
    private String mem_pw;
    private String mem_name;
    private String mem_phone;
    private java.sql.Date mem_joinDate;
    private java.sql.Date mem_birth;
    private String mem_email;
    private String mem_address;
    private String dept_rank;
    private String dept_code;
    
    // 권한 관리를 위해 추가
    private AuthoritDTO authorit;
}

