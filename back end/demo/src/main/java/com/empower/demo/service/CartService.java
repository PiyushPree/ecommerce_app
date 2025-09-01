package com.empower.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empower.demo.entity.Cart;
import com.empower.demo.entity.UserInfo;
import com.empower.demo.repository.CartRepository;

@Service
public class CartService {
	@Autowired
	private CartRepository cr;
	
	public Cart create(Cart cart) {
		cart.setStatus("pending");
		return cr.save(cart);	
	}
	
	public List<Cart> read() {
		return cr.findAll();
	}
	
	public Cart read(Integer id) {
		return cr.findById(id).orElse(null);
	}
	
	public Cart update(Cart cart) {
		Cart c = read(cart.getCid());
		if(c!= null) {
			c = cart;
			cr.save(c);
		}
		return c;
	}
	
	public Cart delete(Integer id) {
		Cart c = read(id);
		if(c!= null) {
			cr.delete(c);
			
		}
		return c;
	}
	
	public List<Cart> findByUser(UserInfo user) {
		return cr.findByUser(user);
		
	}

	
	public Cart findCartsByUserProduct(String username, Integer id)
	{
		return cr.findCartsByUserProduct(username, id);
	}
}
