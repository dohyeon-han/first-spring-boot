package com.inflearn.order.order;

import com.inflearn.order.member.Grade;
import com.inflearn.order.member.Member;
import com.inflearn.order.member.MemberService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OrderServiceTest {
//    AppConfig appConfig = new AppConfig();
//    OrderService orderService = appConfig.orderService();
//    MemberService memberService = appConfig.memberService();
    @Autowired
    MemberService memberService;
    @Autowired
    OrderService orderService;

    @Test
    void order() {
        Member member = new Member(1L,"mem1", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(1L, "mem1", 5000);
        Assertions.assertEquals(order.getDiscountPrice(),500);
    }
}