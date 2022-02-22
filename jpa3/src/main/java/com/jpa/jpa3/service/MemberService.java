package com.jpa.jpa3.service;

import com.jpa.jpa3.domain.Member;
import com.jpa.jpa3.domain.springDataRepository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Long join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member){
        List<Member> members = memberRepository.findByName(member.getName());
        if(!members.isEmpty())
            throw new IllegalStateException();
    }

    @Transactional(readOnly = true)
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Member findOne(Long id){
        return memberRepository.findById(id).orElse(null);
    }
}
