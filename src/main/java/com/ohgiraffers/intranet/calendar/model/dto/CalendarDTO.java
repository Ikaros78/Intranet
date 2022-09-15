package com.ohgiraffers.intranet.calendar.model.dto;

import java.util.Date;

public class CalendarDTO {
/* 입력 받는 값은 풀캘린더에서 같은 값을 사용해야해서 고정값입니다.
*  예외 -> memNum, type, wDate는 예외로 추가적으로 필요해 받는 데이터
* */
    private int memNum;  // 회원번호
    private int id;  // 글 번호
    private String groupId;
    private String title;
    private String content;
    private String start;
    private String end;
    private String allDay;
    private String color;
    private String type;
    private String wDate;
    private String deptCode;

    public CalendarDTO() {

    }

    public CalendarDTO(int memNum, int id, String groupId, String title, String content, String start, String end, String allDay, String color, String type, String wDate, String deptCode) {
        this.memNum = memNum;
        this.id = id;
        this.groupId = groupId;
        this.title = title;
        this.content = content;
        this.start = start;
        this.end = end;
        this.allDay = allDay;
        this.color = color;
        this.type = type;
        this.wDate = wDate;
        this.deptCode = deptCode;
    }

    public int getMemNum() {
        return memNum;
    }

    public void setMemNum(int memNum) {
        this.memNum = memNum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getAllDay() {
        return allDay;
    }

    public void setAllDay(String allDay) {
        this.allDay = allDay;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getwDate() {
        return wDate;
    }

    public void setwDate(String wDate) {
        this.wDate = wDate;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    @Override
    public String toString() {
        return "CalendarDTO{" +
                "memNum=" + memNum +
                ", id=" + id +
                ", groupId='" + groupId + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", start='" + start + '\'' +
                ", end='" + end + '\'' +
                ", allDay='" + allDay + '\'' +
                ", color='" + color + '\'' +
                ", type='" + type + '\'' +
                ", wDate='" + wDate + '\'' +
                ", deptCode='" + deptCode + '\'' +
                '}';
    }
}
