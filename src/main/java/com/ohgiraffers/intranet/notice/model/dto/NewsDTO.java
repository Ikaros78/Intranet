package com.ohgiraffers.intranet.notice.model.dto;

import com.ohgiraffers.intranet.member.model.dto.DepartmentDTO;
import com.ohgiraffers.intranet.member.model.dto.MemberDTO;

import java.sql.Date;

public class NewsDTO {

    private int no;
    private String title;
    private Date date;
    private int views;
    private String contents;
    private int mem_num;
    private MemberDTO member;
    private NewsFileDTO file;
    private DepartmentDTO dept;

    public NewsDTO() {
    }

    public NewsDTO(int no, String title, Date date, int views, String contents, int mem_num, MemberDTO member, NewsFileDTO file, DepartmentDTO dept) {
        this.no = no;
        this.title = title;
        this.date = date;
        this.views = views;
        this.contents = contents;
        this.mem_num = mem_num;
        this.member = member;
        this.file = file;
        this.dept = dept;
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

    public NewsFileDTO getFile() {
        return file;
    }

    public void setFile(NewsFileDTO file) {
        this.file = file;
    }

    public DepartmentDTO getDept() {
        return dept;
    }

    public void setDept(DepartmentDTO dept) {
        this.dept = dept;
    }

    @Override
    public String toString() {
        return "NewsDTO{" +
                "no=" + no +
                ", title='" + title + '\'' +
                ", date=" + date +
                ", views=" + views +
                ", contents='" + contents + '\'' +
                ", mem_num=" + mem_num +
                ", member=" + member +
                ", file=" + file +
                ", dept=" + dept +
                '}';
    }
}
