package com.example.cosmetic.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@SequenceGenerator(
        name = "MEMBER_SEQ_GENERATOR",
        sequenceName = "member_code",
        initialValue = 1,
        allocationSize = 1
)
@Getter
@Setter
@ToString
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ_GENERATOR")
    @Column(name = "id")
    private Long id;
    //이름
    @Column(name = "name")
    private String name;
    //내용
    @Column(name = "story")
    private String story;
    //가격
    @Column(name = "price")
    private String price;
    //용량
    @Column
    private String volume;
    //평점
    private String Score;
    //시간
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;


    public Product() {

    }
}
