package com.empower.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empower.demo.entity.Product;
import com.empower.demo.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired

	private ProductRepository pr;
	
	public Product create(Product product) {
		return pr.save(product);
	}
	
	public List<Product> read() {
		return pr.findAll();
	}
	
	public Product read(Integer id) {
		return pr.findById(id).orElse(null);
	}
	
	public Product update(Product product) {
		Product pro = read(product.getId());
		if(pro != null) {
			pro = product;
			pr.save(pro);
		}
		return pro;
	}
	
	public Product delete(Integer id) {
		
		Product pro = read(id);
		if(pro != null) {
			
			 pr.delete(pro);
		}
		return pro;
	}
	
	public List<Product>findTop4ByCategory(String category){
		return pr.findTop4ByCategory(category);
	}
	
	public  List<Product> findTop8ByOrderByIdDesc(){
		return pr.findTop8ByOrderByIdDesc();
	}
	
	
}
