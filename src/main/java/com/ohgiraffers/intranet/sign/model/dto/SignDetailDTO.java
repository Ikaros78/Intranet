package com.ohgiraffers.intranet.sign.model.dto;


import com.ohgiraffers.intranet.member.model.dto.MemberDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class SignDetailDTO {

    private int signNo;
    private int signWriter;
    private String signTitle;
    private String writerName;
    private String signFormCode;
    private String signContent;
    private java.sql.Date regDate;
    private String isTemp;
    private String approverName;

    private MemberDTO writer;
    private SignFormDTO signForm;
    private List<ApproverDTO> approverList;
    private List<ReceiverDTO> receiverList;
    private List<ReferencerDTO> referencerList;
    private List<SignFileDTO> fileList;

}
