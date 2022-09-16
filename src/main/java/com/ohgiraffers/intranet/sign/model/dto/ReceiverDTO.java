package com.ohgiraffers.intranet.sign.model.dto;

public class ReceiverDTO {

    private int signNo;
    private int receiverNo;

    private ReceiverInfoDTO receiver;

    public ReceiverDTO() {
    }

    public ReceiverDTO(int signNo, int receiverNo, ReceiverInfoDTO receiver) {
        this.signNo = signNo;
        this.receiverNo = receiverNo;
        this.receiver = receiver;
    }

    public int getSignNo() {
        return signNo;
    }

    public void setSignNo(int signNo) {
        this.signNo = signNo;
    }

    public int getReceiverNo() {
        return receiverNo;
    }

    public void setReceiverNo(int receiverNo) {
        this.receiverNo = receiverNo;
    }

    public ReceiverInfoDTO getReceiver() {
        return receiver;
    }

    public void setReceiver(ReceiverInfoDTO receiver) {
        this.receiver = receiver;
    }

    @Override
    public String toString() {
        return "ReceiverDTO{" +
                "signNo=" + signNo +
                ", receiverNo=" + receiverNo +
                ", receiver=" + receiver +
                '}';
    }
}
