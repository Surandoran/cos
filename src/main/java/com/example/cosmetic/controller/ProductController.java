package com.example.cosmetic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {

    @GetMapping("/Product/product")
    public String product(){
        return "Product/product";
    }

    @GetMapping("/Product/ProductMain")
    public String productMain(){
        return "Product/ProductMain";
    }

    @GetMapping("/Product/ProductReview")
    public String productreview(){
        return "Product/ProductReview";
    }

    @GetMapping("/Product/ReviewWrite")
    public String reviewrite(){
        return "Product/ReviewWrite";
    }

}
