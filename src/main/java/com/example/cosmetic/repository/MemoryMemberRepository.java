package com.example.cosmetic.repository;

import com.example.cosmetic.dto.MemberDTO;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, MemberDTO> store = new HashMap<>();
    private static Long sequence= 0L;

    @Override
    public MemberDTO save(MemberDTO memberDTO) {
        memberDTO.setCode(++sequence);
        store.put(memberDTO.getCode(),memberDTO);
        return memberDTO;
    }

    @Override
    public Optional<MemberDTO> findByCode(Long code) {

        return Optional.ofNullable(store.get(code));

    }

    @Override
    public Optional<MemberDTO> findById(String id) {
        return store.values().stream()
                .filter(memberDTO -> memberDTO.getId().equals(id))
                .findAny();
    }

    @Override
    public List<MemberDTO> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
