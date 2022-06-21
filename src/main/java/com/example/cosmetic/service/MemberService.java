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
public class MemberService implements UserDetailsService{

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

    //로그인
    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        // 로그인을 하기 위해 가입된 user정보를 조회하는 메서드
        Optional<Member> memberWrapper = memberRepository.findByid(id);
        Member member = memberWrapper.get();

        List<GrantedAuthority> authorities = new ArrayList<>();

        if("admin".equals(id)){
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
        }

        // 아이디, 비밀번호, 권한리스트를 매개변수로 User를 만들어 반환해준다.
        return new User(member.getId(), member.getPw(), authorities);
    }

    //수정
    public Member get(Long code) throws UserNotFoundException {
        Optional<Member> result = memberRepository.findByCode(code);
        if (result.isPresent()){
            return result.get();
        }
        throw new UserNotFoundException("Colud not find any users with Code " + code);
    }

    public void delete(Member member) {
        Long count = member.getCode();
        if ( count != null || count == 0) {
            throw new UsernameNotFoundException("Could not find any users with code " + count);
        }
        memberRepository.deleteById(member);

    }

}
