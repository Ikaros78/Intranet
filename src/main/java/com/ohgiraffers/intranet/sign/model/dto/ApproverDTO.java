package com.ohgiraffers.intranet.sign.model.dto;

import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class ApproverDTO {

    private int signNo;
    private int approverNo;
    private String isApproved;
    private java.sql.Date approveDate;
    private String opinion;
    private String signType;
}