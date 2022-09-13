package com.ohgiraffers.intranet.member.model.dto;

public class Au_AuthorizationDTO {

    private String au_code;
    private String au_name;
    private String au_contents;

    public Au_AuthorizationDTO() {
    }

    public Au_AuthorizationDTO(String au_code, String au_name, String au_contents) {
        this.au_code = au_code;
        this.au_name = au_name;
        this.au_contents = au_contents;
    }

    public String getAu_code() {
        return au_code;
    }

    public void setAu_code(String au_code) {
        this.au_code = au_code;
    }

    public String getAu_name() {
        return au_name;
    }

    public void setAu_name(String au_name) {
        this.au_name = au_name;
    }

    public String getAu_contents() {
        return au_contents;
    }

    public void setAu_contents(String au_contents) {
        this.au_contents = au_contents;
    }

    @Override
    public String toString() {
        return "Au_AuthorizationDTO{" +
                "au_code='" + au_code + '\'' +
                ", au_name='" + au_name + '\'' +
                ", au_contents='" + au_contents + '\'' +
                '}';
    }
}