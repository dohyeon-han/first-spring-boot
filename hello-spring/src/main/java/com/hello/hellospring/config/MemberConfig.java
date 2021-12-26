package com.hello.hellospring.config;

import com.hello.hellospring.aop.TimeTraceAop;
import com.hello.hellospring.repository.JdbcTemplateMemberRepository;
import com.hello.hellospring.repository.JpaMemberRepository;
import com.hello.hellospring.repository.MemberRepository;
import com.hello.hellospring.repository.MemoryMemberRepository;
import com.hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class MemberConfig {


    //@Service, @Repository 말고 @Bean으로 등록 가능
    private DataSource dataSource;
    private EntityManager em;

    //Spring data jpa 사용시 생성자로 주입 가능
    private final MemberRepository memberRepository;

    public MemberConfig(DataSource dataSource, EntityManager em, MemberRepository memberRepository){
        this.dataSource = dataSource;
        this.em = em;
        this.memberRepository = memberRepository;
    }

//    @Bean
//    public MemberService memberService(){
//        return new MemberService(memberRepository());
//    }

//    @Bean
//    public MemberRepository memberRepository(){
////        return new MemoryMemberRepository();
////        return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
//    }

    @Bean
    public TimeTraceAop timeTraceAop(){
        return new TimeTraceAop();
    }
}
