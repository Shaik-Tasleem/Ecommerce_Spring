package com.revshop.RevShopP1.repository;

import com.revshop.RevShopP1.model.Buyer;
import com.revshop.RevShopP1.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {

    // Finds all cart items by buyer ID
    @Query(value = "SELECT * FROM cart WHERE buyer_id = :buyerId", nativeQuery = true)
    List<Cart> findByBuyer_BuyerId(Long buyerId);

    // Finds a specific product in the buyer's cart
    @Query(value = "SELECT * FROM cart WHERE buyer_id = :buyerId AND product_id = :productId", nativeQuery = true)
    Cart findByBuyer_BuyerIdAndProductId(Long buyerId, Long productId);

	
	
	boolean existsByBuyerAndProduct_ProductId(Buyer buyer, Long productId);

	void deleteByBuyerAndProduct_ProductId(Buyer buyer, Long productId);

	List<Cart> findAllByBuyer(Buyer buyer);
	

    
//    
//    @Query("SELECT c FROM CartItem c WHERE c.buyer.id = :buyerId AND c.product.id = :productId")
//    Cart findByBuyerIdAndProductId(@Param("buyerId") Long buyerId, @Param("productId") Long productId);

}
