package com.inflearn.order;

import com.inflearn.order.member.MemberService;
import com.inflearn.order.order.OrderService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class xmlAppConfigTest {
    @Test
    void xmlAppContext(){
        ApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");
        MemberService memberService = ac.getBean("memberService",MemberService.class);
        OrderService orderService = ac.getBean("orderService", OrderService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
        Assertions.assertThat(orderService).isInstanceOf(OrderService.class);
    }
}
