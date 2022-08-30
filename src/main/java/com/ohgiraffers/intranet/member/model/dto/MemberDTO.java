package com.ohgiraffers.intranet.member.model.dto;

import java.sql.Date;

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
    private int number;

    public MemberDTO() {
    }

    public MemberDTO(int mem_num, String mem_id, String mem_pw, String mem_name, String mem_phone, Date mem_joinDate, Date mem_birth, String mem_email, String mem_address, String dept_rank, int number) {
        this.mem_num = mem_num;
        this.mem_id = mem_id;
        this.mem_pw = mem_pw;
        this.mem_name = mem_name;
        this.mem_phone = mem_phone;
        this.mem_joinDate = mem_joinDate;
        this.mem_birth = mem_birth;
        this.mem_email = mem_email;
        this.mem_address = mem_address;
        this.dept_rank = dept_rank;
        this.number = number;
    }

    public int getMem_num() {
        return mem_num;
    }

    public void setMem_num(int mem_num) {
        this.mem_num = mem_num;
    }

    public String getMem_id() {
        return mem_id;
    }

    public void setMem_id(String mem_id) {
        this.mem_id = mem_id;
    }

    public String getMem_pw() {
        return mem_pw;
    }

    public void setMem_pw(String mem_pw) {
        this.mem_pw = mem_pw;
    }

    public String getMem_name() {
        return mem_name;
    }

    public void setMem_name(String mem_name) {
        this.mem_name = mem_name;
    }

    public String getMem_phone() {
        return mem_phone;
    }

    public void setMem_phone(String mem_phone) {
        this.mem_phone = mem_phone;
    }

    public Date getMem_joinDate() {
        return mem_joinDate;
    }

    public void setMem_joinDate(Date mem_joinDate) {
        this.mem_joinDate = mem_joinDate;
    }

    public Date getMem_birth() {
        return mem_birth;
    }

    public void setMem_birth(Date mem_birth) {
        this.mem_birth = mem_birth;
    }

    public String getMem_email() {
        return mem_email;
    }

    public void setMem_email(String mem_email) {
        this.mem_email = mem_email;
    }

    public String getMem_address() {
        return mem_address;
    }

    public void setMem_address(String mem_address) {
        this.mem_address = mem_address;
    }

    public String getDept_rank() {
        return dept_rank;
    }

    public void setDept_rank(String dept_rank) {
        this.dept_rank = dept_rank;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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
                ", number=" + number +
                '}';
    }
}
