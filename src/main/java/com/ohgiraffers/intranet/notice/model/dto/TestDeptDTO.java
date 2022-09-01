package com.ohgiraffers.intranet.notice.model.dto;

public class TestDeptDTO {

    private String deptCode;
    private String deptName;

    public TestDeptDTO() {
    }

    public TestDeptDTO(String deptCode, String deptName) {
        this.deptCode = deptCode;
        this.deptName = deptName;
    }

    @Override
    public String toString() {
        return "testDeptDTO{" +
                "deptCode='" + deptCode + '\'' +
                ", deptName='" + deptName + '\'' +
                '}';
    }
}
