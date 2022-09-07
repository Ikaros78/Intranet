package com.ohgiraffers.intranet.notice.model.dto;

import com.ohgiraffers.intranet.member.model.dto.DepartmentDTO;
import com.ohgiraffers.intranet.member.model.dto.MemberDTO;

import java.sql.Date;
import java.util.List;

public class GalleryDTO {

    private int no;
    private String title;
    private Date date;
    private int views;
    private String contents;
    private String mem_num;
    private MemberDTO member;
    private DepartmentDTO dept;
    private List<GalleryFileDTO> galleryFile;

    public GalleryDTO() {
    }

    public GalleryDTO(int no, String title, Date date, int views, String contents, String mem_num, MemberDTO member, DepartmentDTO dept, List<GalleryFileDTO> galleryFile) {
        this.no = no;
        this.title = title;
        this.date = date;
        this.views = views;
        this.contents = contents;
        this.mem_num = mem_num;
        this.member = member;
        this.dept = dept;
        this.galleryFile = galleryFile;
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

    public String getMem_num() {
        return mem_num;
    }

    public void setMem_num(String mem_num) {
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

    public List<GalleryFileDTO> getGalleryFile() {
        return galleryFile;
    }

    public void setGalleryFile(List<GalleryFileDTO> galleryFile) {
        this.galleryFile = galleryFile;
    }

    @Override
    public String toString() {
        return "GalleryDTO{" +
                "no=" + no +
                ", title='" + title + '\'' +
                ", date=" + date +
                ", views=" + views +
                ", contents='" + contents + '\'' +
                ", mem_num='" + mem_num + '\'' +
                ", member=" + member +
                ", dept=" + dept +
                ", galleryFile=" + galleryFile +
                '}';
    }
}


