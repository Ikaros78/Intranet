package com.ohgiraffers.intranet.authorManage.model.dto;

import com.ohgiraffers.intranet.member.model.dto.Au_AuthorizationDTO;
import com.ohgiraffers.intranet.member.model.dto.MemberDTO;
import lombok.*;

import java.util.List;


public class AuthoritDTO {

    private int memNum;
    private String auCode;
    private Au_AuthorizationDTO au_authorization;
    @ToString.Exclude private MemberDTO member;

	public AuthoritDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AuthoritDTO(int memNum, String auCode, Au_AuthorizationDTO au_authorization) {
		super();
		this.memNum = memNum;
		this.auCode = auCode;
		this.au_authorization = au_authorization;
	}
	public int getMemNum() {
		return memNum;
	}
	public void setMemNum(int memNum) {
		this.memNum = memNum;
	}
	public String getAuCode() {
		return auCode;
	}
	public void setAuCode(String auCode) {
		this.auCode = auCode;
	}
	public Au_AuthorizationDTO getAu_authorization() {
		return au_authorization;
	}
	public void setAu_authorization(Au_AuthorizationDTO au_authorization) {
		this.au_authorization = au_authorization;
	}
	@Override
	public String toString() {
		return "AuthoritDTO [memNum=" + memNum + ", auCode=" + auCode + ", au_authorization=" + au_authorization + "]";
	}

}
