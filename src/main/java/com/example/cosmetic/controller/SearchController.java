package com.example.cosmetic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SearchController {

    @GetMapping("/Search/search")
    public String search(){
        return "Search/search";
    }

}
