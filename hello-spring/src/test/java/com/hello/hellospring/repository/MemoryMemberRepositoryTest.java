package com.hello.hellospring.repository;

import com.hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {

    private MemoryMemberRepository repository = new MemoryMemberRepository();

    //하나의 테스트가 끝날 때마다 테스트한 것을 초기화 해준다.
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void findById(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member ret = repository.findById(member.getId()).get();
        //System.out.println(ret == member);
        //Assertions.assertEquals(member, ret);
        assertThat(member).isEqualTo(ret);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member ret = repository.findByName("spring2").get();

        assertThat(ret).isEqualTo(member2);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> list = repository.findAll();

        assertThat(list.size()).isEqualTo(2);
    }

}
