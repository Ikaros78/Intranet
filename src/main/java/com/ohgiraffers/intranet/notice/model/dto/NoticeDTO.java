package com.ohgiraffers.intranet.notice.model.dto;

import com.ohgiraffers.intranet.member.model.dto.MemberDTO;

import javax.naming.Name;
import java.sql.Date;

public class NoticeDTO {

    private int no;
    private String title;
    private Date date;
    private int views;
    private String contents;
    private MemberDTO member;
    private NoticeFileDTO file;

    public NoticeDTO() {
    }

    public NoticeDTO(int no, String title, Date date, int views, String contents) {
        this.no = no;
        this.title = title;
        this.date = date;
        this.views = views;
        this.contents = contents;
    }

    public NoticeDTO(int no, String title, Date date, int views, String contents, MemberDTO member, NoticeFileDTO file) {
        this.no = no;
        this.title = title;
        this.date = date;
        this.views = views;
        this.contents = contents;
        this.member = member;
        this.file = file;

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

    public MemberDTO getMember() {
        return member;
    }

    public void setMember(MemberDTO member) {
        this.member = member;
    }

    public NoticeFileDTO getFile() {
        return file;
    }

    public void setFile(NoticeFileDTO file) {
        this.file = file;
    }




}
