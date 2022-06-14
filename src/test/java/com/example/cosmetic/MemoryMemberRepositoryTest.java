package com.example.cosmetic;

import com.example.cosmetic.dto.MemberDTO;
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
        MemberDTO member = new MemberDTO();
        member.setName("spring");
        //when
        repository.save(member);
        //then
        MemberDTO result = repository.findByCode(member.getCode()).get();
        assertThat(result).isEqualTo(member);
    }
    @Test
    public void findById() {
        //given
        MemberDTO member1 = new MemberDTO();
        member1.setId("spring1");
        repository.save(member1);
        MemberDTO member2 = new MemberDTO();
        member2.setId("spring2");
        repository.save(member2);
        //when
        MemberDTO result = repository.findById("spring1").get();
        //then
        assertThat(result).isEqualTo(member1);
    }
    @Test
    public void findAll() {
        //given
        MemberDTO member1 = new MemberDTO();
        member1.setName("spring1");
        repository.save(member1);
        MemberDTO member2 = new MemberDTO();
        member2.setName("spring2");
        repository.save(member2);
        //when
        List<MemberDTO> result = repository.findAll();
        //then
        assertThat(result.size()).isEqualTo(2);
    }
}