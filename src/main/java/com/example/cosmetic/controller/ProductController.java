package com.example.cosmetic.controller;

import com.example.cosmetic.dto.Product;
import com.example.cosmetic.dto.ProductEntity;
import com.example.cosmetic.repository.ProductRepository;
import com.example.cosmetic.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/product/new")
    public String createproduct(Model model) {
        model.addAttribute("product", new Product());

        return "/Product/ReviewWrite";
    }

    @PostMapping(value = "product/new")
    public String create(ProductEntity productEntity) {
        try{
            Product product = new Product();
            product.setName(productEntity.getName());
            product.setStory(productEntity.getStory());
            product.setPrice(productEntity.getPrice());
            product.setScore(productEntity.getScore());
            product.setVolume(productEntity.getVolume());
            product.setCreatedDate(productEntity.getCreatedDate());


            productService.join(product);

            if (product.getId() != null){
                return "redirect:/Product/ReviewWrite";
            }else{
                return "Product/new";
            }

        }catch (Exception e){
            System.out.println("");
            return "/Product/ReviewWrite";
        }
    }

    //품목전체조회
    @GetMapping(value = "/Products")
    public String list(Model model){
        List<Product> products = productService.findProduct();
        model.addAttribute("products", products);
        return "Product/productList";
    }


    @GetMapping("/Product/product")
    public String product() {
        return "Product/product";
    }

    @GetMapping("/Product/ProductMain")
    public String productMain() {
        return "Product/ProductMain";
    }

    @GetMapping("/Product/ProductReview")
    public String productreview() {
        return "Product/ProductReview";
    }

    @GetMapping("/Product/ReviewWrite")
    public String reviewrite() {
        return "Product/ReviewWrite";
    }

}
