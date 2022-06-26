package com.example.cosmetic.repository;

import com.example.cosmetic.dto.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    //useYn 조건
    Page<Board> findAllByUseYn(Pageable pageable, String useYn);

    //useYn 조건, boardTitle like 조건
    Page<Board> findAllByBoardTitleContainingIgnoreCaseAndUseYn(Pageable pageable, String boardTitle, String useYn);

}
