package com.revshop.RevShopP1.controller;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revshop.RevShopP1.model.Seller;
import com.revshop.RevShopP1.service.EmailService;
import com.revshop.RevShopP1.service.SellerService;
import com.revshop.RevShopP1.utils.PasswordUtils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/ecom")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @Autowired
    private PasswordUtils pwd_obj;

    @Autowired
    private EmailService emailService;

    // Initialize seller session attribute
    @ModelAttribute("seller")
    public Seller getSeller() {
        return new Seller(); // Create an empty Seller object
    }

    @GetMapping("/sellerRegistration")
    public String registrationForm(Model model) {
        model.addAttribute("seller", new Seller());
        return "SellerRegistration";
    }

    @PostMapping("/sellerRegistration")
    public String registration(@ModelAttribute Seller seller) throws NoSuchAlgorithmException {
        sellerService.insertSeller(seller);
        return "LoginPage";
    }

    @PostMapping("/api/send-verificationseller")
    @ResponseBody
    public ResponseEntity<String> sendVerificationEmail(@RequestParam("email") String sellerEmail) {
        String otp = emailService.generateOtp();
        boolean emailSent = emailService.sendEmail(sellerEmail, otp);
        if (emailSent) {
            return ResponseEntity.ok("OTP sent successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send OTP.");
        }
	}
    @PostMapping("/api/verify-codeseller")
    @ResponseBody
    public ResponseEntity<String> verifyOtp(@RequestParam("email") String sellerEmail,
                                            @RequestParam("code") String otp) {
        boolean isOtpValid = emailService.verifyOtp(sellerEmail, otp);

        if (isOtpValid) {
            return ResponseEntity.ok("OTP verified successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid OTP.");
        }
    }

    @PostMapping("/seller/handleLogin")
    public String sellerLogin(@RequestParam(required = false) String email,
                              @RequestParam(required = false) String mobileNumber, 
                              @RequestParam String password, 
                              Model model,
                              HttpServletResponse response) throws NoSuchAlgorithmException {
        Seller seller_obj = null;
        
        if (email != null) {
            seller_obj = sellerService.getSellerDetailsByEmail(email);
        } else if (mobileNumber != null) {
            seller_obj = sellerService.getSellerDetailsByMobileNumber(mobileNumber);
        }

        if (seller_obj == null || !seller_obj.getPassword().equals(pwd_obj.hashPassword(password))) {
            String msg = "Invalid Email or Password...\nIf you are a new user Kindly...Register..\nTo access our Services..";
            model.addAttribute("errorMessage", msg);
            return "LoginPage";
        } else {
            // Store seller ID in a cookie
            Cookie sellerCookie = new Cookie("sellerId", seller_obj.getSellerId().toString());
            sellerCookie.setPath("/");
            sellerCookie.setMaxAge(24 * 60 * 60); // Expires in 1 day
            sellerCookie.setHttpOnly(true); // Cookie is only accessible by the server
            response.addCookie(sellerCookie);

            return "redirect:/ecom/SellerDashboard";
        }
    }
    @GetMapping("/SellerDashboard")
    public String diplaySellerDashboard() {
    	return "SellerDashboard";
    }
}

