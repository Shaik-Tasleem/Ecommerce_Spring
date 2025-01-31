package com.revshop.RevShopP1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.revshop.RevShopP1.model.Order_Detail;


@Repository
public interface OrderdetailRepository extends JpaRepository<Order_Detail, Long> {
    // Additional query methods if needed
	
	@Query("SELECT od FROM Order_Detail od WHERE od.order.buyer.buyerId = :buyerId ORDER BY od.order_detail_id DESC")
    List<Order_Detail> findByBuyerIdOrderByLocalDateDesc(Long buyerId);
    
    @Query("SELECT od FROM Order_Detail od WHERE od.seller.sellerId = :sellerId ORDER BY od.order_detail_id DESC")
    List<Order_Detail> findBySellerIdOrderByOrderDetailIdDesc(Long sellerId);
    
    List<Order_Detail> findByOrderOrderId(Long orderId);

}
