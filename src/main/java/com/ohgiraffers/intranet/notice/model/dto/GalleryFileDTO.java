package com.ohgiraffers.intranet.notice.model.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public class GalleryFileDTO {

    private int no;
    private String originName;
    private String saveName;
    private String savePath;
    private String deleteYn;
    private String thumbnailPath;
    private int galNo;
    private String fileType;

    public GalleryFileDTO() {
    }

    public GalleryFileDTO(int no, String originName, String saveName, String savePath, String deleteYn, String thumbnailPath, int galNo, String fileType) {
        this.no = no;
        this.originName = originName;
        this.saveName = saveName;
        this.savePath = savePath;
        this.deleteYn = deleteYn;
        this.thumbnailPath = thumbnailPath;
        this.galNo = galNo;
        this.fileType = fileType;
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

    public String getThumbnailPath() {
        return thumbnailPath;
    }

    public void setThumbnailPath(String thumbnailPath) {
        this.thumbnailPath = thumbnailPath;
    }

    public int getGalNo() {
        return galNo;
    }

    public void setGalNo(int galNo) {
        this.galNo = galNo;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    @Override
    public String toString() {
        return "GalleryFileDTO{" +
                "no=" + no +
                ", originName='" + originName + '\'' +
                ", saveName='" + saveName + '\'' +
                ", savePath='" + savePath + '\'' +
                ", deleteYn='" + deleteYn + '\'' +
                ", thumbnailPath='" + thumbnailPath + '\'' +
                ", galNo=" + galNo +
                ", fileType='" + fileType + '\'' +
                '}';
    }
}


