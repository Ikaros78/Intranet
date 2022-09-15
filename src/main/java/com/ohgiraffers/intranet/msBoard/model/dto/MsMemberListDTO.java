package com.ohgiraffers.intranet.msBoard.model.dto;

public class MsMemberListDTO {

	private int memNum;
	private String name;
	private String deptCode;
	private String deptName;
	
	public MsMemberListDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MsMemberListDTO(int memNum, String name, String deptCode, String deptName) {
		super();
		this.memNum = memNum;
		this.name = name;
		this.deptCode = deptCode;
		this.deptName = deptName;
	}
	public int getMemNum() {
		return memNum;
	}
	public void setMemNum(int memNum) {
		this.memNum = memNum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	@Override
	public String toString() {
		return "MsMemberListDTO [memNum=" + memNum + ", name=" + name + ", deptCode=" + deptCode + ", deptName="
				+ deptName + "]";
	}

	
	
	
}
