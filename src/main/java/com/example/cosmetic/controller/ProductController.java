package com.example.cosmetic.controller;

import com.example.cosmetic.dto.Member;
import com.example.cosmetic.dto.Product;
import com.example.cosmetic.dto.ProductEntity;
import com.example.cosmetic.repository.ProductRepository;
import com.example.cosmetic.service.ProductNotFoundException;
import com.example.cosmetic.service.ProductService;
import com.example.cosmetic.service.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ProductController {

    private final ProductService productService;
    private ProductRepository productRepository;

    @Autowired
    public ProductController(ProductService productService, ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }


    @GetMapping(value = "/product/new")
    public String createproduct(Model model) {
        model.addAttribute("product", new Product());

        return "/Product/ReviewWrite";
    }

    @PostMapping(value = "/product/new")
    public String create(ProductEntity productEntity, RedirectAttributes ra) {
        try{
            Product product = new Product();
            product.setName(productEntity.getName());
            product.setStory(productEntity.getStory());
            product.setPrice(productEntity.getPrice());
            product.setRating(productEntity.getRating());
            product.setVolume(productEntity.getVolume());

            ra.addFlashAttribute("message", "등록성공!");

            productService.join(product);

            if (product.getId() != null){
                return "redirect:/Product/ProductMain";
            }else{
                return "Product/new";
            }

        }catch (Exception e){
            System.out.println("에러로 인한 등록불가");
            return "Product/ReviewWrite";
        }
    }

    //품목전체조회
    @GetMapping(value = "/products")
    public String list(Model model){
        List<Product> products = productService.findProduct();
        model.addAttribute("products", products);
        return "Product/productList";
    }

    //수정
    @GetMapping("/products/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model, RedirectAttributes ra) {
        try {
            Product product = productService.get(id);
            if(id == product.getId()){
                productService.deleteById(id);
            } else{
                productService.get(id);
            }
            model.addAttribute("product", product);
            model.addAttribute("pageTitle", "Edit product (ID: " + id + ")");
            return "Product/ReviewWrite";
        } catch (ProductNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/products";
        }
    }

    //삭제
    @GetMapping("/products/delete/{id}")
    public String deleteMember(@PathVariable("id") Long id, RedirectAttributes ra) {
        try {
            productService.deleteById(id);
            ra.addFlashAttribute("message", "The product ID " + id + " has been deleted.");
        } catch (Exception e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/products";
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
