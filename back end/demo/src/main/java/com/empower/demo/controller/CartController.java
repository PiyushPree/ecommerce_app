package com.empower.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empower.demo.entity.Cart;
import com.empower.demo.service.CartService;
import com.empower.demo.service.UserInfoService;

@RestController
@RequestMapping("/api/cart")

@CrossOrigin("*")
public class CartController {
	
	@Autowired
	private CartService cs;
	
	@Autowired
	private UserInfoService us;
	
	@PostMapping
	 @PreAuthorize("hasAuthority('user')")
	public Cart create(@RequestBody Cart cart) {
		//check if this user, has a cart for this product
		Cart existingCart = cs.findCartsByUserProduct(cart.getUser().getUsername(), cart.getProduct().getId());
		if(existingCart==null)
		{
			cart.setQuantity(1);			
		}else
		{
			cart.setQuantity(existingCart.getQuantity()+1);
			cart.setCid(existingCart.getCid());
		}
		cart.setStatus("pending");
			return cs.create(cart);
	}
	
	@PostMapping("/subtract")
	 @PreAuthorize("hasAuthority('user')")
	public Cart subtract(@RequestBody Cart cart) {
		Cart existingCart = cs.findCartsByUserProduct(cart.getUser().getUsername(), cart.getProduct().getId());
		if(existingCart != null && existingCart.getQuantity()>1) {
			cart.setQuantity(existingCart.getQuantity()-1);
			cart.setCid(existingCart.getCid());
			cart.setStatus("pending");
			return cs.create(cart);
		}else {
			cs.delete(cart.getCid());
			return null;
		}
		
	}
	
	@GetMapping
	 @PreAuthorize("hasAuthority('user')")
	
	public List<Cart> read() {
		return cs.read();
	}
	
	@GetMapping("/{cid}")
	 @PreAuthorize("hasAuthority('user')")
	
	public Cart read(@PathVariable("cid") Integer cid) {		
		return cs.read(cid);
	}
	
	@PutMapping
	 @PreAuthorize("hasAuthority('user')")
	public Cart update(@RequestBody Cart cart) {
		return cs.update(cart);
	}
	
	@DeleteMapping("/{cid}")
	 @PreAuthorize("hasAuthority('user')")
	public Cart delete(@PathVariable("cid") Integer cid) {
		return cs.delete(cid);
	}
	
	@GetMapping("/user/{username}")
	 @PreAuthorize("hasAuthority('user')")
	public List<Cart> findCartByUser(@PathVariable("username") String username)
	{
		System.out.println("Reached findCartByUser");
		List<Cart> allcart = cs.findByUser(us.findUserById(username));
		return allcart.stream()
		.filter((c->c.getStatus().equals("pending")))
		.collect(Collectors.toList());
//		return cs.findByUser(us.findUserById(username));
	}

}
