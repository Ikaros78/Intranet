package com.ohgiraffers.intranet.authorManage.model.dto;
/* 인사권한 관리를 위해 만든 DTO */
public class AuthoritTypeDTO {

    private String em_all;
    private String em_read;
    private String hr_all;
    private String hr_read;
    private String nm;
    private String hr_em_auth;
    private String nt_db_auth;
    private String cd_auth;
    private String nt_all;
    private String db_all;
    private String cd_all;
    private String cd_dept;

    public AuthoritTypeDTO() {
    }

    public AuthoritTypeDTO(String em_all, String em_read, String hr_all, String hr_read, String nm, String hr_em_auth, String nt_db_auth, String cd_auth, String nt_all, String db_all, String cd_all, String cd_dept) {
        this.em_all = em_all;
        this.em_read = em_read;
        this.hr_all = hr_all;
        this.hr_read = hr_read;
        this.nm = nm;
        this.hr_em_auth = hr_em_auth;
        this.nt_db_auth = nt_db_auth;
        this.cd_auth = cd_auth;
        this.nt_all = nt_all;
        this.db_all = db_all;
        this.cd_all = cd_all;
        this.cd_dept = cd_dept;
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

    public String getHr_em_auth() {
        return hr_em_auth;
    }

    public void setHr_em_auth(String hr_em_auth) {
        this.hr_em_auth = hr_em_auth;
    }

    public String getNt_db_auth() {
        return nt_db_auth;
    }

    public void setNt_db_auth(String nt_db_auth) {
        this.nt_db_auth = nt_db_auth;
    }

    public String getCd_auth() {
        return cd_auth;
    }

    public void setCd_auth(String cd_auth) {
        this.cd_auth = cd_auth;
    }

    public String getNt_all() {
        return nt_all;
    }

    public void setNt_all(String nt_all) {
        this.nt_all = nt_all;
    }

    public String getDb_all() {
        return db_all;
    }

    public void setDb_all(String db_all) {
        this.db_all = db_all;
    }

    public String getCd_all() {
        return cd_all;
    }

    public void setCd_all(String cd_all) {
        this.cd_all = cd_all;
    }

    public String getCd_dept() {
        return cd_dept;
    }

    public void setCd_dept(String cd_dept) {
        this.cd_dept = cd_dept;
    }

    @Override
    public String toString() {
        return "AuthoritTypeDTO{" +
                "em_all='" + em_all + '\'' +
                ", em_read='" + em_read + '\'' +
                ", hr_all='" + hr_all + '\'' +
                ", hr_read='" + hr_read + '\'' +
                ", nm='" + nm + '\'' +
                ", hr_em_auth='" + hr_em_auth + '\'' +
                ", nt_db_auth='" + nt_db_auth + '\'' +
                ", cd_auth='" + cd_auth + '\'' +
                ", nt_all='" + nt_all + '\'' +
                ", db_all='" + db_all + '\'' +
                ", cd_all='" + cd_all + '\'' +
                ", cd_dept='" + cd_dept + '\'' +
                '}';
    }
}
