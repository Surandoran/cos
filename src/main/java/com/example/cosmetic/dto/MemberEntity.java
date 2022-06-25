package com.example.cosmetic.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class MemberEntity {

    private Long id;
    private String name;
    private String name2;
    private String pw;
    private String nickname;
    private String Email;
    private String addr;
    private String gender;

    public Member toEntity(){
        return Member.builder()
                .id(id)
                .name(name)
                .pw(pw)
                .build();
    }

    @Builder
    public MemberEntity(Long id, String name, String pw) {
        this.id = id;
        this.name = name;
        this.pw = pw;
    }
}
