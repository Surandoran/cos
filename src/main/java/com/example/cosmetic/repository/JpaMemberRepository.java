package com.example.cosmetic.repository;

import com.example.cosmetic.dto.MemberDTO;
import com.example.cosmetic.dto.MemberEntity;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em;

    @Autowired
    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public MemberDTO save(MemberDTO memberDTO) {
        em.persist(memberDTO);
        return memberDTO;
    }

    @Override
    public Optional<MemberDTO> findByCode(Long code) {
        MemberDTO memberDTO = em.find(MemberDTO.class, code);
        return Optional.ofNullable(memberDTO);
    }

    @Override
    public Optional<MemberDTO> findById(String id) {
        List<MemberDTO> result = em.createQuery("select m from member_entity m where m.id = :id", MemberDTO.class)
                .setParameter("id",id)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<MemberDTO> findAll() {
        return em.createQuery("select m from member_entity m", MemberDTO.class)
                .getResultList();
    }
}
