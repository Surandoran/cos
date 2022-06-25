package com.example.cosmetic.repository;

import com.example.cosmetic.dto.Product;
import org.hibernate.Criteria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    /* 상품 리스트 */
    List<Product> findAll();
    /* 제품 찾기 */
    Optional<Product> findByName(String name);

}
