package com.ohgiraffers.intranet.board.model.dao;

import com.ohgiraffers.intranet.board.model.dto.*;
import com.ohgiraffers.intranet.common.paging.SelectCriteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardMapper {


   

    int freeinsert(FreeinsertDTO freeinsert);


    int selectTotalCount(Map<String, String> searchMap);

    List<FreeinsertDTO> selectBoardList(SelectCriteria selectCriteria);

    int incresementBoardCount(String no);

    FreeinsertDTO selectBoardDetail(String no);

    int boardUpdate(FreeinsertDTO board);

    int selectUpdate(FreeinsertDTO freeinsert);

    void boardDelete(String no);

    List<AnonyDTO> selectBoardListe(SelectCriteria selectCriteria);

    int selectTotalCounte(Map<String, String> searchMap);

    int anonyinsert(AnonyDTO anonyinsert);


    AnonyDTO selecteiBoardDetail(String no);

    int incresementeiBoardCount(String no);

    int boardeiUpdatePag(AnonyDTO anonyinsert);

    void eiboardDelete(String no);


    int boardcomment(CommentDTO boardcomment);

    List<CommentDTO> selectComentList(String no);

    int boardeicomment(EiCommentDTO boardeicomment);

    List<EiCommentDTO> selecteiComentList(String nb_no);

    void eeiboardDelete(String nb_no);

    void eiboardDelet(String no);

    void eicommentDelete(String no);

    void boardcommentDelete(String no);

    int cajax(AjaxDTO cajax);

    List<SelAjaxDTO> ajaxBlacklistCnt(AjaxDTO cajax);
    List<SelAjaxDTO> ajaxBlacklistCntNB(AjaxDTO cajax);
    List<SelAjaxDTO> ajaxBlacklistUserListName(AjaxDTO cajax);
    List<SelAjaxDTO> ajaxBlacklistUserListEnum(AjaxDTO cajax);
    List<SelAjaxDTO> ajaxBlacklistDetailList(AjaxDTO cajax);
    int ajaxBlacklistFRInsert(AjaxDTO cajax);
    int ajaxBlacklistFRInsertDetail(AjaxDTO cajax);
    int ajaxBlacklistNBInsert(AjaxDTO cajax);
    int ajaxBlacklistNBInsertDetail(AjaxDTO cajax);
}


