package com.ohgiraffers.intranet.msBoard.model.dto;

import java.sql.Date;

public class MsBoardDTO {

	private int msNo;
	private String msTitle;
	private String sendName;
	private String recpName;
	private Date sendDate;
	private Date recpDate;
	private String readYn;
	private String gubun;
	private int memNum;
	public MsBoardDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MsBoardDTO(int msNo, String msTitle, String sendName, String recpName, Date sendDate, Date recpDate,
			String readYn, String gubun, int memNum) {
		super();
		this.msNo = msNo;
		this.msTitle = msTitle;
		this.sendName = sendName;
		this.recpName = recpName;
		this.sendDate = sendDate;
		this.recpDate = recpDate;
		this.readYn = readYn;
		this.gubun = gubun;
		this.memNum = memNum;
	}
	public int getMsNo() {
		return msNo;
	}
	public void setMsNo(int msNo) {
		this.msNo = msNo;
	}
	public String getMsTitle() {
		return msTitle;
	}
	public void setMsTitle(String msTitle) {
		this.msTitle = msTitle;
	}
	public String getSendName() {
		return sendName;
	}
	public void setSendName(String sendName) {
		this.sendName = sendName;
	}
	public String getRecpName() {
		return recpName;
	}
	public void setRecpName(String recpName) {
		this.recpName = recpName;
	}
	public Date getSendDate() {
		return sendDate;
	}
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	public Date getRecpDate() {
		return recpDate;
	}
	public void setRecpDate(Date recpDate) {
		this.recpDate = recpDate;
	}
	public String getReadYn() {
		return readYn;
	}
	public void setReadYn(String readYn) {
		this.readYn = readYn;
	}
	public String getGubun() {
		return gubun;
	}
	public void setGubun(String gubun) {
		this.gubun = gubun;
	}
	public int getMemNum() {
		return memNum;
	}
	public void setMemNum(int memNum) {
		this.memNum = memNum;
	}
	@Override
	public String toString() {
		return "MsBoardDTO [msNo=" + msNo + ", msTitle=" + msTitle + ", sendName=" + sendName + ", recpName=" + recpName
				+ ", sendDate=" + sendDate + ", recpDate=" + recpDate + ", readYn=" + readYn + ", gubun=" + gubun
				+ ", memNum=" + memNum + "]";
	}
	
	
}
