package com.revshop.RevShopP1.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "seller")
public class Seller implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long sellerId;

	@OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
	private List<Product> products = new ArrayList<>();

	@OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
	private List<Payments> payments = new ArrayList<>();

	@OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
	private List<Order_Detail> orders = new ArrayList<>();

	@OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
	private List<Cart> cart = new ArrayList<>();

	private String firstName;
	private String lastName;

	@Column(unique = true)
	private String email;

	@Column(unique = true)
	private String mobileNumber;

	private String password; // Removed uniqueness constraint
	private String bussinessName;
	private String street;
	private String city;
	private int postalCode;
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public List<Payments> getPayments() {
		return payments;
	}

	public void setPayments(List<Payments> payments) {
		this.payments = payments;
	}

	public List<Order_Detail> getOrders() {
		return orders;
	}

	public void setOrders(List<Order_Detail> orders) {
		this.orders = orders;
	}

	public List<Cart> getCart() {
		return cart;
	}

	public void setCart(List<Cart> cart) {
		this.cart = cart;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

	private String state;
	private String country;
	public Long getSellerId() {
		return sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getBussinessName() {
		return bussinessName;
	}

	public void setBussinessName(String bussinessName) {
		this.bussinessName = bussinessName;
	}
}