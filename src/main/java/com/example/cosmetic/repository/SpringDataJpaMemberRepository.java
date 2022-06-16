package com.example.cosmetic.repository;

import com.example.cosmetic.dto.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long> {

    Member findById(String id);

    Member findByNickname(String nickname);

}
