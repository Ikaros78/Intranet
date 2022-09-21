package com.ohgiraffers.intranet.board.model.service;

import com.ohgiraffers.intranet.board.model.dao.BoardMapper;
import com.ohgiraffers.intranet.board.model.dto.*;
import com.ohgiraffers.intranet.common.paging.SelectCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service("freeinsertService")
public class BoardService {

    private final BoardMapper boardMapper;

    public BoardService(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }

    /* 신고 접수 메소드 */
    @Transactional
    public int singoInsert(SingoDTO singo) {

        return boardMapper.singoInsert(singo);
    }

    @Transactional
    public int freeinsert(FreeinsertDTO freeinsert) {

        return boardMapper.freeinsert(freeinsert);
    }

    public int selectTotalCount(Map<String, String> searchMap) {

        int result = boardMapper.selectTotalCount(searchMap);

        return result;
    }

    public List<FreeinsertDTO> selectBoardList(SelectCriteria selectCriteria) {

        List<FreeinsertDTO> boardList = boardMapper.selectBoardList(selectCriteria);

        return boardList;
    }

    @Transactional
    public FreeinsertDTO selectBoardDetail(String no) {

        FreeinsertDTO boardDetail = null;

        int result = boardMapper.incresementBoardCount(no); //조회수 증가

        if (result > 0) {
            boardDetail = boardMapper.selectBoardDetail(no);
        }
        return boardDetail;
    }




    @Transactional
    public int boardUpdate(FreeinsertDTO freeinsert) {
        return boardMapper.selectUpdate(freeinsert);

    }



//    익명

    public List<AnonyDTO> selectBoardListe(SelectCriteria selectCriteria) {
        List<AnonyDTO> eiboardList = boardMapper.selectBoardListe(selectCriteria);

        return eiboardList;
    }


    public int selectTotalCounte(Map<String, String> searchMap) {

        int result = boardMapper.selectTotalCounte(searchMap);

        return result;

    }

    @Transactional
    public int anonyinsert(AnonyDTO anonyinsert) {

        return boardMapper.anonyinsert(anonyinsert);
    }


    public AnonyDTO selecteiBoardDetail(String no) {

        AnonyDTO eiboardDetail = null;

        int result = boardMapper.incresementeiBoardCount(no); //조회수 증가

        if (result > 0) {
            eiboardDetail = boardMapper.selecteiBoardDetail(no);
        }
        return eiboardDetail;
    }

    public AnonyDTO selectboardeiDetail(String no) {

        AnonyDTO eiboardDetail = null;
        eiboardDetail = boardMapper.selecteiBoardDetail(no);

        return eiboardDetail;

    }

    public int eiboardUpdate(AnonyDTO anonyinsert) {
        return boardMapper.boardeiUpdatePag(anonyinsert);

    }

//    public void eiboardDelete(String no) {
//
//      String result = boardMapper.eiboardDelete(no);
//      
//        if(result != null ){
//            
//        boardMapper.eicommentDelete(no);
//        }
//    }

    @Transactional
    public int boardcomment(CommentDTO boardcomment) {

       int reult = boardMapper.boardcomment(boardcomment);

       return reult;


    }





    public FreeinsertDTO selectboardDetail(String no) {

        FreeinsertDTO boardDetail = null;

        int result = boardMapper.incresementBoardCount(no); //조회수 증가

        if (result > 0) {
            boardDetail = boardMapper.selectBoardDetail(no);
        }
        return boardDetail;
    }

    public List<CommentDTO> selectComentList(String fr_no) {
        List<CommentDTO> commentlist = boardMapper.selectComentList(fr_no);

       System.out.println("commentlist" + commentlist);
               return commentlist;
       }
//        익명
    public int boardeicomment(EiCommentDTO boardeicomment) {

        int reult = boardMapper.boardeicomment(boardeicomment);

        return reult;

    }
//    익명
    public List<EiCommentDTO> selecteiComentList(String nb_no) {
        List<EiCommentDTO> eicommentlist = boardMapper.selecteiComentList(nb_no);

        System.out.println("eicommentlist" + eicommentlist);
        return eicommentlist;

    }

@Transactional
    public void eiboardDelete(String no) {

      boardMapper.eeiboardDelete(no);
      boardMapper.eiboardDelete(no);

    }
@Transactional
    public void boardDelete(String no) {

    boardMapper.boardcommentDelete(no);
    boardMapper.boardDelete(no);


    }

    public List<SelAjaxDTO> selectAjax(AjaxDTO ajaxDTO) {
        List<SelAjaxDTO> SelAjaxList = null;

        // 블랙리스트 있는지 여부 확인
        if(ajaxDTO.getA_ACTION().equals("ajaxBlacklistCnt")) {
            SelAjaxList = boardMapper.ajaxBlacklistCnt(ajaxDTO);
        }
        // 블랙리스트 있는지 여부 확인
        else if(ajaxDTO.getA_ACTION().equals("ajaxBlacklistCntNB")) {
            SelAjaxList = boardMapper.ajaxBlacklistCntNB(ajaxDTO);
        }

        // 블랙리스트 유저 이름 검색
        else if(ajaxDTO.getA_ACTION().equals("ajaxBlacklistUserListName")) {
            SelAjaxList = boardMapper.ajaxBlacklistUserListName(ajaxDTO);
        }

        // 블랙리스트 유저 사원번호 검색
        else if(ajaxDTO.getA_ACTION().equals("ajaxBlacklistUserListEnum")) {
            SelAjaxList = boardMapper.ajaxBlacklistUserListEnum(ajaxDTO);
        }

        else if(ajaxDTO.getA_ACTION().equals("ajaxBlacklistDetailList")) {
            SelAjaxList = boardMapper.ajaxBlacklistDetailList(ajaxDTO);
        }

        System.out.println("SelAjaxList" + SelAjaxList);
        return SelAjaxList;
    }

    @Transactional
    public int cajax(AjaxDTO cajax) {

        String ACTION = cajax.getA_ACTION();

        int reult = 0;

        if(ACTION.equals("ajaxBlacklistFRInsert")) {
            reult = boardMapper.ajaxBlacklistFRInsert(cajax);
        }

        else if(ACTION.equals("ajaxBlacklistFRInsertDetail")) {
            reult = boardMapper.ajaxBlacklistFRInsertDetail(cajax);
        }

        else if(ACTION.equals("ajaxBlacklistNBInsert")) {
            reult = boardMapper.ajaxBlacklistNBInsert(cajax);
        }

        else if(ACTION.equals("ajaxBlacklistNBInsertDetail")) {
            reult = boardMapper.ajaxBlacklistNBInsertDetail(cajax);
        }



        return reult;


    }

//    public void eiboardDelete(String no) {
//
//       boardMapper.boardeicomment(no);
//       
//    }

//    public void eeiboardDelete(EiCommentDTO eeiboardDelete) {
//
//        boardMapper.eeiboardDelete(eeiboardDelete.getNb_no());
//
//
//    }


}

//
//   public CommentDTO selectComentList(String no) {
//       List<CommentDTO> commentlist = boardMapper.selectComentList(no);
//
//       System.out.println("commentlist" + commentlist);
//
//
//            return null;
//        }







