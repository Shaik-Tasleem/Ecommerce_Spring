package com.revshop.RevShopP1.service;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

@Service
public class OtpService {

    private Map<String, String> otpStorage = new HashMap<>();  // You can store this in a DB in real-world apps
    private SecureRandom random = new SecureRandom();

    // Generate and return a 6-digit OTP
    public String generateOtp(String email) {
        String otp = String.format("%06d", random.nextInt(999999));
        otpStorage.put(email, otp); // Store OTP with the email as the key
        return otp;
    }

    // Verify the OTP entered by the user
    public boolean verifyOtp(String email, String otp) {
        String storedOtp = otpStorage.get(email);
        return storedOtp != null && storedOtp.equals(otp);
    }
}
