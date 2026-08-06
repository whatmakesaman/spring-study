package com.example.jpa_practice;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

@DataJpaTest
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    void Test(){
        Member member=new Member("방준혁","12@naver.com");

        Member savedMember=memberRepository.save(member);

        entityManager.flush();
        entityManager.clear();

        Member foundMember=memberRepository.findById(savedMember.getId())
                .orElseThrow();
    }
}
