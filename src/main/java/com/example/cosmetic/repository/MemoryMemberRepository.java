import com.example.cosmetic.repository.MemberRepository;

//package com.example.cosmetic.repository;
//
//import com.example.cosmetic.dto.Member;
//import org.springframework.stereotype.Repository;
//
//import java.util.*;
//
//@Repository
//public class MemoryMemberRepository implements MemberRepository {
//
//    private static Map<Long, Member> store = new HashMap<>();
//    private static Long sequence= 0L;
//
//    @Override
//    public Member save(Member member) {
//        member.setCode(++sequence);
//        store.put(member.getCode(), member);
//        return member;
//    }
//
//    @Override
//    public Optional<Member> findByCode(Long code) {
//
//        return store.values().stream()
//                .filter(member -> member.getCode().equals(code))
//                .findAny();
//    }
//
//    @Override
//    public Optional<Member> findById(String id) {
//        return store.values().stream()
//                .filter(member -> member.getId().equals(id))
//                .findAny();
//    }
//
//    @Override
//    public List<Member> findAll() {
//        return new ArrayList<>(store.values());
//    }
//
//    public void clearStore() {
//        store.clear();
//    }
//}
