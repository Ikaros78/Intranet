package com.ohgiraffers.intranet.fileboard.model.dto;

public class FileFileDTO {
	
	private int ffNo;
	private String ffOriginName;
	private String ffSaveName;
	private String ffSavePath;
	private String deleteYn;
	private int fbNo;
	public FileFileDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FileFileDTO(int ffNo, String ffOriginName, String ffSavePath, String deleteYn, String ffSaveName, int fbNo) {
		super();
		this.ffNo = ffNo;
		this.ffOriginName = ffOriginName;
		this.ffSavePath = ffSavePath;
		this.deleteYn = deleteYn;
		this.ffSaveName = ffSaveName;
		this.fbNo = fbNo;
	}
	public int getFfNo() {
		return ffNo;
	}
	public void setFfNo(int ffNo) {
		this.ffNo = ffNo;
	}
	public String getFfOriginName() {
		return ffOriginName;
	}
	public void setFfOriginName(String ffOriginName) {
		this.ffOriginName = ffOriginName;
	}
	public String getFfSavePath() {
		return ffSavePath;
	}
	public void setFfSavePath(String ffSavePath) {
		this.ffSavePath = ffSavePath;
	}
	public String getDeleteYn() {
		return deleteYn;
	}
	public void setDeleteYn(String deleteYn) {
		this.deleteYn = deleteYn;
	}
	public String getFfSaveName() {
		return ffSaveName;
	}
	public void setFfSaveName(String ffSaveName) {
		this.ffSaveName = ffSaveName;
	}
	public int getFbNo() {
		return fbNo;
	}
	public void setFbNo(int fbNo) {
		this.fbNo = fbNo;
	}
	@Override
	public String toString() {
		return "FileFileDTO [ffNo=" + ffNo + ", ffOriginName=" + ffOriginName + ", ffSavePath=" + ffSavePath
				+ ", deleteYn=" + deleteYn + ", ffSaveName=" + ffSaveName + ", fbNo=" + fbNo + "]";
	}
	
}
