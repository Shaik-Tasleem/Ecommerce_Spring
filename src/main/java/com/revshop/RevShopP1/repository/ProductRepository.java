package com.revshop.RevShopP1.repository;

import com.revshop.RevShopP1.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Custom search method to search by product name or category name
    List<Product> findByProductNameContainingIgnoreCaseOrCategory_CategoryNameContainingIgnoreCase(String productName, String categoryName);

    // Corrected method to find products by category ID
    List<Product> findByCategory_CategoryId(Long categoryId);
}
