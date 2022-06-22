package com.example.cosmetic.service;

import com.example.cosmetic.dto.Member;
import com.example.cosmetic.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class MemberService{

    private final MemberRepository memberRepository;


    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    private void validateDuplicateMember(Member member){
        memberRepository.findByid(member.getId())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 아이디입니다.");
                });
    }

    //회원가입
    public Long join(Member member){
        validateDuplicateMember(member);//중복회원검증
        memberRepository.save(member);
        return member.getCode();
    }

    //전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    //멤버 찾기
    public Optional<Member> findOne(Long memberCode){
        return memberRepository.findByCode(memberCode);
    }


    //수정
    public Member get(Long code) throws UserNotFoundException {
        Optional<Member> result = memberRepository.findByCode(code);
        if (result.isPresent()){
            return result.get();
        }
        throw new UserNotFoundException("Colud not find any users with Code " + code);
    }


    public void deleteById(Long code){
        Long count = code;
        if ( count == null || count == 0) {
            throw new UsernameNotFoundException("Could not find any users with code " + count);
        }
        memberRepository.deleteById(code);
    }





}
