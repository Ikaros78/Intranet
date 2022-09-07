package com.ohgiraffers.intranet.member.model.dto;

import com.ohgiraffers.intranet.authorManage.model.dto.AuthoritDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

/* 세션에 들어간 정보를 받기 위한 User객체 생성용 클래스 */
public class UserImpl extends User {

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
    private List<AuthoritDTO> authorit;
    private DepartmentDTO department;
    private Au_AuthorizationDTO au_authorization;

    /* 유저 id, pw, 권한을 담는 UserImpl (id, pw, 권한만 담겨집니다) */
    public UserImpl(String mem_id, String mem_pw, Collection<? extends GrantedAuthority> authorities){
        super(mem_id, mem_pw, authorities);
    }

    /* mapper 객체를 전달받아 필드를 다 초기화 해주는 메소드 */
    public void setDetails(MemberDTO memberDTO){

        this.mem_num = memberDTO.getMem_num();
        this.mem_id = memberDTO.getMem_id();
        this.mem_pw = memberDTO.getMem_pw();
        this.mem_name = memberDTO.getMem_name();
        this.mem_phone = memberDTO.getMem_phone();
        this.mem_joinDate = memberDTO.getMem_joinDate();
        this.mem_birth = memberDTO.getMem_birth();
        this.mem_email = memberDTO.getMem_email();
        this.mem_address = memberDTO.getMem_address();
        this.dept_rank = memberDTO.getDept_rank();
        this.dept_code = memberDTO.getDept_code();
        this.authorit = memberDTO.getAuthorit();
        this.department = memberDTO.getDepartment();
        this.au_authorization = memberDTO.getAu_authorization();

    }

    public int getMem_num() {
        return mem_num;
    }

    public String getMem_id() {
        return mem_id;
    }

    public String getMem_pw() {
        return mem_pw;
    }

    public String getMem_name() {
        return mem_name;
    }

    public String getMem_phone() {
        return mem_phone;
    }

    public Date getMem_joinDate() {
        return mem_joinDate;
    }

    public Date getMem_birth() {
        return mem_birth;
    }

    public String getMem_email() {
        return mem_email;
    }

    public String getMem_address() {
        return mem_address;
    }

    public String getDept_rank() {
        return dept_rank;
    }

    public String getDept_code() {
        return dept_code;
    }

    public List<AuthoritDTO> getAuthorit() {
        return authorit;
    }

    public DepartmentDTO getDepartment() {
        return department;
    }

    public Au_AuthorizationDTO getAu_authorization() {
        return au_authorization;
    }

    @Override
    public String toString() {
        return "UserImpl{" +
                "mem_num=" + mem_num +
                ", mem_id='" + mem_id + '\'' +
                ", mem_pw='" + mem_pw + '\'' +
                ", mem_name='" + mem_name + '\'' +
                ", mem_phone='" + mem_phone + '\'' +
                ", mem_joinDate=" + mem_joinDate +
                ", mem_birth=" + mem_birth +
                ", mem_email='" + mem_email + '\'' +
                ", mem_address='" + mem_address + '\'' +
                ", dept_rank='" + dept_rank + '\'' +
                ", dept_code='" + dept_code + '\'' +
                ", authorit=" + authorit +
                ", department=" + department +
                ", au_authorization=" + au_authorization +
                '}';
    }
}
