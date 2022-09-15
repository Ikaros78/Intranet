package com.ohgiraffers.intranet.authorManage.model.dto;
/* 인사권한 관리를 위해 만든 DTO */
public class AuthoritTypeDTO {

    private String em_all;
    private String em_read;
    private String hr_all;
    private String hr_read;
    private String nm;

    public AuthoritTypeDTO() {
    }

    public AuthoritTypeDTO(String em_all, String em_read, String hr_all, String hr_read, String nm) {
        this.em_all = em_all;
        this.em_read = em_read;
        this.hr_all = hr_all;
        this.hr_read = hr_read;
        this.nm = nm;
    }

    public String getEm_all() {
        return em_all;
    }

    public void setEm_all(String em_all) {
        this.em_all = em_all;
    }

    public String getEm_read() {
        return em_read;
    }

    public void setEm_read(String em_read) {
        this.em_read = em_read;
    }

    public String getHr_all() {
        return hr_all;
    }

    public void setHr_all(String hr_all) {
        this.hr_all = hr_all;
    }

    public String getHr_read() {
        return hr_read;
    }

    public void setHr_read(String hr_read) {
        this.hr_read = hr_read;
    }

    public String getNm() {
        return nm;
    }

    public void setNm(String nm) {
        this.nm = nm;
    }

    @Override
    public String toString() {
        return "AuthoritTypeDTO{" +
                "em_all='" + em_all + '\'' +
                ", em_read='" + em_read + '\'' +
                ", hr_all='" + hr_all + '\'' +
                ", hr_read='" + hr_read + '\'' +
                ", nm='" + nm + '\'' +
                '}';
    }
}
