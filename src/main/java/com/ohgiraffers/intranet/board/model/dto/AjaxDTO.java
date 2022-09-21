package com.ohgiraffers.intranet.board.model.dto;


public class AjaxDTO {
        
    private String ATTRIBUTE1;
    private String ATTRIBUTE2;
    private String ATTRIBUTE3;
    private String ATTRIBUTE4;
    private String ATTRIBUTE5;
    private String ATTRIBUTE6;

    private String A_ACTION;
    private String A_CALLBACK;

    public AjaxDTO() {}

    public AjaxDTO(String ATTRIBUTE1, String ATTRIBUTE2, String ATTRIBUTE3,
                   String ATTRIBUTE4, String ATTRIBUTE5, String ATTRIBUTE6,
                   String A_ACTION, String A_CALLBACK) {
        this.ATTRIBUTE1 = ATTRIBUTE1;
        this.ATTRIBUTE2 = ATTRIBUTE2;
        this.ATTRIBUTE3 = ATTRIBUTE3;
        this.ATTRIBUTE4 = ATTRIBUTE4;
        this.ATTRIBUTE5 = ATTRIBUTE5;
        this.ATTRIBUTE6 = ATTRIBUTE6;
        this.A_ACTION = A_ACTION;
        this.A_CALLBACK = A_CALLBACK;
    }

    public String getATTRIBUTE1() {
        return ATTRIBUTE1;
    }

    public void setATTRIBUTE1(String ATTRIBUTE1) {
        this.ATTRIBUTE1 = ATTRIBUTE1;
    }

    public String getATTRIBUTE2() {
        return ATTRIBUTE2;
    }

    public void setATTRIBUTE2(String ATTRIBUTE2) {
        this.ATTRIBUTE2 = ATTRIBUTE2;
    }

    public String getATTRIBUTE3() {
        return ATTRIBUTE3;
    }

    public void setATTRIBUTE3(String ATTRIBUTE3) {
        this.ATTRIBUTE3 = ATTRIBUTE3;
    }

    public String getATTRIBUTE4() {
        return ATTRIBUTE4;
    }

    public void setATTRIBUTE4(String ATTRIBUTE4) {
        this.ATTRIBUTE4 = ATTRIBUTE4;
    }

    public String getATTRIBUTE5() {
        return ATTRIBUTE5;
    }

    public void setATTRIBUTE5(String ATTRIBUTE5) {
        this.ATTRIBUTE5 = ATTRIBUTE5;
    }

    public String getATTRIBUTE6() {
        return ATTRIBUTE6;
    }

    public void setATTRIBUTE6(String ATTRIBUTE6) {
        this.ATTRIBUTE6 = ATTRIBUTE6;
    }

    public String getA_ACTION() {
        return A_ACTION;
    }

    public void setA_ACTION(String A_ACTION) {
        this.A_ACTION = A_ACTION;
    }

    public String getA_CALLBACK() {
        return A_CALLBACK;
    }

    public void setA_CALLBACK(String A_CALLBACK) {
        this.A_CALLBACK = A_CALLBACK;
    }

    @Override
    public String toString() {
        return "AjaxDTO{" +
                "ATTRIBUTE1='" + ATTRIBUTE1 + '\'' +
                ", ATTRIBUTE2='" + ATTRIBUTE2 + '\'' +
                ", ATTRIBUTE3='" + ATTRIBUTE3 + '\'' +
                ", ATTRIBUTE4='" + ATTRIBUTE4 + '\'' +
                ", ATTRIBUTE5='" + ATTRIBUTE5 + '\'' +
                ", ATTRIBUTE6='" + ATTRIBUTE6 + '\'' +
                ", A_ACTION='" + A_ACTION + '\'' +
                ", A_CALLBACK='" + A_CALLBACK + '\'' +
                '}';
    }
}
