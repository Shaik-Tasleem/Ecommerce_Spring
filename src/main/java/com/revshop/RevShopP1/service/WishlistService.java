package com.revshop.RevShopP1.service;

import com.revshop.RevShopP1.model.Buyer;
import com.revshop.RevShopP1.model.Product;
import com.revshop.RevShopP1.model.Wishlist;
import com.revshop.RevShopP1.repository.BuyerRepository;
import com.revshop.RevShopP1.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;
    @Autowired
    private WishlistRepository wislistRepo;
    @Autowired
    private BuyerRepository buyerRepo;
    

    // Add product to wishlist
    public Wishlist addToWishlist(Wishlist wishlist) {
        return wishlistRepository.save(wishlist);
    }

    // Get wishlist for a specific buyer
    public List<Wishlist> getWishlistByBuyer(Long buyerId) {
        return wishlistRepository.findByBuyer_BuyerId(buyerId);
    }

    // Check if a product is in the buyer's wishlist
    public Wishlist getWishlistItem(Long buyerId, Long productId) {
        return wishlistRepository.findByBuyer_BuyerIdAndProductId(buyerId, productId);
    }

//    // Remove product from wishlist
//    public void removeFromWishlist(Long buyerId, Long productId) {
//        wishlistRepository.deleteByBuyer_BuyerIdAndProductId(buyerId, productId);
//    }
//   
 
    public void removeFromWishlist(Long buyerId, Long productId) {
        List<Wishlist> wishlistItems = (List<Wishlist>) wishlistRepository.findByBuyer_BuyerIdAndProductId(buyerId, productId);
        if (wishlistItems != null && !wishlistItems.isEmpty()) {
            wishlistRepository.deleteAll(wishlistItems);
        }
    }
    public boolean existsByBuyerAndProduct_ProductId(Buyer buyer, Long productId) {
		// TODO Auto-generated method stub
		return wislistRepo.existsByBuyerAndProduct_ProductId(buyer,productId);
	}
	@Transactional
	public void addProductToWish(Long buyerId,Product product) {
		Buyer buyer=buyerRepo.findById(buyerId).get();
		Optional<Wishlist> existingWish=wislistRepo.findAllByBuyer(buyer).stream()
				.filter(wishlist->wishlist.getProduct().equals(product))
				.findFirst();
		if(existingWish.isPresent()) {
			Wishlist wishlist=existingWish.get();
			wishlist.setQuantity(wishlist.getQuantity()+1);
			wislistRepo.save(wishlist);
		}
		else {
			Wishlist wishlist=new Wishlist();
			wishlist.setBuyer(buyer);
			wishlist.setProduct(product);
			wishlist.setQuantity(1);
			wishlist.setSeller(product.getSeller());
			wislistRepo.save(wishlist);
		}
	}
	@Transactional
	public void removeProductFromWishlist(Long buyerId,Product product) {
		Buyer buyer = buyerRepo.findById(buyerId)
                .orElseThrow(() -> new RuntimeException("Buyer not found"));
		wislistRepo.deleteByBuyerAndProduct_ProductId(buyer, product.getProductId());
	}
	public List<Wishlist> findAllByBuyer(Buyer buyer){
		return wislistRepo.findAllByBuyer(buyer);
	}
}


	 

