package com.example.cosmetic.controller;

import com.example.cosmetic.dto.MemberDTO;
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
        MemberDTO memberDTO1 = new MemberDTO();
        memberDTO1.setName(memberEntity.getName());
        memberDTO1.setId(memberEntity.getId());
        memberDTO1.setPw(memberEntity.getPw());
        memberDTO1.setNickname(memberEntity.getNickname());
        memberDTO1.setEmail(memberEntity.getEmail());
        memberDTO1.setAddr(memberEntity.getAddr());
        memberDTO1.setPhone(memberEntity.getPhone());
        memberDTO1.setGender(memberEntity.getGender());

        memberService.join(memberDTO1);

        return "redirect:/";
    }

    @GetMapping(value = "/members")
    public String list(Model model){
        List<MemberDTO> memberDTOS = memberService.findMembers();
        model.addAttribute("memberDTOS", memberDTOS);
        return "members/memberList";
    }
}
