package com.ohgiraffers.intranet.sign.model.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@RequiredArgsConstructor
public class SignFormDTO {

    private String code;
    private String name;

}
