package com.example.cosmetic.controller;

import com.example.cosmetic.dto.Member;
import com.example.cosmetic.dto.MemberEntity;
import com.example.cosmetic.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping(value = "/members/new")
    public String createForm() {
        return "members/Singup";
    }

    @PostMapping(value = "/members/new")
    public String create(MemberEntity memberEntity){
        Member member1 = new Member();
        member1.setName(memberEntity.getName());
        member1.setId(memberEntity.getId());
        member1.setPw(memberEntity.getPw());
        member1.setNickname(memberEntity.getNickname());
        member1.setEmail(memberEntity.getEmail());
        member1.setAddr(memberEntity.getAddr());
        member1.setPhone(memberEntity.getPhone());
        member1.setGender(memberEntity.getGender());

        System.out.println(member1);
        memberService.join(member1);

        return "redirect:/";
    }

    @GetMapping(value = "/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
