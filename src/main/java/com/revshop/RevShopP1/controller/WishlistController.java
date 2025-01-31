 

package com.revshop.RevShopP1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.revshop.RevShopP1.model.Buyer;
import com.revshop.RevShopP1.model.Product;
import com.revshop.RevShopP1.model.Seller;
import com.revshop.RevShopP1.model.Wishlist;
import com.revshop.RevShopP1.service.BuyerService;
import com.revshop.RevShopP1.service.ProductService;
import com.revshop.RevShopP1.service.SellerService;
import com.revshop.RevShopP1.service.WishlistService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/ecom")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    @Autowired
    private ProductService productService;

    @Autowired
    private BuyerService  buyerService;

    @Autowired
    private SellerService  sellerService;

    // Add product to wishlist
    @PostMapping("/wishlist/add/{productId}")
    public String addToWishlist(@PathVariable Long productId, HttpServletRequest request, HttpServletResponse response) {
        // Retrieve buyer ID from the cookie
        Long buyerId = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("buyerId")) {
                    buyerId = Long.parseLong(cookie.getValue());
                    break;
                }
            }
        }

        if (buyerId != null) {
        	Buyer buyer = buyerService.findBuyerDetailsById(buyerId); // Assuming you have this method in BuyerService
        	Product product=productService.getProductById(productId);
            // Fetch the seller object manually (for demonstration, fetching seller with ID 3)
            Seller seller = sellerService.findById(3L); // Assuming this seller exists and the method works

            // Create a new Wishlist entry
            Wishlist wishlist = new Wishlist();
            wishlist.setBuyer(buyer);
            wishlist.setProduct(product); // Set the product ID
            wishlist.setQuantity(1); // Default quantity
            wishlist.setSeller(seller); // Set the Seller object, which is required

            // Save the Wishlist entry
            wishlistService.addToWishlist(wishlist);
        }

            return "redirect:/ecom/wishlist/view";
        }
    
    



	// Method to remove a product from the wishlist
    @PostMapping("/wishlist/remove/{productId}")
    public String removeFromWishlist(@PathVariable Product productId, HttpServletRequest request) {
        Long buyerId = null;

        // Retrieve buyer ID from cookies
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("buyerId")) {
                    buyerId = Long.parseLong(cookie.getValue());
                    break;
                }
            }
        }

        if (buyerId != null) {
            // Call service to remove the product from wishlist
        	System.out.println(buyerId);
        	System.out.println(productId);
            wishlistService.removeProductFromWishlist(buyerId, productId);
        }

        // Redirect back to wishlist view
        return "redirect:/ecom/wishlist/view";
    }
    
    
   // @GetMapping("/wishlist/view")
    

//        // Create a new Wishlist entry
//    public String viewWishlist(Model model, HttpServletRequest request) {
//        Long buyerId = getBuyerIdFromCookies(request);
//
//        if (buyerId != null) {
//            List<Wishlist> wishlistItems = wishlistService.getWishlistByBuyer(buyerId);
//            System.out.println(buyerId);
//            model.addAttribute("wishlistItems", wishlistItems);
//            System.out.println(wishlistItems);
//        }
//
//        return "wishlist-view"; // Render the wishlist Thymeleaf view
//    }
//       
//    
//
//    // View wishlist
//    

    // Helper method to get buyer ID from cookies
//    private Long getBuyerIdFromCookies(HttpServletRequest request) {
//        Cookie[] cookies = request.getCookies();
//        if (cookies != null) {
//            for (Cookie cookie : cookies) {
//                if ("buyerId".equals(cookie.getName())) {
//                    try {
//                        return Long.parseLong(cookie.getValue());
//                    } catch (NumberFormatException e) {
//                        return null;
//                    }
//                }
//            }
//        }
//        return null;
//    }
      
}

