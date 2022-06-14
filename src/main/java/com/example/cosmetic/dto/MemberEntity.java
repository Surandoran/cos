package com.example.cosmetic.dto;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class MemberEntity {

    @Id
    private Long code;
    private String name;
    private String id;
    private String pw;
    private String nickname;
    private String email;
    private String phone;
    private String addr;
    private String gender;
}
