package com.ohgiraffers.intranet.sign.model.dto;

import com.ohgiraffers.intranet.member.model.dto.MemberDTO;

import java.sql.Date;
import java.util.List;

public class SignDTO {

    private int signNo;
    private int signWriter;
    private String signFormCode;
    private String signTitle;
    private String signContent;
    private java.sql.Date regDate;
    private String isTemp;

    private MemberDTO writer;
    private SignFormDTO signForm;
    private SignFileDTO file;
    private List<ApproverDTO> approver;
    private List<ReceiverDTO> receiver;
    private List<ReferencerDTO> referencer;

    public SignDTO() {
    }

    public SignDTO(int signNo, int signWriter, String signFormCode, String signTitle, String signContent, Date regDate, String isTemp, MemberDTO writer, SignFormDTO signForm, SignFileDTO file, List<ApproverDTO> approver, List<ReceiverDTO> receiver, List<ReferencerDTO> referencer) {
        this.signNo = signNo;
        this.signWriter = signWriter;
        this.signFormCode = signFormCode;
        this.signTitle = signTitle;
        this.signContent = signContent;
        this.regDate = regDate;
        this.isTemp = isTemp;
        this.writer = writer;
        this.signForm = signForm;
        this.file = file;
        this.approver = approver;
        this.receiver = receiver;
        this.referencer = referencer;
    }

    public int getSignNo() {
        return signNo;
    }

    public void setSignNo(int signNo) {
        this.signNo = signNo;
    }

    public int getSignWriter() {
        return signWriter;
    }

    public void setSignWriter(int signWriter) {
        this.signWriter = signWriter;
    }

    public String getSignFormCode() {
        return signFormCode;
    }

    public void setSignFormCode(String signFormCode) {
        this.signFormCode = signFormCode;
    }

    public String getSignTitle() {
        return signTitle;
    }

    public void setSignTitle(String signTitle) {
        this.signTitle = signTitle;
    }

    public String getSignContent() {
        return signContent;
    }

    public void setSignContent(String signContent) {
        this.signContent = signContent;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public String getIsTemp() {
        return isTemp;
    }

    public void setIsTemp(String isTemp) {
        this.isTemp = isTemp;
    }

    public MemberDTO getWriter() {
        return writer;
    }

    public void setWriter(MemberDTO writer) {
        this.writer = writer;
    }

    public SignFormDTO getSignForm() {
        return signForm;
    }

    public void setSignForm(SignFormDTO signForm) {
        this.signForm = signForm;
    }

    public List<ApproverDTO> getApprover() {
        return approver;
    }

    public void setApprover(List<ApproverDTO> approver) {
        this.approver = approver;
    }

    public List<ReceiverDTO> getReceiver() {
        return receiver;
    }

    public void setReceiver(List<ReceiverDTO> receiver) {
        this.receiver = receiver;
    }

    public List<ReferencerDTO> getReferencer() {
        return referencer;
    }

    public void setReferencer(List<ReferencerDTO> referencer) {
        this.referencer = referencer;
    }

    public SignFileDTO getFile() {
        return file;
    }

    public void setFile(SignFileDTO file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "SignDTO{" +
                "signNo=" + signNo +
                ", signWriter=" + signWriter +
                ", signFormCode='" + signFormCode + '\'' +
                ", signTitle='" + signTitle + '\'' +
                ", signContent='" + signContent + '\'' +
                ", regDate=" + regDate +
                ", isTemp='" + isTemp + '\'' +
                ", writer=" + writer +
                ", signForm=" + signForm +
                ", file=" + file +
                ", approver=" + approver +
                ", receiver=" + receiver +
                ", referencer=" + referencer +
                '}';
    }
}
