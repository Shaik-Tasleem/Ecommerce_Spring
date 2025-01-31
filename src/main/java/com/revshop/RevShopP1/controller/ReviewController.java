//package com.revshop.RevShopP1.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import com.revshop.RevShopP1.model.Buyer;
//import com.revshop.RevShopP1.model.Product;
//import com.revshop.RevShopP1.model.Review;
//import com.revshop.RevShopP1.service.BuyerService;
//import com.revshop.RevShopP1.service.ProductService;
//import com.revshop.RevShopP1.service.ReviewService;
//
//import jakarta.servlet.http.Cookie;
//import jakarta.servlet.http.HttpServletRequest;
//
//@Controller
//@RequestMapping("/reviews")
//public class ReviewController {
//
//    @Autowired
//    private ReviewService reviewService;
//
//    @Autowired
//    private ProductService productService;
//
//    @Autowired
//    private BuyerService buyerService;
//
//    // Add review form
//    @PostMapping("/add")
//    public String addReview(@RequestParam Long productId, 
//                            @RequestParam String comment, 
//                            @RequestParam int rating, 
//                            HttpServletRequest request) {
//        // Get buyer ID from cookies (assuming user is logged in)
//        Long buyerId = getBuyerIdFromCookies(request);
//        Buyer buyer = buyerService.findBuyerDetailsById(buyerId);
//        Product product = productService.getProductById(productId);
//
//        Review review = new Review();
//        review.setBuyer(buyer);
//        review.setProduct(product);
//        review.setComment(comment);
//        review.setRating(rating);
//
//        reviewService.addReview(review);
//
//        return "redirect:/product/view/" + productId;
//    }
//
//    // Helper method to retrieve buyerId from cookies
//    private Long getBuyerIdFromCookies(HttpServletRequest request) {
//        Cookie[] cookies = request.getCookies();
//        if (cookies != null) {
//            for (Cookie cookie : cookies) {
//                if ("buyerId".equals(cookie.getName())) {
//                    return Long.parseLong(cookie.getValue());
//                }
//            }
//        }
//        return null;
//    }
//}
