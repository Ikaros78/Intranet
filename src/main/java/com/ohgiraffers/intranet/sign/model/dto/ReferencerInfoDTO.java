package com.ohgiraffers.intranet.sign.model.dto;

import java.sql.Date;

public class ReferencerInfoDTO {

    private int mem_num;
    private String mem_id;
    private String mem_pw;
    private String referencerName;
    private String mem_phone;
    private java.sql.Date mem_joinDate;
    private java.sql.Date mem_birth;
    private String mem_email;
    private String mem_address;
    private String referencerRank;
    private String dept_code;

    public ReferencerInfoDTO() {
    }

    public ReferencerInfoDTO(int mem_num, String mem_id, String mem_pw, String referencerName, String mem_phone, Date mem_joinDate, Date mem_birth, String mem_email, String mem_address, String referencerRank, String dept_code) {
        this.mem_num = mem_num;
        this.mem_id = mem_id;
        this.mem_pw = mem_pw;
        this.referencerName = referencerName;
        this.mem_phone = mem_phone;
        this.mem_joinDate = mem_joinDate;
        this.mem_birth = mem_birth;
        this.mem_email = mem_email;
        this.mem_address = mem_address;
        this.referencerRank = referencerRank;
        this.dept_code = dept_code;
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

    public String getReferencerName() {
        return referencerName;
    }

    public void setReferencerName(String referencerName) {
        this.referencerName = referencerName;
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

    public String getReferencerRank() {
        return referencerRank;
    }

    public void setReferencerRank(String referencerRank) {
        this.referencerRank = referencerRank;
    }

    public String getDept_code() {
        return dept_code;
    }

    public void setDept_code(String dept_code) {
        this.dept_code = dept_code;
    }

    @Override
    public String toString() {
        return "ReferencerInfoDTO{" +
                "mem_num=" + mem_num +
                ", mem_id='" + mem_id + '\'' +
                ", mem_pw='" + mem_pw + '\'' +
                ", referencerName='" + referencerName + '\'' +
                ", mem_phone='" + mem_phone + '\'' +
                ", mem_joinDate=" + mem_joinDate +
                ", mem_birth=" + mem_birth +
                ", mem_email='" + mem_email + '\'' +
                ", mem_address='" + mem_address + '\'' +
                ", referencerRank='" + referencerRank + '\'' +
                ", dept_code='" + dept_code + '\'' +
                '}';
    }
}
