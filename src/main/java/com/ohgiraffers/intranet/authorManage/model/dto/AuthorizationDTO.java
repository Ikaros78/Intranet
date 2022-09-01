package com.ohgiraffers.intranet.authorManage.model.dto;

import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class AuthorizationDTO {

    private String auCode;
    private String auName;
    private String auContents;
}
