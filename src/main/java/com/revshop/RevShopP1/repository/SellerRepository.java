package com.revshop.RevShopP1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revshop.RevShopP1.model.Seller;

public interface SellerRepository extends JpaRepository<Seller,Long>{

	Seller findByEmail(String email);

	Seller findByMobileNumber(String mobileNumber);

}
