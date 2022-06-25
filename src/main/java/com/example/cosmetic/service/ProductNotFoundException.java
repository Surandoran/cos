package com.example.cosmetic.service;

public class ProductNotFoundException extends Throwable{
    public ProductNotFoundException(String message) {
        super(message);
    }
}
