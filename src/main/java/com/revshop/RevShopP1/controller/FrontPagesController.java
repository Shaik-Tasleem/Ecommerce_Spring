package com.revshop.RevShopP1.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.revshop.RevShopP1.model.Product;
import com.revshop.RevShopP1.model.Wishlist;
import com.revshop.RevShopP1.service.ProductService;
import com.revshop.RevShopP1.service.WishlistService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Controller
@RequestMapping("/ecom")

public class FrontPagesController {
    @Autowired
	private ProductService productService;
    @Autowired
	private WishlistService wishlistService;
	@GetMapping("/LoginPage")
	public String loginPage(Model model) {
		return "LoginPage";
	}
	@GetMapping
	public String frontPage(Model model) {
		return "indexPage";
	}
	@GetMapping("/welcomepage")
	public String welcomePage(Model model) {
		return "welcomepage";
	}
    @GetMapping("/emptyCart")
    public String emptyCartPage(Model model) {
        return "emptyCart";
    }
    @GetMapping("/category/{categoryId}")
    public String showProductsByCategory(@PathVariable Long categoryId, Model model) {
        List<Product> products = productService.getProductsByCategoryId(categoryId);
        model.addAttribute("products", products);
        return "BuyerdashboardExtend";  // HTML page to display the products
    }
    @GetMapping("/logout/category/{categoryId}")
    public String showProductswithoutlogoutByCategory(@PathVariable Long categoryId, Model model) {
        List<Product> products = productService.getProductsByCategoryId(categoryId);
        model.addAttribute("products", products);
        return "logoutBuyerdashboardExtend";  // HTML page to display the products
    }
//    @GetMapping("/wishlist/view")
//    
//
//    // Create a new Wishlist entry
//    public String viewWishlist(Model model, HttpServletRequest request) {
//    Long buyerId = getBuyerIdFromCookies(request);
//
//    if (buyerId != null) {
//        List<Wishlist> wishlistItems = wishlistService.getWishlistByBuyer(buyerId);
//        List<Product> productInWish=new ArrayList<>();
//        for(Wishlist i:wishlistItems) {
//        	productInWish.add(i.getProduct());
//        	//System.out.println();
//        }
//        for(Product i:productInWish) {
//        	System.out.println(i.getProductName());
//        }
//        model.addAttribute("wishlistItems", productInWish);
//    }
//
//    return "wishlist-view"; // Render the wishlist Thymeleaf view
//}    // Helper method to get buyer ID from cookies
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
}
 
