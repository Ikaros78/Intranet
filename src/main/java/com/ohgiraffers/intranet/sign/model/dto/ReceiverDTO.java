package com.ohgiraffers.intranet.sign.model.dto;

import com.ohgiraffers.intranet.member.model.dto.MemberDTO;
import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class ReceiverDTO {

    private int signNo;
    private int receiverNo;

    private MemberDTO receiver;

}
