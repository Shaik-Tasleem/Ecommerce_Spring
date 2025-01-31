package com.revshop.RevShopP1.controller;

import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.revshop.RevShopP1.model.Buyer;
import com.revshop.RevShopP1.service.BuyerService;
import com.revshop.RevShopP1.service.ForgotService;
import com.revshop.RevShopP1.service.SellerService;
import com.revshop.RevShopP1.utils.PasswordUtils;

@Controller
@RequestMapping("/ecom")
public class ForgotPasswordController {
	private final Map<String, String> verifyCodeStorage = new ConcurrentHashMap<>();
	private String em="";
	@Autowired
	private ForgotService forgotService;
	@Autowired
	private BuyerService buyerService;
	@Autowired
	private SellerService sellerService;
	@Autowired PasswordUtils pwd_obj;
	@GetMapping("/ForgotPassword")
	public String showForgotPasswordPage(@RequestParam("userType") String userType,Model model) {
		model.addAttribute("userType", userType);
	    return "ForgotPassword"; // This returns the forgot-password.html template
	}
	@PostMapping("/api/send-verification/forgot")
    @ResponseBody
    public ResponseEntity<String> sendVerificationCode(@RequestParam("email") String email, @RequestParam("userType") String userType) {
        // Generate a random 6-digit code
        String verificationCode = String.format("%06d", new Random().nextInt(999999));
        em=email;
        Buyer buyer_obj=buyerService.getBuyerDetailsByEmail(em);
        if(buyer_obj==null) {
        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Check your Email....\n If you are a new to our website\n Kindly Register"); 
        }
        else {
	        try {
	        	forgotService.sendVerificationEmail(email, verificationCode);
	        	em=email;
	        	verifyCodeStorage.put(email, verificationCode);
	        	return ResponseEntity.ok("Verification Code Sent Successfully");
	        } catch (Exception e) {
	        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to Send Verification Code");
	        }
        }
    }

    @PostMapping("/api/verify-code/forgot")
    @ResponseBody
    public ResponseEntity<String> verifyCode(@RequestParam("code") String code,@RequestParam("userType") String userType) {
    	String sessionCode = verifyCodeStorage.get(em);
        if (sessionCode != null && sessionCode.equals(code)) {
        	return ResponseEntity.ok("Code verified successfully.");
        } else {
        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid verification code.");
        }
    }

    @PostMapping("/api/reset-password/forgot")
    public String resetPassword(
            @RequestParam("new-password") String newPassword, 
            @RequestParam("confirm-password") String confirmPassword, 
            Model model,@RequestParam("userType") String userType) throws NoSuchAlgorithmException {
        if (!newPassword.equals(confirmPassword)) {
            model.addAttribute("error", "Passwords do not match.");
            return "ForgotPassword";  // return to the reset form
        }

        if (em == null) {
            model.addAttribute("error", "Session expired, please try again.");
            return "ForgotPassword"; // return to the reset form
        }
        if(userType.equals("buyer")) {
        	buyerService.updateBuyerPassword(em, pwd_obj.hashPassword(newPassword));
        }
        else if(userType.equals("seller")) {
        	sellerService.updateSellerPassword(em, pwd_obj.hashPassword(newPassword));
        }
        model.addAttribute("message", "Password reset successfully.");
        return "LoginPage";  // Redirect to the login page after success
    }
}
