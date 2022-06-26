package com.example.cosmetic.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@SequenceGenerator(
        name = "boardfile_SEQ_GENERATOR",
        sequenceName = "boardfile_code",
        initialValue = 1,
        allocationSize = 1
)
@Getter
@Setter
@NoArgsConstructor
public class BoardFile {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOARDFILE_SEQ_GENERATOR")
    private Long fileId;

    private Long boardIdx;

    private String useYn;

    public BoardFile(Long boardIdx, Long fileId, String useYn) {
        this.boardIdx = boardIdx;
        this.fileId = fileId;
        this.useYn = useYn;
    }

}