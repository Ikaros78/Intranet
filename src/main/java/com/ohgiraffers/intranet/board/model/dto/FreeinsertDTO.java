package com.ohgiraffers.intranet.board.model.dto;

import com.ohgiraffers.intranet.member.model.dto.MemberDTO;

import java.util.Date;

public class FreeinsertDTO {

    private int no;

    private String title;

    private Date date;

    private int views;

    private String contents;

    private MemberDTO member;

    public FreeinsertDTO(){}

    public FreeinsertDTO(int no, String title, Date date, int views, String contents, MemberDTO member) {
        this.no = no;
        this.title = title;
        this.date = date;
        this.views = views;
        this.contents = contents;
        this.member = member;
    }

    public int getNo() {
        return no;
    }

    public String getTitle() {
        return title;
    }

    public Date getDate() {
        return date;
    }

    public int getViews() {
        return views;
    }

    public String getContents() {
        return contents;
    }

    public MemberDTO getMember() {
        return member;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public void setMember(MemberDTO member) {
        this.member = member;
    }

    @Override
    public String toString() {
        return "FreeinsertDTO{" +
                "no=" + no +
                ", title='" + title + '\'' +
                ", date=" + date +
                ", views=" + views +
                ", contents='" + contents + '\'' +
                ", member=" + member +
                '}';
    }
}
