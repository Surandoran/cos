package com.example.cosmetic.repository;

import com.example.cosmetic.dto.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Member save(Member member);
    Optional<Member> findById(Long id);
    List<Member> findAll();
    Member findByName(String name);
    Optional<Member> findByname(String name);
    Member findByNickname(String nickname);


}
