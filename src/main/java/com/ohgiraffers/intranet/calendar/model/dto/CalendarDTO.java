package com.ohgiraffers.intranet.calendar.model.dto;

import java.util.Date;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class CalendarDTO {
/* 입력 받는 값은 풀캘린더에서 같은 값을 사용해야해서 고정값입니다.
*  예외 -> memNum, type, wDate는 예외로 추가적으로 필요해 받는 데이터
* */
    private int memNum;
    private int id;
    private String groupId;
    private String title;
    private String content;
    private String start;
    private String end;
    private String allDay;
    private String color;
    private String type;
    private java.util.Date wDate;


}
