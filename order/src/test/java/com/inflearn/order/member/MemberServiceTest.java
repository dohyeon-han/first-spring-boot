package com.inflearn.order.member;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
class MemberServiceTest {

//    AppConfig appConfig = new AppConfig();
//    MemberService memberService = appConfig.memberService();
    @Autowired
    MemberService memberService;

    @Test
    void join() {
        Member member = new Member(1L,"spring",Grade.VIP);

        memberService.join(member);

        Assertions.assertEquals(memberService.findMember(1L),member);
    }
}