package com.example.cosmetic.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@SequenceGenerator(
        name = "MEMBER_SEQ_GENERATOR",
        sequenceName = "member_code",
        initialValue = 1,
        allocationSize = 1
)
@Table(name = "member_tbl")
@Data
public class MemberDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ_GENERATOR")
    private Long Code;
    private String name;
    private String id;
    private String pw;
    private String nickname;
    private String Email;
    private String phone;
    private String addr;
    private String gender;
}
