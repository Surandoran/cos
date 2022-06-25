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
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    private String name2;
    @Column(name = "pw")
    private String pw;
    @Column(name = "nickname")
    private String nickname;
    @Column(name = "email")
    private String Email;
    private String addr;
    private String gender;
    private String Role;

    public Member() {

    }

    @Builder
    public Member(Long id, String name, String pw) {
        this.id = id;
        this.name = name;
        this.pw = pw;
    }

}
