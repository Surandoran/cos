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

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class MemberService implements UserDetailsService{

    private final MemberRepository memberRepository;


    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    private void validateDuplicateMember(Member member){
        memberRepository.findByname(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 아이디입니다.");
                });
    }

    //회원가입
    public Long join(Member member){
        validateDuplicateMember(member);//중복회원검증
        memberRepository.save(member);
        return member.getId();
    }

    //전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    //멤버 찾기
    public Optional<Member> findOne(Long id){
        return memberRepository.findById(id);
    }


    //수정
    public Member get(Long id) throws UserNotFoundException {
        Optional<Member> result = memberRepository.findById(id);
        if (result.isPresent()){
            return result.get();
        }
        throw new UserNotFoundException("Colud not find any users with ID " + id);
    }


    public void deleteById(Long id){
        Long count = id;
        if ( count == null || count == 0) {
            throw new UsernameNotFoundException("Could not find any users with ID " + count);
        }
        memberRepository.deleteById(id);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
