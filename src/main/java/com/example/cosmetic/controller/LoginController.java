package com.example.cosmetic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

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

    @GetMapping("/")
    public String home(){
        return "home";
    }
}
