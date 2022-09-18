package com.ohgiraffers.intranet.board.model.dto;

import com.ohgiraffers.intranet.member.model.dto.MemberDTO;

import java.sql.Date;

public class CommentDTO {

    private String no;

    private String contents;

    private Date date;

    private int mem_num;

    private MemberDTO member;

   public CommentDTO() {}

    public CommentDTO(String no, String contents, Date date, int mem_num, MemberDTO member) {
        this.no = no;
        this.contents = contents;
        this.date = date;
        this.mem_num = mem_num;
        this.member = member;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
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

    @Override
    public String toString() {
        return "CommentDTO{" +
                "no='" + no + '\'' +
                ", contents='" + contents + '\'' +
                ", date=" + date +
                ", mem_num=" + mem_num +
                ", member=" + member +
                '}';
    }
}
