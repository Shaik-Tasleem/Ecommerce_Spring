package com.revshop.RevShopP1.controller;

import com.revshop.RevShopP1.model.Buyer;
import com.revshop.RevShopP1.model.Cart;
import com.revshop.RevShopP1.model.Product;
import com.revshop.RevShopP1.model.Seller;
import com.revshop.RevShopP1.service.BuyerService;
import com.revshop.RevShopP1.service.CartService;
import com.revshop.RevShopP1.service.ProductService;
import com.revshop.RevShopP1.service.SellerService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/ecom")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private BuyerService buyerService;

    @Autowired
    private SellerService sellerService;

    // Add product to cart
    @PostMapping("/cart/add/{productId}")
    public String addToCart(@PathVariable Long productId, HttpServletRequest request, HttpServletResponse response) {
        Long buyerId = null;
        Long sellerId = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("buyerId")) {
                    buyerId = Long.parseLong(cookie.getValue());
                    break;
                }
            }
        }
        
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("sellerId")) {
                	sellerId = Long.parseLong(cookie.getValue());
                    break;
                }
            }
        }

        if (buyerId != null) {
            Buyer buyer = buyerService.findBuyerDetailsById(buyerId);
            Product product = productService.getProductById(productId);
            Seller seller = sellerService.findById(3L); // Assuming seller exists

            // Create a new Cart entry
            Cart cart = new Cart();
            cart.setBuyer(buyer);
            cart.setProduct(product);
            cart.setQuantity(1); // Default quantity
            cart.setPrice((int) product.getPrice()); // Set product price 
            cart.setSeller(seller);

            cartService.addToCart(cart);
        }

        return "redirect:/ecom/cart/view";
    }
    
    

    // View cart items

    
//    @GetMapping("/products/{productId}")
//    public String viewProduct(@PathVariable Long productId, Model model, HttpServletRequest request) {
//        Long buyerId = getBuyerIdFromCookies(request); // Custom method to get buyerId from cookies
//        Product product = productService.getProductById(productId);
//        
//        boolean productInCart = cartService.isProductInCart(buyerId, productId);
//        
//        model.addAttribute("product", product);
//        model.addAttribute("productInCart", productInCart);
//        
//        return "products"; // Return the Thymeleaf view for the product
//    }
//

    // Helper method to get buyer ID from cookies
    private Long getBuyerIdFromCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("buyerId".equals(cookie.getName())) {
                    try {
                        return Long.parseLong(cookie.getValue());
                    } catch (NumberFormatException e) {
                        return null;
                    }
                }
            }
        }
        return null;
    }
    
    private Long getSellerIdFromCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("sellerId".equals(cookie.getName())) {
                    try {
                        return Long.parseLong(cookie.getValue());
                    } catch (NumberFormatException e) {
                        return null;
                    }
                }
            }
        }
        return null;
    }
//    @GetMapping("/cart/view")
//    public String viewCart(Model model, HttpServletRequest request) {
//        // Retrieve buyerId from cookies
//        Long buyerId = getBuyerIdFromCookies(request);
//
//        // Ensure buyerId is available
//        if (buyerId != null) {
//            // Fetch all cart items for the buyer
//            List<Cart> cartItems = cartService.getCartByBuyer(buyerId);
//
//            // Add cart items to the model
//            model.addAttribute("cartItems", cartItems);
//        } else {
//            // If no buyerId is found, redirect or show an empty cart
//            model.addAttribute("cartItems", List.of());
//        }
//
//        // Return the Thymeleaf view for the cart (assuming it's named "cart.html")
//        return "cart-view";
//    }
}
