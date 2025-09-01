package com.empower.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.empower.demo.entity.Cart;
import com.empower.demo.entity.UserInfo;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
	
	List<Cart> findByUser(UserInfo user);
	@Query("SELECT c FROM Cart c WHERE c.user.username=:username and c.product.id=:id")
	Cart findCartsByUserProduct(@Param("username") String username,@Param("id") Integer id);

}
