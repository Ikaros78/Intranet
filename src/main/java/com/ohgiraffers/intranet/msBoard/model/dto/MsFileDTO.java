package com.ohgiraffers.intranet.msBoard.model.dto;

import java.sql.Date;

public class MsFileDTO {

	private int fileNo;
	private String originName;
	private String saveName;
	private String savePath;
	private Date noDate;
	private String deleteYn;
	private int msNo;
	public MsFileDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MsFileDTO(int fileNo, String originName, String saveName, String savePath, Date noDate, String deleteYn,
			int msNo) {
		super();
		this.fileNo = fileNo;
		this.originName = originName;
		this.saveName = saveName;
		this.savePath = savePath;
		this.noDate = noDate;
		this.deleteYn = deleteYn;
		this.msNo = msNo;
	}
	public int getFileNo() {
		return fileNo;
	}
	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}
	public String getOriginName() {
		return originName;
	}
	public void setOriginName(String originName) {
		this.originName = originName;
	}
	public String getSaveName() {
		return saveName;
	}
	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}
	public String getSavePath() {
		return savePath;
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	public Date getNoDate() {
		return noDate;
	}
	public void setNoDate(Date noDate) {
		this.noDate = noDate;
	}
	public String getDeleteYn() {
		return deleteYn;
	}
	public void setDeleteYn(String deleteYn) {
		this.deleteYn = deleteYn;
	}
	public int getMsNo() {
		return msNo;
	}
	public void setMsNo(int msNo) {
		this.msNo = msNo;
	}
	@Override
	public String toString() {
		return "MsFileDTO [fileNo=" + fileNo + ", originName=" + originName + ", saveName=" + saveName + ", savePath="
				+ savePath + ", noDate=" + noDate + ", deleteYn=" + deleteYn + ", msNo=" + msNo + "]";
	}

	
}
