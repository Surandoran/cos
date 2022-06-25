package com.example.cosmetic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/Login/id")
    public String id(){
        return "Login/id";
    }

    @GetMapping("/login")
    public String login123(){
        return "Login/MemberLogin";
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

    @GetMapping("/Login/Update")
    public String update(){
        return "Login/Update";
    }


}
