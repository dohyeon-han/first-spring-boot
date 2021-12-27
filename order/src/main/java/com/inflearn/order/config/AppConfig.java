package com.inflearn.order.config;

import com.inflearn.order.member.MemberRepository;
import com.inflearn.order.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.inflearn.order.*")
public class AppConfig {

    //각 인터페이스의 구현체를 바꾸기만 하면 된다.
    //기존 코드의 수정 없이 사용 가능

//    @Service 사용
//    @Bean
//    public MemberService memberService(){
//        return new MemberServiceImpl(memberRepository());
//    }
//    @Bean
//    public OrderService orderService(){
//        return new OrderServiceImpl(discountPolicy(), memberRepository());
//    }
    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
//    @Bean
//    public DiscountPolicy discountPolicy(){
//        return new RateDiscountPolicy();
//    }
}
