package com.ohgiraffers.intranet.board.model.dto;

import com.ohgiraffers.intranet.member.model.dto.MemberDTO;

import java.sql.Date;

public class CommentDTO {

    private String fc_no;

    private String contents;

    private Date date;

    private int mem_num;

    private MemberDTO member;

    private String fr_no;

    public CommentDTO() {
    }





    public CommentDTO(String fc_no, String contents, Date date, int mem_num, MemberDTO member, String fr_no) {
        this.fc_no = fc_no;
        this.contents = contents;
        this.date = date;
        this.mem_num = mem_num;
        this.member = member;
        this.fr_no = fr_no;
    }

    public String getFc_no() {
        return fc_no;
    }

    public void setFc_no(String fc_no) {
        this.fc_no = fc_no;
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

    public String getFr_no() {
        return fr_no;
    }

    public void setFr_no(String fr_no) {
        this.fr_no = fr_no;
    }

    @Override
    public String toString() {
        return "CommentDTO{" +
                "fc_no='" + fc_no + '\'' +
                ", contents='" + contents + '\'' +
                ", date=" + date +
                ", mem_num=" + mem_num +
                ", member=" + member +
                ", fr_no='" + fr_no + '\'' +
                '}';
    }
}
