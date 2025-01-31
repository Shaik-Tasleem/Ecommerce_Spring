package com.revshop.RevShopP1.repository;

import com.revshop.RevShopP1.model.Buyer;
import com.revshop.RevShopP1.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
	
	@Query(value="select * from wishlist where buyer_id=:buyerId",nativeQuery=true)
    // Finds all wishlists by buyer's ID
    List<Wishlist> findByBuyer_BuyerId(Long buyerId);

    // Finds a wishlist for a buyer that contains a specific product
    @Query(value="SELECT * FROM Wishlist where buyer_id=:buyerId and product_id=:productId",nativeQuery=true)
    Wishlist findByBuyer_BuyerIdAndProductId(Long buyerId, Long productId);
//
//    // Deletes a product from the wishlist by buyer ID and product ID
//    @Query("DELETE FROM Wishlist WHERE w.buyer.buyerId = :buyerId AND w.product.productId=:productId")
//    void deleteByBuyer_BuyerIdAndProductId(Long buyerId, Long productId);
    
    boolean existsByBuyerAndProduct_ProductId(Buyer buyer, Long productId);
	void deleteByBuyerAndProduct_ProductId(Buyer buyer, Long productId);
	List<Wishlist> findAllByBuyer(Buyer buyer);
	
	

    
}