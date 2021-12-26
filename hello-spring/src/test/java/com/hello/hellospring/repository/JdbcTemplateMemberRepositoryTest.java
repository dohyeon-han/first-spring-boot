package com.hello.hellospring.repository;

import com.hello.hellospring.domain.Member;
import com.hello.hellospring.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class JdbcTemplateMemberRepositoryTest {
    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    void save() {
        Member member = new Member();
        member.setName("spring1");

        Long id = memberService.join(member);

        Member find = memberRepository.findById(id).get();

        assertEquals(find.getId(),id);
    }

    @Test
    void duplicateMember() {
        Member member1 = new Member();
        member1.setName("spring");
        memberService.join(member1);
        Member member2 = new Member();
        member2.setName("spring");
        IllegalStateException e = assertThrows(IllegalStateException.class,
                ()->memberService.join(member2));
    }
}