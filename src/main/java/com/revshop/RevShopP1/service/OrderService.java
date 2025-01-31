package com.revshop.RevShopP1.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revshop.RevShopP1.model.Buyer;
import com.revshop.RevShopP1.model.Orders;
import com.revshop.RevShopP1.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    // Method to save an order
    public void saveOrder(Orders order) {
        orderRepository.save(order);
    }
    public List<Orders> getOrdersByBuyerId(Long buyerId) {
        return orderRepository.findByBuyer_BuyerId(buyerId);
    }
    
    public Orders createOrder(Buyer customer, double totalValue, String deliveryAddress) {
        Orders order = new Orders();
        order.setBuyer(customer);
        order.setTotalPrice(totalValue);
        order.setShippingAddress(deliveryAddress);
        order.setOrderDate(LocalDate.now());


        return orderRepository.save(order);
    }
}
