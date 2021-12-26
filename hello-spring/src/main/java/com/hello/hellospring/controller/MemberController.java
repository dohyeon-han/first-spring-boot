package com.hello.hellospring.controller;

import com.hello.hellospring.domain.Member;
import com.hello.hellospring.domain.MemberForm;
import com.hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/new")
    public String createForm(){
        return "/members/createMemberForm";
    }

    @PostMapping("/new")
    public String create(MemberForm memberForm){
        Member member = new Member();
        member.setName(memberForm.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "/members/memberList";
    }
}
