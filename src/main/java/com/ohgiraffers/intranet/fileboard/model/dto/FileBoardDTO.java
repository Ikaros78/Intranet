package com.ohgiraffers.intranet.fileboard.model.dto;

import java.sql.Date;

import com.ohgiraffers.intranet.member.model.dto.DepartmentDTO;
import com.ohgiraffers.intranet.member.model.dto.MemberDTO;
import com.ohgiraffers.intranet.notice.model.dto.NoticeFileDTO;

public class FileBoardDTO {
	
	private int fbNo;
	private String fbTitle;
	private Date fbDate;
	private int fbViews;
	private String fbContent;
	private int memnum;
    private MemberDTO member;
    private FileFileDTO file;
    private DepartmentDTO dept;
	public FileBoardDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FileBoardDTO(int fbNo, String fbTitle, Date fbDate, int fbViews, String fbContent, int memnum,
			MemberDTO member, FileFileDTO file, DepartmentDTO dept) {
		super();
		this.fbNo = fbNo;
		this.fbTitle = fbTitle;
		this.fbDate = fbDate;
		this.fbViews = fbViews;
		this.fbContent = fbContent;
		this.memnum = memnum;
		this.member = member;
		this.file = file;
		this.dept = dept;
	}
	public int getFbNo() {
		return fbNo;
	}
	public void setFbNo(int fbNo) {
		this.fbNo = fbNo;
	}
	public String getFbTitle() {
		return fbTitle;
	}
	public void setFbTitle(String fbTitle) {
		this.fbTitle = fbTitle;
	}
	public Date getFbDate() {
		return fbDate;
	}
	public void setFbDate(Date fbDate) {
		this.fbDate = fbDate;
	}
	public int getFbViews() {
		return fbViews;
	}
	public void setFbViews(int fbViews) {
		this.fbViews = fbViews;
	}
	public String getFbContent() {
		return fbContent;
	}
	public void setFbContent(String fbContent) {
		this.fbContent = fbContent;
	}
	public int getMemnum() {
		return memnum;
	}
	public void setMemnum(int memnum) {
		this.memnum = memnum;
	}
	public MemberDTO getMember() {
		return member;
	}
	public void setMember(MemberDTO member) {
		this.member = member;
	}
	public FileFileDTO getFile() {
		return file;
	}
	public void setFile(FileFileDTO file) {
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
		return "FileBoardDTO [fbNo=" + fbNo + ", fbTitle=" + fbTitle + ", fbDate=" + fbDate + ", fbViews=" + fbViews
				+ ", fbContent=" + fbContent + ", memnum=" + memnum + ", member=" + member + ", file=" + file
				+ ", dept=" + dept + "]";
	}

	
}
