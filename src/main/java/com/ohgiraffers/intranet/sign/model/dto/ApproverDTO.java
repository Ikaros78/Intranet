package com.ohgiraffers.intranet.sign.model.dto;

import java.sql.Date;

public class ApproverDTO {

    private int signNo;
    private int approverNo;
    private String isApproved;
    private String isRefused;
    private String isLastApprover;
    private java.sql.Date approveDate;
    private String opinion;
    private String signType;

    private ApproverInfoDTO approver;

    public ApproverDTO() {
    }

    public ApproverDTO(int signNo, int approverNo, String isApproved, String isRefused, String isLastApprover, Date approveDate, String opinion, String signType, ApproverInfoDTO approver) {
        this.signNo = signNo;
        this.approverNo = approverNo;
        this.isApproved = isApproved;
        this.isRefused = isRefused;
        this.isLastApprover = isLastApprover;
        this.approveDate = approveDate;
        this.opinion = opinion;
        this.signType = signType;
        this.approver = approver;
    }

    public int getSignNo() {
        return signNo;
    }

    public void setSignNo(int signNo) {
        this.signNo = signNo;
    }

    public int getApproverNo() {
        return approverNo;
    }

    public void setApproverNo(int approverNo) {
        this.approverNo = approverNo;
    }

    public String getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(String isApproved) {
        this.isApproved = isApproved;
    }

    public String getIsRefused() {
        return isRefused;
    }

    public void setIsRefused(String isRefused) {
        this.isRefused = isRefused;
    }

    public String getIsLastApprover() {
        return isLastApprover;
    }

    public void setIsLastApprover(String isLastApprover) {
        this.isLastApprover = isLastApprover;
    }

    public Date getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(Date approveDate) {
        this.approveDate = approveDate;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public ApproverInfoDTO getApprover() {
        return approver;
    }

    public void setApprover(ApproverInfoDTO approver) {
        this.approver = approver;
    }

    @Override
    public String toString() {
        return "ApproverDTO{" +
                "signNo=" + signNo +
                ", approverNo=" + approverNo +
                ", isApproved='" + isApproved + '\'' +
                ", isRefused='" + isRefused + '\'' +
                ", isLastApprover='" + isLastApprover + '\'' +
                ", approveDate=" + approveDate +
                ", opinion='" + opinion + '\'' +
                ", signType='" + signType + '\'' +
                ", approver=" + approver +
                '}';
    }
}
