package com.ohgiraffers.intranet.sign.model.dto;

import com.ohgiraffers.intranet.member.model.dto.MemberDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class SignDTO {

    private int signNo;
    private int signWriter;
    private String signForm;
    private String signTitle;
    private String signContent;
    private java.sql.Date regDate;
    private String isTemp;

    private MemberDTO writer;
    private List<ApproverDTO> approver;
    private List<ReceiverDTO> receiver;
    private List<ReferencerDTO> referencer;
    private List<SignFileDTO> fileList;

}
