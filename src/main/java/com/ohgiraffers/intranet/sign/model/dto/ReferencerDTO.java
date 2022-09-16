package com.ohgiraffers.intranet.sign.model.dto;

public class ReferencerDTO {

    private int signNo;
    private int referencerNo;

    private ReferencerInfoDTO referencer;

    public ReferencerDTO() {
    }

    public ReferencerDTO(int signNo, int referencerNo, ReferencerInfoDTO referencer) {
        this.signNo = signNo;
        this.referencerNo = referencerNo;
        this.referencer = referencer;
    }

    public int getSignNo() {
        return signNo;
    }

    public void setSignNo(int signNo) {
        this.signNo = signNo;
    }

    public int getReferencerNo() {
        return referencerNo;
    }

    public void setReferencerNo(int referencerNo) {
        this.referencerNo = referencerNo;
    }

    public ReferencerInfoDTO getReferencer() {
        return referencer;
    }

    public void setReferencer(ReferencerInfoDTO referencer) {
        this.referencer = referencer;
    }

    @Override
    public String toString() {
        return "ReferencerDTO{" +
                "signNo=" + signNo +
                ", referencerNo=" + referencerNo +
                ", referencer=" + referencer +
                '}';
    }
}
