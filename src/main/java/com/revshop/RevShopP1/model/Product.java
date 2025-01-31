package com.revshop.RevShopP1.model;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @ManyToOne
    @JoinColumn(name = "sellerId", nullable = false)
    private Seller seller;

    @ManyToOne
    @JoinColumn(name = "categoryId", nullable = false)
    private Category category;


    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Payments> payments = new ArrayList<>();

    @OneToMany(mappedBy="product",cascade = CascadeType.ALL)
    private List<Wishlist> wishlist=new ArrayList<>();
    
//    @OneToMany(mappedBy="product",cascade = CascadeType.ALL)
//    private List<Wishlist> cart=new ArrayList<>();
//    
    public List<Payments> getPayments() {
		return payments;
	}
    
    
    @OneToMany(mappedBy="product",cascade = CascadeType.ALL)
    private List<Wishlist> review=new ArrayList<>();
    
    

//	public List<Wishlist> getReview() {
//		return review;
//	}

//	public void setReview(List<Wishlist> review) {
//		this.review = review;
//	}

	public void setPayments(List<Payments> payments) {
		this.payments = payments;
	}

	public List<Wishlist> getWishlist() {
		return wishlist;
	}

	public void setWishlist(List<Wishlist> wishlist) {
		this.wishlist = wishlist;
	}

	private String productName;
    private String productDescription;
    private double price;
    private double discountPrice;
    private int threshold;
    private int quantity;
    private String image;

    // Getters and Setters
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
