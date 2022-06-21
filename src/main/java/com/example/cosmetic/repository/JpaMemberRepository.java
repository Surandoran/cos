//package com.example.cosmetic.repository;
//
//import com.example.cosmetic.dto.Member;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import javax.persistence.EntityManager;
//import java.util.List;
//import java.util.Optional;
//
//public class JpaMemberRepository implements MemberRepository{
//
//    private final EntityManager em;
//
//    @Autowired
//    public JpaMemberRepository(EntityManager em) {
//        this.em = em;
//    }
//
//    @Override
//    public Member save(Member member) {
//        em.persist(member);
//        return member;
//    }
//
//    @Override
//    public Optional<Member> findByCode(Long code) {
//        Member member = em.find(Member.class, code);
//        return Optional.ofNullable(member);
//    }
//
//    @Override
//    public Optional<Member> findById(String id) {
//        List<Member> result = em.createQuery("select m from Member m where m.id = :id", Member.class)
//                .setParameter("id",id)
//                .getResultList();
//        return result.stream().findAny();
//    }
//
//    @Override
//    public List<Member> findAll() {
//        return em.createQuery("select m from Member m", Member.class)
//                .getResultList();
//    }
//}
