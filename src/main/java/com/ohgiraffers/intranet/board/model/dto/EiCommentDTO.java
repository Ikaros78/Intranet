package com.ohgiraffers.intranet.board.model.dto;

import com.ohgiraffers.intranet.member.model.dto.MemberDTO;

import java.sql.Date;

public class EiCommentDTO {

    private String nc_no;

    private String contents;

    private Date date;

    private int mem_num;

    private MemberDTO member;

    private String nb_no;

    public EiCommentDTO() {}

    public EiCommentDTO(String nc_no, String contents, Date date, int mem_num, MemberDTO member, String nb_no) {
        this.nc_no = nc_no;
        this.contents = contents;
        this.date = date;
        this.mem_num = mem_num;
        this.member = member;
        this.nb_no = nb_no;
    }

    public String getNc_no() {
        return nc_no;
    }

    public void setNc_no(String nc_no) {
        this.nc_no = nc_no;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getMem_num() {
        return mem_num;
    }

    public void setMem_num(int mem_num) {
        this.mem_num = mem_num;
    }

    public MemberDTO getMember() {
        return member;
    }

    public void setMember(MemberDTO member) {
        this.member = member;
    }

    public String getNb_no() {
        return nb_no;
    }

    public void setNb_no(String nb_no) {
        this.nb_no = nb_no;
    }

    @Override
    public String toString() {
        return "EiCommentDTO{" +
                "nc_no='" + nc_no + '\'' +
                ", contents='" + contents + '\'' +
                ", date=" + date +
                ", mem_num=" + mem_num +
                ", member=" + member +
                ", nb_no='" + nb_no + '\'' +
                '}';
    }
}
