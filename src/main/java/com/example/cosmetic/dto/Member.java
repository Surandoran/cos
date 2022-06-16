package com.example.cosmetic.dto;


import lombok.*;

import javax.persistence.*;

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
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ_GENERATOR")
    @Column(name = "code")
    private Long code;
    @Column(name = "name")
    private String name;
    @Column(name = "id")
    private String id;
    @Column(name = "pw")
    private String pw;
    @Column(name = "nickname")
    private String nickname;
    @Column(name = "email")
    private String Email;
    private String phone;
    private String addr;
    private String gender;

    public Member() {

    }

    public Member toEntity() {
        return Member.builder()
                .code(code)
                .id(id)
                .pw(pw)
                .build();
    }

    @Builder
    public Member(Long code ,String id ,String pw){
        this.code = code;
        this.id = id;
        this.pw = pw;
    }
}
