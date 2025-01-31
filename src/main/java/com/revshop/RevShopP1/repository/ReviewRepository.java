package com.revshop.RevShopP1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revshop.RevShopP1.model.Buyer;
import com.revshop.RevShopP1.model.Product;
import com.revshop.RevShopP1.model.Review; 

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

 
@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {

	
	boolean existsByBuyerAndProduct(Buyer buyer, Product product);
	
	@Query(value = "SELECT * FROM review WHERE product_id = ?1 ORDER BY review_id DESC", nativeQuery = true)
    List<Review> findByProductId(Long productId);

}