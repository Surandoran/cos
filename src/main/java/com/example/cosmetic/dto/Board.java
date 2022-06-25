package com.example.cosmetic.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@SequenceGenerator(
        name = "MEMBER_SEQ_GENERATOR",
        sequenceName = "Boardidx_code",
        initialValue = 1,
        allocationSize = 1
)
@Getter
@Setter
@ToString

public class Board {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOARDIDX_SEQ_GENERATOR")
    private Long boardIdx;

    @Column(columnDefinition = "varchar(45) not null comment '타이틀'")
    private String boardTitle;

    @Column(columnDefinition = "TEXT not null comment '내용'")
    private String boardContent;

    @Column(columnDefinition = "varchar(45) not null comment '등록자'")
    private String regId;

    //조회수
    private int viewCount;

    private String useYn;

    //insert시에 현재시간을 읽어서 저장
    @CreationTimestamp
    private LocalDateTime regDate;

    //update시에 현재시간을 읽여서 저장
    @UpdateTimestamp
    private LocalDateTime uptDate;
}
