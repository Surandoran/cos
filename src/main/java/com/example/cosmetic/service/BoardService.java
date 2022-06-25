package com.example.cosmetic.service;

import com.example.cosmetic.dto.Board;
import com.example.cosmetic.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardService {
    @Autowired
    BoardRepository boardRepository;

    public Page<Board> list(int page){
        return boardRepository.findAll(PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "boardIdx")));
    }

    /**
     * 게시판 상세
     * @param boardIdx
     * @return
     */
    public Board getDetail(Long boardIdx){
        Optional<Board> optional = boardRepository.findById(boardIdx);
        if(optional.isPresent()){
            Board board = optional.get();
            board.setViewCount(board.getViewCount()+1);
            boardRepository.save(board);
            return board;
        }
        else{
            throw new NullPointerException();
        }
    }

    /**
     * 게시판 저장
     * @param board
     * @return
     */
    public Long savePost(Board board){
        return boardRepository.save(board).getBoardIdx();
    }

    /**
     게시판 use_yn N으로 업데이트
     */
    public void deleteBoard(List<String> boardIdxArray){
        for(int i=0; i<boardIdxArray.size(); i++) {
            String boardIdx = boardIdxArray.get(i);
            Optional<Board> optional = boardRepository.findById(Long.parseLong(boardIdx));
            if(optional.isPresent()){
                Board board = optional.get();
                board.setUseYn("N");
                boardRepository.save(board);
            }
            else{
                throw new NullPointerException();
            }
        }
    }
}
