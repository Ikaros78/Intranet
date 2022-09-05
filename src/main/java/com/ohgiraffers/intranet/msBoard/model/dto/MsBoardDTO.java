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
	private String sendDel;
	private String recpDel;
	private String recpNum;
	private String contents;
	private int memNum;
	public MsBoardDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MsBoardDTO(int msNo, String msTitle, String sendName, String recpName, Date sendDate, Date recpDate,
			String readYn, String sendDel, String recpDel, String recpNum, String contents, int memNum) {
		super();
		this.msNo = msNo;
		this.msTitle = msTitle;
		this.sendName = sendName;
		this.recpName = recpName;
		this.sendDate = sendDate;
		this.recpDate = recpDate;
		this.readYn = readYn;
		this.sendDel = sendDel;
		this.recpDel = recpDel;
		this.recpNum = recpNum;
		this.contents = contents;
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
	public String getSendDel() {
		return sendDel;
	}
	public void setSendDel(String sendDel) {
		this.sendDel = sendDel;
	}
	public String getRecpDel() {
		return recpDel;
	}
	public void setRecpDel(String recpDel) {
		this.recpDel = recpDel;
	}
	public String getRecpNum() {
		return recpNum;
	}
	public void setRecpNum(String recpNum) {
		this.recpNum = recpNum;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
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
				+ ", sendDate=" + sendDate + ", recpDate=" + recpDate + ", readYn=" + readYn + ", sendDel=" + sendDel
				+ ", recpDel=" + recpDel + ", recpNum=" + recpNum + ", contents=" + contents + ", memNum=" + memNum
				+ "]";
	}

}
