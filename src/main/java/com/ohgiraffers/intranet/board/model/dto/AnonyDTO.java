package com.ohgiraffers.intranet.board.model.dto;

import com.ohgiraffers.intranet.member.model.dto.MemberDTO;

import java.sql.Date;

public class AnonyDTO {

    private String no;

    private String title;

    private Date date;

    private int views;

    private String contents;

    private MemberDTO member;

    private int mem_num;

    public AnonyDTO() {

    }

    public AnonyDTO(String no, String title, Date date, int views, String contents, MemberDTO member, int mem_num) {
        this.no = no;
        this.title = title;
        this.date = date;
        this.views = views;
        this.contents = contents;
        this.member = member;
        this.mem_num = mem_num;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public MemberDTO getMember() {
        return member;
    }

    public void setMember(MemberDTO member) {
        this.member = member;
    }

    public int getMem_num() {
        return mem_num;
    }

    public void setMem_num(int mem_num) {
        this.mem_num = mem_num;
    }

    @Override
    public String toString() {
        return "AnonyDTO{" +
                "no='" + no + '\'' +
                ", title='" + title + '\'' +
                ", date=" + date +
                ", views=" + views +
                ", contents='" + contents + '\'' +
                ", member=" + member +
                ", memnum=" + mem_num +
                '}';
    }
}
