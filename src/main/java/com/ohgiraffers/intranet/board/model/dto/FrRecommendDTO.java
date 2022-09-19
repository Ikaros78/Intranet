package com.ohgiraffers.intranet.board.model.dto;

public class FrRecommendDTO {

    private String fr_no;
    private int mem_num;
    private String like_yn;

    private int recCount;

    public FrRecommendDTO() {
    }

    public FrRecommendDTO(String fr_no, int mem_num, String like_yn, int recCount) {
        this.fr_no = fr_no;
        this.mem_num = mem_num;
        this.like_yn = like_yn;
        this.recCount = recCount;
    }

    public String getFr_no() {
        return fr_no;
    }

    public void setFr_no(String fr_no) {
        this.fr_no = fr_no;
    }

    public int getMem_num() {
        return mem_num;
    }

    public void setMem_num(int mem_num) {
        this.mem_num = mem_num;
    }

    public String getLike_yn() {
        return like_yn;
    }

    public void setLike_yn(String like_yn) {
        this.like_yn = like_yn;
    }

    public int getRecCount() {
        return recCount;
    }

    public void setRecCount(int recCount) {
        this.recCount = recCount;
    }

    @Override
    public String toString() {
        return "FrRecommendDTO{" +
                "fr_no='" + fr_no + '\'' +
                ", mem_num=" + mem_num +
                ", like_yn='" + like_yn + '\'' +
                ", recCount=" + recCount +
                '}';
    }
}
