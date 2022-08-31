package com.ohgiraffers.intranet.sign.model.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
public class SignFileDTO {

    private int fileNo;
    private String originName;
    private String saveName;
    private String savePath;
    private String tempPath;
    private String deleteYn;
    private int signNo;

}
