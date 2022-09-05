package com.ohgiraffers.intranet.notice.model.dto;

public class NewsFileDTO {

    private int no;
    private String originName;
    private String saveName;
    private String savePath;
    private String deleteYn;
    private int nwNo;

    public NewsFileDTO() {
    }

    public NewsFileDTO(int no, String originName, String saveName, String savePath, String deleteYn, int nwNo) {
        this.no = no;
        this.originName = originName;
        this.saveName = saveName;
        this.savePath = savePath;
        this.deleteYn = deleteYn;
        this.nwNo = nwNo;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public String getSaveName() {
        return saveName;
    }

    public void setSaveName(String saveName) {
        this.saveName = saveName;
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public String getDeleteYn() {
        return deleteYn;
    }

    public void setDeleteYn(String deleteYn) {
        this.deleteYn = deleteYn;
    }

    public int getNwNo() {
        return nwNo;
    }

    public void setNwNo(int nwNo) {
        this.nwNo = nwNo;
    }

    @Override
    public String toString() {
        return "NewsFileDTO{" +
                "no=" + no +
                ", originName='" + originName + '\'' +
                ", saveName='" + saveName + '\'' +
                ", savePath='" + savePath + '\'' +
                ", deleteYn='" + deleteYn + '\'' +
                ", nwNo=" + nwNo +
                '}';
    }
}
