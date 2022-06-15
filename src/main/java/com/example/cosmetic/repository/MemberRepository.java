package com.example.cosmetic.repository;

import com.example.cosmetic.dto.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository {

    Member save(Member member);
    Optional<Member> findByCode(Long code);
    Optional<Member> findById(String id);
    List<Member> findAll();

}
