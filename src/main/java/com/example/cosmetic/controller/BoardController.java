package com.example.cosmetic.controller;

import com.example.cosmetic.dto.Board;
import com.example.cosmetic.repository.BoardRepository;
import com.example.cosmetic.service.BoardService;
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
public class BoardController {

    BoardService boardService;
    BoardRepository boardRepository;

    @Autowired
    public BoardController(BoardService boardService, BoardRepository boardRepository) {
        this.boardService = boardService;
        this.boardRepository = boardRepository;
    }

    @GetMapping("/list")
    public String list(HttpSession session, Model model, @RequestParam(required = false, defaultValue = "0", value = "page") int page){

        //불러올 페이지의 데이터 1페이지는 0부터 시작
        Page<Board> listPage =  boardService.list(page);

        //총페이지수
        int totalPage = listPage.getTotalPages();

        model.addAttribute("board", listPage.getContent());
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("pageNo", page);

        return "/list";
    }

    @GetMapping("/board/write")
    public String write(){
        return "write";
    }

    //게시판 저장
    @ResponseBody
    @PostMapping("/board/write")
    public Board writeSubmit(@RequestBody Board board){
        log.info("params={}", board);

        boardRepository.save(board);
        return board;
    };

    @GetMapping("/board/update")
    public String update(){
        return "update";
    }

    /**
     *  게시판 수정화면
     * @param boardIdx
     * @param model
     * @return
     */
    @GetMapping("/board/update/{boardIdx}")
    public String update(@PathVariable Long boardIdx, Model model){
        log.info("boardIdx={}", boardIdx);
        Board boardDetail = boardService.getDetail(boardIdx);
        log.info("boardDetail={}", boardDetail);
        model.addAttribute("board", boardDetail);
        return "main/update";
    }

    /**
     * 게시판 수정화면
     * @param board
     * @return
     */
    @ResponseBody
    @PostMapping("/board/update")
    public Long updateSubmit(@RequestBody Board board){
        log.info("params={}", board);
        return boardService.savePost(board);
    }

    /**
     * 게시판 삭제기능
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
