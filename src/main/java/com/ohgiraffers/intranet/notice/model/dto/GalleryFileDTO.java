package com.ohgiraffers.intranet.notice.model.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class GalleryFileDTO {

    private int no;
    private String originName;
    private String saveName;
    private String savePath;
    private String deleteYn;
    private String thumbnailPath;
    private int galNo;
}


