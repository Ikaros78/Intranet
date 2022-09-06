package com.ohgiraffers.intranet.authorManage.model.dto;

import com.ohgiraffers.intranet.member.model.dto.Au_AuthorizationDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class AuthoritDTO {

    private int memNum;
    private String auCode;
    private List<Au_AuthorizationDTO> au_authorization;

}
