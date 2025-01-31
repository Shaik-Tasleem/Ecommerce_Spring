package com.revshop.RevShopP1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revshop.RevShopP1.model.Order_Detail;
import com.revshop.RevShopP1.repository.OrderdetailRepository;

@Service
public class OrderdetailService {
	
	@Autowired
	private OrderdetailRepository orderDetailRepository;
	
	public void addOrderDetails(Order_Detail orderDetails) {
        orderDetailRepository.save(orderDetails);
    }

	public List<Order_Detail> getOrdersByCustomerId(Long customerId) {
        return orderDetailRepository.findByBuyerIdOrderByLocalDateDesc(customerId);
    }
	
	public List<Order_Detail> getOrderDetailByOrderId(Long orderId) {
        return orderDetailRepository.findByOrderOrderId(orderId); // Fetch details by orderId
    }
	
	
	public List<Order_Detail> getOrdersBySellerId(Long sellerId) {
		return orderDetailRepository.findBySellerIdOrderByOrderDetailIdDesc(sellerId);
    }

    public void updateOrderStatus(Long orderId, String status) {
    	Order_Detail orderDetail = orderDetailRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
            orderDetail.setStatus(status);
            orderDetailRepository.save(orderDetail);
    }



}
