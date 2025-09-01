package com.empower.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "SHOPSY_CART")
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer cid;
	@ManyToOne
	private UserInfo user;
	@ManyToOne
	private Product product;
	private Integer quantity;
	@Column(columnDefinition = "varchar2(50) default 'pending'")
	private String status;
	
	
	Cart(){}

	public Cart(Integer cid, UserInfo user, Product product) {
		super();
		this.cid = cid;
		this.user = user;
		this.product = product;
	}
	
	

	public Cart(Integer cid, UserInfo user, Product product, Integer quantity) {
		super();
		this.cid = cid;
		this.user = user;
		this.product = product;
		this.quantity = quantity;
	}
	
	

	public Cart(Integer cid, UserInfo user, Product product, Integer quantity, String status) {
		super();
		this.cid = cid;
		this.user = user;
		this.product = product;
		this.quantity = quantity;
		this.status = status;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Cart [cid=" + cid + ", user=" + user + ", product=" + product + ", quantity=" + quantity + ", status="
				+ status + "]";
	}

	

	

	
	
	

}
