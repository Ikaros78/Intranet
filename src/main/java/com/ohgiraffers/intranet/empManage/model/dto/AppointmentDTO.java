package com.ohgiraffers.intranet.empManage.model.dto;

import com.ohgiraffers.intranet.member.model.dto.MemberDTO;

import java.sql.Date;

public class AppointmentDTO {

    private int no;
    private String category;
    private Date date;
    private String contents;
    private String bef_rank;
    private int mem_num;
    private MemberDTO member;

    public AppointmentDTO() {
    }

    public AppointmentDTO(int no, String category, Date date, String contents, String bef_rank, int mem_num, MemberDTO member) {
        this.no = no;
        this.category = category;
        this.date = date;
        this.contents = contents;
        this.bef_rank = bef_rank;
        this.mem_num = mem_num;
        this.member = member;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getBef_rank() {
        return bef_rank;
    }

    public void setBef_rank(String bef_rank) {
        this.bef_rank = bef_rank;
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
        return "AppointmentDTO{" +
                "no=" + no +
                ", category='" + category + '\'' +
                ", date=" + date +
                ", contents='" + contents + '\'' +
                ", bef_rank='" + bef_rank + '\'' +
                ", mem_num=" + mem_num +
                ", member=" + member +
                '}';
    }
}
