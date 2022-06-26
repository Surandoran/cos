package com.example.cosmetic.controller;

import com.example.cosmetic.dto.Board;
import com.example.cosmetic.repository.BoardRepository;
import com.example.cosmetic.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class BoardController {

    @Autowired
    private final BoardService boardService;


    /**
     * 게시판 목록화면
     * @param session
     * @param model
     * @param page
     * @return
     */
    @GetMapping("/list")
    public String list(HttpSession session, Model model
            , @RequestParam(required = false, defaultValue = "0", value = "page") int page
            , @RequestParam(required = false, defaultValue = "", value = "keyword") String keyword){

        //불러올 페이지의 데이터 1페이지는 0부터 시작
        Page<Board> listPage =  boardService.list(page, keyword);

        //총페이지수
        int totalPage = listPage.getTotalPages();
        model.addAttribute("board", listPage.getContent());
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("pageNo", page);
        model.addAttribute("resultDataTotal", listPage.getTotalElements());
        model.addAttribute("size", listPage.getSize());
        model.addAttribute("number", listPage.getNumber());
        model.addAttribute("keyword", keyword);

        return "list";
    }

    /**
     * 게시판 등록화면
     * @return
     */
    @GetMapping("/board/write")
    public String write(){
        return "write";
    }

    /**
     * 게시판 등록
     * @param board
     * @return
     */
    @ResponseBody
    @PostMapping("/board/write")
    public Long writeSubmit(@RequestBody Board board){
        log.info("params={}", board);
        return boardService.savePost(board);
    };

    /**
     *  게시판 수정화면
     * @param boardIdx
     * @param model
     * @return
     */
    @GetMapping("/board/update/{boardIdx}")
    public String update(@PathVariable Long boardIdx, Model model){

        //게시판 상세 데이터
        model.addAttribute("board", boardService.getDetail(boardIdx));

        //게시판 파일 리스트
        model.addAttribute("boardFileInfo", boardService.selectBoardFile(boardIdx));
        return "update";
    }

    /**
     * 게시판 수정
     * @param board
     * @return
     */
    @ResponseBody
    @PostMapping("/board/update")
    public Long updateSubmit(@RequestBody Board board){
        log.info("params={}", board);
        Long boardIdx = boardService.savePost(board);

        //board 파일 테이블 insert
        boardService.insertBoardFile(board);

        //넘어온 파일 삭제 시퀀스 삭제처리
        if(!board.getDeleteFileIdxs().isEmpty()){
            String deleteFileIdxs = (String) board.getDeleteFileIdxs();
            String[] fileIdxsArray = deleteFileIdxs.split(",");

            //해당 시퀀스 삭제처리
            for(int i=0; i<fileIdxsArray.length; i++){
                String fileId = fileIdxsArray[i];
                System.out.println("fileId : " + fileId);
                boardService.deleteBoardFile(Long.parseLong(fileId));
            }
        }

        return boardIdx;
    }

    /**
     * 게시판 삭제
     * @param boardIdxArray
     * @return
     */

    @ResponseBody
    @PostMapping("/board/delete")
    public List<String> deleteSubmit(@RequestBody List<String> boardIdxArray){
        log.info("boardIdxArray={}", boardIdxArray);
        boardService.deleteBoard(boardIdxArray);
        return boardIdxArray;
    }

}
