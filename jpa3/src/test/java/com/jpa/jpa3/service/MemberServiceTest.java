package com.jpa.jpa3.service;

import com.jpa.jpa3.domain.Member;
import com.jpa.jpa3.domain.springDataRepository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Transactional
@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberService service;

    @Autowired
    MemberRepository repository;

    @Test
    @DisplayName("회원가입")
    void join() {
        Member member = Member.builder().name("kim").build();

        Long id = service.join(member);

        assertThat(member).isEqualTo(service.findOne(id));
    }

    @Test
    @DisplayName("중복 회원 예외")
    void joinException() {
        Member member1 = Member.builder().name("kim").build();
        Member member2 = Member.builder().name("kim").build();

        service.join(member1);

        assertThrows(IllegalStateException.class, () -> service.join(member2));
    }
}