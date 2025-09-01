package com.empower.demo.controller;

import java.util.List;

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

import com.empower.demo.entity.Product;
import com.empower.demo.service.ProductService;


@RestController
@RequestMapping("/api/product")
//@CrossOrigin(origins = {"http://localhost:3000/admin"})
@CrossOrigin("*")
public class ProductController {
	
	@Autowired
	private ProductService ps;
	
	@PostMapping
    @PreAuthorize("hasAuthority('admin')")
	public Product add(@RequestBody Product product) {
		return ps.create(product);
	};
	
	@GetMapping
//	 @PreAuthorize("hasAuthority('admin')")
	
//	@PreAuthorize("hasAnyRole('admin','user')")
//	@PreAuthorize("hasRole('admin') || hasRole('user')")
//	@PreAuthorize("hasAnyAuthority('admin','user')")
	public List<Product> findAllProduct() {
		System.out.println("reached findAllProduct");
		return ps.read();
	};
	
	@GetMapping("/{id}")
	public Product findProductById(@PathVariable Integer id) {
		return ps.read(id);
	};
	
	@PutMapping
	public Product update(@RequestBody Product product) {
		return ps.update(product);
	};
	
	@DeleteMapping("/{id}")
	 @PreAuthorize("hasAuthority('admin')")
	public Product delete(@PathVariable Integer id) {
		return ps.delete(id);
	};
	
	 @GetMapping("/popularinwomen")
//	    @PreAuthorize("hasAnyAuthority('admin','user')")
	    public List<Product> findTop4ByCategory() {
	        return ps.findTop4ByCategory("women");
	    }
	 
	 @GetMapping("/latest")
//	    @PreAuthorize("hasAnyAuthority('admin','user')")
	    public List<Product> findLatestEightProducts() {
	        return ps.findTop8ByOrderByIdDesc();
	    }

}
