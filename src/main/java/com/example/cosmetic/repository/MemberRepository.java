package com.example.cosmetic.repository;

import com.example.cosmetic.dto.MemberDTO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository {

    MemberDTO save(MemberDTO memberDTO);
    Optional<MemberDTO> findByCode(Long code);
    Optional<MemberDTO> findById(String id);
    List<MemberDTO> findAll();

}
