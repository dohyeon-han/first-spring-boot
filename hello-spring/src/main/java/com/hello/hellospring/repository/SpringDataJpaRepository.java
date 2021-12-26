package com.hello.hellospring.repository;

import com.hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//interface는 extends로 interface 상속
//<entity,pk>
public interface SpringDataJpaRepository extends JpaRepository<Member,Long>, MemberRepository {
    @Override
    Optional<Member> findByName(String name);
}
