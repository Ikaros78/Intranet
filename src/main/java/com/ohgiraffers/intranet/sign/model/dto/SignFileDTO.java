package com.ohgiraffers.intranet.sign.model.dto;

public class SignFileDTO {

    private int fileNo;
    private String originName;
    private String saveName;
    private String savePath;
    private String tempPath;
    private String deleteYn;
    private int signNo;

    public SignFileDTO() {
    }

    public SignFileDTO(int fileNo, String originName, String saveName, String savePath, String tempPath, String deleteYn, int signNo) {
        this.fileNo = fileNo;
        this.originName = originName;
        this.saveName = saveName;
        this.savePath = savePath;
        this.tempPath = tempPath;
        this.deleteYn = deleteYn;
        this.signNo = signNo;
    }

    public int getFileNo() {
        return fileNo;
    }

    public void setFileNo(int fileNo) {
        this.fileNo = fileNo;
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

    public String getTempPath() {
        return tempPath;
    }

    public void setTempPath(String tempPath) {
        this.tempPath = tempPath;
    }

    public String getDeleteYn() {
        return deleteYn;
    }

    public void setDeleteYn(String deleteYn) {
        this.deleteYn = deleteYn;
    }

    public int getSignNo() {
        return signNo;
    }

    public void setSignNo(int signNo) {
        this.signNo = signNo;
    }

    @Override
    public String toString() {
        return "SignFileDTO{" +
                "fileNo=" + fileNo +
                ", originName='" + originName + '\'' +
                ", saveName='" + saveName + '\'' +
                ", savePath='" + savePath + '\'' +
                ", tempPath='" + tempPath + '\'' +
                ", deleteYn='" + deleteYn + '\'' +
                ", signNo=" + signNo +
                '}';
    }
}
