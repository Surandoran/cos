package com.example.cosmetic.service;

import com.example.cosmetic.dto.MemberDTO;
import com.example.cosmetic.repository.MemberRepository;
import com.example.cosmetic.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    private void validateDuplicateMember(MemberDTO memberDTO){
        memberRepository.findById(memberDTO.getId())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 아이디입니다.");
                });
    }

    //회원가입
    public Long join(MemberDTO memberDTO){
        validateDuplicateMember(memberDTO);//중복회원검증
        memberRepository.save(memberDTO);
        return memberDTO.getCode();
    }

    //전체 회원 조회
    public List<MemberDTO> findMembers() {
        return memberRepository.findAll();
    }

    //멤버 찾기
    public Optional<MemberDTO> findOne(Long memberCode){
        return memberRepository.findByCode(memberCode);
    }
}
