package com.inflearn.order.member;

import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService{

    //순수 자바 코드 사용 시 DIP 위반
    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long id) {
        return memberRepository.findById(id);
    }
}
