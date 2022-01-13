package com.login.web;

import com.login.domain.member.Member;
import com.login.domain.member.MemberRepository;
import com.login.web.session.SessionManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final MemberRepository memberRepository;
    private final SessionManager sessionManager;

    //@GetMapping("/")
    public String homeLogin(@CookieValue(name = "memberId", required = false) Long memberId, Model model) {
        if(memberId == null) return "index";

        Member loginMember = memberRepository.findById(memberId);

        if (loginMember == null) return "index";

        model.addAttribute("member", loginMember);
        return "home";
    }

//    @GetMapping("/")
    public String homeSessionLogin(HttpServletRequest request, Model model){
        Member member = (Member)sessionManager.getSession(request);
        if(member==null) return "index";
        model.addAttribute("member", member);
        return "home";
    }

    @GetMapping("/")
    public String homeServletSessionLogin(@SessionAttribute(name = "loginMember", required = false)Member member, Model model){
        if(member==null) return "index";
        model.addAttribute("member",member);
        return "home";
    }

}
