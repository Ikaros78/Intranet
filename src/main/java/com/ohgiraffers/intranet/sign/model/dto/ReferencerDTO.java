package com.ohgiraffers.intranet.sign.model.dto;

import com.ohgiraffers.intranet.member.model.dto.MemberDTO;
import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class ReferencerDTO {

    private int signNo;
    private int referencerNo;

    private ReferencerInfoDTO referencer;

}
