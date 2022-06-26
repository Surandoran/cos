package com.example.cosmetic.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
public class ProductEntity {

    private Long id;
    //이름
    private String name;
    //내용
    private String story;
    //가격
    private String price;
    //용량
    @Column
    private String volume;
    //평점
    private String rating;
}
