package com.inflearn.order.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

//@Component("service")
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    //순수 자바 코드 사용 시 DIP 위반
    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long id) {
        return memberRepository.findById(id);
    }
}
