package com.login.domain.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberRepository memberRepository;

    @GetMapping("/add")
    public String addForm(@ModelAttribute("member") Member member) {
        return "member/addMemberForm";
    }

    @PostMapping("/add")
    public String save(@Validated @ModelAttribute("member") Member member, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "member/addMemberForm";
        }
        memberRepository.save(member);
        return "redirect:/";
    }

    @PostConstruct
    public void init() {
        Member member = new Member();
        member.setLoginId("test");
        member.setPassword("test!");
        member.setName("테스터");
        memberRepository.save(member);
    }
}
