package com.revshop.RevShopP1.service;
import java.util.Optional;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revshop.RevShopP1.model.Buyer;
import com.revshop.RevShopP1.repository.BuyerRepository;
import com.revshop.RevShopP1.utils.PasswordUtils;

@Service
public class BuyerService {
	@Autowired
	private BuyerRepository buyerRepo;
	@Autowired
	private PasswordUtils pwd_obj;
	public void insertBuyer(Buyer buyer) throws NoSuchAlgorithmException {
		buyer.setPassword(pwd_obj.hashPassword(buyer.getPassword()));
		buyerRepo.save(buyer);
	}
	public void deleteBuyer(Long buyerId) {
        buyerRepo.deleteById(buyerId);
    }

    public Buyer updateBuyer(Long buyerId, Buyer updatedBuyer) {
        Optional<Buyer> existingBuyer = buyerRepo.findById(buyerId);
        
        if (existingBuyer.isPresent()) {
            Buyer buyer = existingBuyer.get();
            buyer.setFirstName(updatedBuyer.getFirstName());  
            buyer.setLastName(updatedBuyer.getLastName());
            buyer.setEmail(updatedBuyer.getEmail());
            buyer.setMobileNumber(updatedBuyer.getMobileNumber());
            buyer.setStreet(updatedBuyer.getStreet());
            buyer.setCity(updatedBuyer.getCity());
            buyer.setPostalCode(updatedBuyer.getPostalCode());  
            buyer.setState(updatedBuyer.getState());  
            buyer.setCountry(updatedBuyer.getCountry());  
            return buyerRepo.save(buyer); 
        }
        
        return null; 
    }
	public Buyer getBuyerDetailsByEmail(String email) {
		System.out.println(buyerRepo.findByEmail(email));
		return buyerRepo.findByEmail(email);
	}
	public Buyer getBuyerDetailsByMobileNumber(String mobileNumber) {
		return buyerRepo.findByMobileNumber(mobileNumber);
	}
	public void updateBuyerPassword(String email, String newPassword) throws NoSuchAlgorithmException {
        Buyer buyer = buyerRepo.findByEmail(email);
        buyer.setPassword(newPassword);
        buyerRepo.save(buyer);
    }
	public Buyer findBuyerDetailsById(Long buyerId) {
		// TODO Auto-generated method stub
		return buyerRepo.findById(buyerId).get();
	}
 
}
