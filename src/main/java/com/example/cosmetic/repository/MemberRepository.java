package com.example.cosmetic.repository;

import com.example.cosmetic.dto.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Member save(Member member);
    Optional<Member> findByCode(Long code);
    List<Member> findAll();
    Member findById(String id);
    Optional<Member> findByid(String id);
    Member findByNickname(String nickname);
    public Long countByCode(Long code);


}
