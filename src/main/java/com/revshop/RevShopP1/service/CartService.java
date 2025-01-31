package com.revshop.RevShopP1.service;

import com.revshop.RevShopP1.model.Buyer;
import com.revshop.RevShopP1.model.Cart;
import com.revshop.RevShopP1.model.Product;
import com.revshop.RevShopP1.repository.BuyerRepository;
import com.revshop.RevShopP1.repository.CartRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    // Add product to cart
    public Cart addToCart(Cart cart) {
        return cartRepository.save(cart);
    }

    // Get cart items for a specific buyer
    public List<Cart> getCartByBuyer(Long buyerId) {
        return cartRepository.findByBuyer_BuyerId(buyerId);
    }

    // Check if a product is in the buyer's cart
    public Cart getCartItem(Long buyerId, Long productId) {
        return cartRepository.findByBuyer_BuyerIdAndProductId(buyerId, productId);
    }
    ////
//    public boolean isProductInCart(Long buyerId, Long productId) {
//        Cart cartItem = cartRepository.findByBuyerIdAndProductId(buyerId, productId);
//        return cartItem != null;
//    }
    @Autowired
    private BuyerRepository buyerRepository;
    
    @Transactional
    // Add product to cart
    public void addProductToCart(Long buyerId, Product product) {
        Buyer buyer = buyerRepository.findById(buyerId)
                .orElseThrow(() -> new RuntimeException("Buyer not found"));

        Optional<Cart> existingCart = cartRepository.findAllByBuyer(buyer)
            .stream()
            .filter(cart -> cart.getProduct().equals(product))
            .findFirst();

        if (existingCart.isPresent()) {
            // If the product is already in the cart, update the quantity
            Cart cart = existingCart.get();
            cart.setQuantity(cart.getQuantity() + 1);  // Increase quantity
            cartRepository.save(cart);
        } else {
            // Add new cart entry if the product is not in the cart
            Cart cart = new Cart();
            cart.setSeller(product.getSeller());
            cart.setBuyer(buyer);
            cart.setProduct(product);
            cart.setQuantity(1);  // Assuming starting quantity is 1
            cart.setPrice((int) product.getPrice());  // Set the price
            cartRepository.save(cart);
        }
    }
    @Transactional
    // Remove product from cart
    public void removeProductFromCart(Long buyerId, Product product) {
    	System.out.println(buyerId+"Servicecart");
        Buyer buyer = buyerRepository.findById(buyerId).get();
               
        System.out.println(buyerId+"Servicecart2");
        cartRepository.deleteByBuyerAndProduct_ProductId(buyer, product.getProductId());
    }

	public List<Cart> findAllByBuyer(Buyer buyer) {
		// TODO Auto-generated method stub
		return cartRepository.findAllByBuyer(buyer);
	}

	public boolean existsByBuyerAndProduct_ProductId(Buyer buyer, Long productId) {
		// TODO Auto-generated method stub
		return cartRepository.existsByBuyerAndProduct_ProductId(buyer, productId);
	}
	 public void clearCartForBuyer(Buyer buyer) {
	        List<Cart> cartItems = cartRepository.findAllByBuyer(buyer);
	        cartRepository.deleteAll(cartItems);  // Deletes all items for the buyer
	    }
	
    
    

}





 
    





