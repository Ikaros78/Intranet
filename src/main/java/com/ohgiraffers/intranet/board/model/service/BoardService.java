package com.ohgiraffers.intranet.board.model.service;

import com.ohgiraffers.intranet.board.model.dao.BoardMapper;
import com.ohgiraffers.intranet.board.model.dto.AnonyDTO;
import com.ohgiraffers.intranet.board.model.dto.CommentDTO;
import com.ohgiraffers.intranet.board.model.dto.FreeinsertDTO;
import com.ohgiraffers.intranet.common.paging.SelectCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Service("freeinsertService")
public class BoardService {

    private final BoardMapper boardMapper;

    public BoardService(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
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

    @Transactional
    public void boardDelete(FreeinsertDTO freeinsert) {
        boardMapper.boardDelete(freeinsert.getNo());
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

    public void eiboardDelete(AnonyDTO anonyinsert) {

        boardMapper.eiboardDelete(anonyinsert.getNo());
    }

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







