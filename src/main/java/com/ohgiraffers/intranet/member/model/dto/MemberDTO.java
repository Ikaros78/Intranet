package com.ohgiraffers.intranet.member.model.dto;

import com.ohgiraffers.intranet.authorManage.model.dto.AuthoritDTO;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

public class MemberDTO implements UserDetails {

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
    private String dept_name;

    // 공지사항 부서명 조회를 위해 추가
    private List<DepartmentDTO> department;

    // 권한 관리를 위해 추가
    private List<AuthoritDTO> authorit;

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

    public String getDept_name() {
        return dept_name;
    }

    public List<DepartmentDTO> getDepartment() {
        return department;
    }

    public List<AuthoritDTO> getAuthorit() {
        return authorit;
    }

    public void setMem_num(int mem_num) {
        this.mem_num = mem_num;
    }

    public void setMem_id(String mem_id) {
        this.mem_id = mem_id;
    }

    public void setMem_pw(String mem_pw) {
        this.mem_pw = mem_pw;
    }

    public void setMem_name(String mem_name) {
        this.mem_name = mem_name;
    }

    public void setMem_phone(String mem_phone) {
        this.mem_phone = mem_phone;
    }

    public void setMem_joinDate(Date mem_joinDate) {
        this.mem_joinDate = mem_joinDate;
    }

    public void setMem_birth(Date mem_birth) {
        this.mem_birth = mem_birth;
    }

    public void setMem_email(String mem_email) {
        this.mem_email = mem_email;
    }

    public void setMem_address(String mem_address) {
        this.mem_address = mem_address;
    }

    public void setDept_rank(String dept_rank) {
        this.dept_rank = dept_rank;
    }

    public void setDept_code(String dept_code) {
        this.dept_code = dept_code;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    public void setDepartment(List<DepartmentDTO> department) {
        this.department = department;
    }

    public void setAuthorit(List<AuthoritDTO> authorit) {
        this.authorit = authorit;
    }

    @Override
    public String toString() {
        return "MemberDTO{" +
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
                ", dept_name='" + dept_name + '\'' +
                ", department=" + department +
                ", authorit=" + authorit +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return mem_pw;
    }

    @Override
    public String getUsername() {
        return mem_id;
    }

    // 계정 만료 여부 -> true. 만료되지 않음.
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정 잠김 여부 -> true. 잠기지 않음.
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 비밀번호 만료 여부 -> true. 만료되지 않음.
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정 활성화 여부
    @Override
    public boolean isEnabled() {
        return true;
    }


}
