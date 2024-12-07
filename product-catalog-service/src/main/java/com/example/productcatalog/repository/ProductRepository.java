package com.example.productcatalog.repository;

import com.example.productcatalog.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategoryCategoryIdAndIsActiveTrueAndInventoryGreaterThan(Long categoryId, int inventory);
}

