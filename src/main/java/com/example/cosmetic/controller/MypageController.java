package com.example.cosmetic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MypageController {

    @GetMapping("/Mypage/Inform_change")
    public String inform_change(){
        return "Mypage/Inform_change";
    }

    @GetMapping("/Mypage/My_page")
    public String my_page(){
        return "Mypage/my_page";
    }

    @GetMapping("/Mypage/reg_cancel")
    public String reg_cancel(){
        return "Mypage/reg_cancel";
    }

}
