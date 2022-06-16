package com.example.cosmetic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MagazineController {

    @GetMapping("/Magazine/CosviewStory")
    public String cosviewstory(){
        return "Magazine/CosviewStory";
    }

}
