package com.example.cosmetic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(){
        return "Main/home";
    }

    @GetMapping("/test")
    public String test(){
        return "index";
    }

}

