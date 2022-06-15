package com.example.cosmetic;

import com.example.cosmetic.dto.Member;
import com.example.cosmetic.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }
    @Test
    public void save() {
        //given
        Member member = new Member();
        member.setName("spring");
        //when
        repository.save(member);
        //then
        Member result = repository.findByCode(member.getCode()).get();
        assertThat(result).isEqualTo(member);
    }
    @Test
    public void findById() {
        //given
        Member member1 = new Member();
        member1.setId("spring1");
        repository.save(member1);
        Member member2 = new Member();
        member2.setId("spring2");
        repository.save(member2);
        //when
        Member result = repository.findById("spring1").get();
        //then
        assertThat(result).isEqualTo(member1);
    }
    @Test
    public void findAll() {
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);
        //when
        List<Member> result = repository.findAll();
        //then
        assertThat(result.size()).isEqualTo(2);
    }
}