package com.ohgiraffers.intranet.notice.model.dto;

import com.ohgiraffers.intranet.member.model.dto.MemberDTO;

import java.util.Date;
import java.util.List;

public class NoticeDTO {

    private int no;
    private String title;
    private java.util.Date date;
    private int views;
    private String contents;

    private List<MemberDTO> member;

    public NoticeDTO() {
    }

    public NoticeDTO(int no, String title, Date date, int views, String contents) {
        this.no = no;
        this.title = title;
        this.date = date;
        this.views = views;
        this.contents = contents;
    }

    public NoticeDTO(int no, String title, Date date, int views, String contents, List<MemberDTO> member) {
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

    public void setNo(int no) {
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

    public List<MemberDTO> getMember() {
        return member;
    }

    public void setMember(List<MemberDTO> member) {
        this.member = member;
    }

    @Override
    public String toString() {
        return "NoticeDTO{" +
                "no=" + no +
                ", title='" + title + '\'' +
                ", date=" + date +
                ", views=" + views +
                ", contents='" + contents + '\'' +
                ", member=" + member +
                '}';
    }
}
