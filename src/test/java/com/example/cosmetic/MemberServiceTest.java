package com.example.cosmetic;

import com.example.cosmetic.dto.MemberDTO;
import com.example.cosmetic.repository.MemoryMemberRepository;
import com.example.cosmetic.service.MemberService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MemberServiceTest {
    MemberService memberService;
    MemoryMemberRepository memberRepository;
    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }
    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }
    @Test
    public void 회원가입() throws Exception {
        //Given
        MemberDTO member = new MemberDTO();
        member.setName("hello");
        //When
        Long saveCode = memberService.join(member);
        //Then
        MemberDTO findMember = memberRepository.findByCode(saveCode).get();
        assertEquals(member.getName(), findMember.getName());
    }
    @Test
    public void 중복_회원_예외() throws Exception {
        //Given
        MemberDTO member1 = new MemberDTO();
        member1.setName("spring");
        MemberDTO member2 = new MemberDTO();
        member2.setName("spring");
        //When
        memberService.join(member1);
        IllegalAccessException e = assertThrows(IllegalAccessException.class,
                () -> memberService.join(member2));//예외가 발생해야 한다.
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
}
