package com.empower.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.empower.demo.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
	
	
	 List<Product> findTop4ByCategory(String category);
	 List<Product> findTop8ByOrderByIdDesc(); 
}
