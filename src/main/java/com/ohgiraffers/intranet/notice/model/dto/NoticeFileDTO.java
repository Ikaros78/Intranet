package com.ohgiraffers.intranet.notice.model.dto;

public class NoticeFileDTO {

    private int no;
    private String originName;
    private String saveName;
    private String savePath;
    private String deleteYn;
    private int ntNo;

    public NoticeFileDTO() {
    }

    public NoticeFileDTO(int no, String originName, String saveName, String savePath, String deleteYn, int ntNo) {
        this.no = no;
        this.originName = originName;
        this.saveName = saveName;
        this.savePath = savePath;
        this.deleteYn = deleteYn;
        this.ntNo = ntNo;
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

    public int getNtNo() {
        return ntNo;
    }

    public void setNtNo(int ntNo) {
        this.ntNo = ntNo;
    }

    @Override
    public String toString() {
        return "NoticeFileDTO{" +
                "no=" + no +
                ", originName='" + originName + '\'' +
                ", saveName='" + saveName + '\'' +
                ", savePath='" + savePath + '\'' +
                ", deleteYn='" + deleteYn + '\'' +
                ", ntNo=" + ntNo +
                '}';
    }
}

