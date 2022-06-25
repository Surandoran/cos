package com.example.cosmetic.service;

import com.example.cosmetic.dto.Product;
import com.example.cosmetic.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //제품등록
    public Long join(Product product){
        productRepository.save(product);
        return product.getId();
    }

    //모든제품조회
    public List<Product> findProduct() {
        return productRepository.findAll();
    }

    //제품찾기
    public Optional<Product> findOne(String name){
        return productRepository.findByName(name);
    }

    //수정
    public Product get(Long id) throws ProductNotFoundException {
        Optional<Product> result = productRepository.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new ProductNotFoundException("Colud not find any users with Product name ");
    }

    //삭제
    public void deleteById(Long id) throws ProductNotFoundException {
        Long count = id;
        if ( count == null || count == 0){
            throw new ProductNotFoundException("Colud not find any Product with code " + count);
        }
        productRepository.deleteById(id);
    }

}
