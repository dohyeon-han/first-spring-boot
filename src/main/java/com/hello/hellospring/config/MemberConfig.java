package com.hello.hellospring.config;

import com.hello.hellospring.repository.JdbcTemplateMemberRepository;
import com.hello.hellospring.repository.MemberRepository;
import com.hello.hellospring.repository.MemoryMemberRepository;
import com.hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class MemberConfig {

    //@Service, @Repository 말고 @Bean으로 등록 가능
    private DataSource dataSource;

    public MemberConfig(DataSource dataSource){
        this.dataSource = dataSource;
    }

//    @Bean
//    public MemberService memberService(){
//        return new MemberService(memberRepository());
//    }

    @Bean
    public MemberRepository memberRepository(){
//        return new MemoryMemberRepository();
        return new JdbcTemplateMemberRepository(dataSource);
    }
}
