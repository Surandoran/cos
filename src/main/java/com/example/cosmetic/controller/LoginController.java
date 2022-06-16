package com.example.cosmetic.controller;

import com.example.cosmetic.dto.Member;
import com.example.cosmetic.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    private MemberService memberService;

    @GetMapping("/Login/id")
    public String id(){
        return "Login/id";
    }

    @GetMapping("/Login/login")
    public String login(){
        return "Login/login";
    }

    @GetMapping("/Login/LoginCompletion")
    public String logincompletion(){
        return "Login/LoginCompletion";
    }

    @GetMapping("/Login/pw")
    public String pw(){
        return "Login/pw";
    }

    @GetMapping("/Login/pwSet")
    public String pwset(){
        return "Login/pwSet";
    }

    @GetMapping("/Login/reg_cancel")
    public String reg_cancel(){
        return "Login/reg_cancel";
    }

}
