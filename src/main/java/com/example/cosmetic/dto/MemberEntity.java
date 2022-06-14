package com.example.cosmetic.dto;

import lombok.Data;

import javax.persistence.*;

@Data
public class MemberEntity {

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
