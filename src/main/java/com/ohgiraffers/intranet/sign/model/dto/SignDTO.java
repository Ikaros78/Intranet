package com.ohgiraffers.intranet.sign.model.dto;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class SignDTO {

    private int signNo;
    private int signWriter;
    private String signTitle;
    private String signContent;
    private java.sql.Date regDate;
    private String isTemp;

}
