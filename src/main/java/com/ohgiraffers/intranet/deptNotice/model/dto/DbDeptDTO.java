package com.ohgiraffers.intranet.deptNotice.model.dto;

import com.ohgiraffers.intranet.member.model.dto.DepartmentDTO;
import com.ohgiraffers.intranet.member.model.dto.MemberDTO;

import java.sql.Date;

public class DbDeptDTO {

    private int db_no;
    private String db_title;
    private Date db_date;
    private int db_views;
    private String db_contents;
    private int mem_num;

    private MemberDTO member;
    private DepartmentDTO dept;

    public DbDeptDTO() {
    }

    public DbDeptDTO(int db_no, String db_title, Date db_date, int db_views, String db_contents, int mem_num, MemberDTO member, DepartmentDTO dept) {
        this.db_no = db_no;
        this.db_title = db_title;
        this.db_date = db_date;
        this.db_views = db_views;
        this.db_contents = db_contents;
        this.mem_num = mem_num;
        this.member = member;
        this.dept = dept;
    }

    public int getDb_no() {
        return db_no;
    }

    public void setDb_no(int db_no) {
        this.db_no = db_no;
    }

    public String getDb_title() {
        return db_title;
    }

    public void setDb_title(String db_title) {
        this.db_title = db_title;
    }

    public Date getDb_date() {
        return db_date;
    }

    public void setDb_date(Date db_date) {
        this.db_date = db_date;
    }

    public int getDb_views() {
        return db_views;
    }

    public void setDb_views(int db_views) {
        this.db_views = db_views;
    }

    public String getDb_contents() {
        return db_contents;
    }

    public void setDb_contents(String db_contents) {
        this.db_contents = db_contents;
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

    public DepartmentDTO getDept() {
        return dept;
    }

    public void setDept(DepartmentDTO dept) {
        this.dept = dept;
    }

    @Override
    public String toString() {
        return "DbDeptDTO{" +
                "db_no=" + db_no +
                ", db_title='" + db_title + '\'' +
                ", db_date=" + db_date +
                ", db_views=" + db_views +
                ", db_contents='" + db_contents + '\'' +
                ", mem_num=" + mem_num +
                ", member=" + member +
                ", dept=" + dept +
                '}';
    }
}
