package com.inflearn.order.member;

public interface MemberRepository {
    void save(Member member);
    Member findById(Long id);
}
