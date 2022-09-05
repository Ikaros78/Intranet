package com.ohgiraffers.intranet.member.model.dto;

public class Au_AuthorizationDTO {

    private String all_all;
    private String nt_all;
    private String db_all;
    private String fr_all;
    private String nb_all;
    private String em_read;
    private String em_all;
    private String hr_read;
    private String hr_all;
    private String cd_all;
    private String cd_dept;

    public Au_AuthorizationDTO() {}

    public Au_AuthorizationDTO(String all_all, String nt_all, String db_all, String fr_all, String nb_all, String em_read, String em_all, String hr_read, String hr_all, String cd_all, String cd_dept) {
        this.all_all = all_all;
        this.nt_all = nt_all;
        this.db_all = db_all;
        this.fr_all = fr_all;
        this.nb_all = nb_all;
        this.em_read = em_read;
        this.em_all = em_all;
        this.hr_read = hr_read;
        this.hr_all = hr_all;
        this.cd_all = cd_all;
        this.cd_dept = cd_dept;
    }

    public String getAll_all() {
        return all_all;
    }

    public void setAll_all(String all_all) {
        this.all_all = all_all;
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

    public String getFr_all() {
        return fr_all;
    }

    public void setFr_all(String fr_all) {
        this.fr_all = fr_all;
    }

    public String getNb_all() {
        return nb_all;
    }

    public void setNb_all(String nb_all) {
        this.nb_all = nb_all;
    }

    public String getEm_read() {
        return em_read;
    }

    public void setEm_read(String em_read) {
        this.em_read = em_read;
    }

    public String getEm_all() {
        return em_all;
    }

    public void setEm_all(String em_all) {
        this.em_all = em_all;
    }

    public String getHr_read() {
        return hr_read;
    }

    public void setHr_read(String hr_read) {
        this.hr_read = hr_read;
    }

    public String getHr_all() {
        return hr_all;
    }

    public void setHr_all(String hr_all) {
        this.hr_all = hr_all;
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
        return "Au_AuthorizationDTO{" +
                "all_all='" + all_all + '\'' +
                ", nt_all='" + nt_all + '\'' +
                ", db_all='" + db_all + '\'' +
                ", fr_all='" + fr_all + '\'' +
                ", nb_all='" + nb_all + '\'' +
                ", em_read='" + em_read + '\'' +
                ", em_all='" + em_all + '\'' +
                ", hr_read='" + hr_read + '\'' +
                ", hr_all='" + hr_all + '\'' +
                ", cd_all='" + cd_all + '\'' +
                ", cd_dept='" + cd_dept + '\'' +
                '}';
    }

}
