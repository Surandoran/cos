package com.example.cosmetic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ServiceCenterController {

    @GetMapping("/ServiceCenter/CSCenter")
    public String cscenter(){
        return "ServiceCenter/CSCenter";
    }

    @GetMapping("/ServiceCenter/FAQ")
    public String faq(){
        return "ServiceCenter/faq";
    }

    @GetMapping("/ServiceCenter/Location")
    public String location(){
        return "ServiceCenter/Location";
    }

    @GetMapping("/ServiceCenter/PersonalQuestion")
    public String personalquestion(){
        return "ServiceCenter/PersonalQuestion";
    }

}
