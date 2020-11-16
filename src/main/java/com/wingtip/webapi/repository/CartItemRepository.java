package com.wingtip.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wingtip.webapi.repository.entities.CartItemEntity;

@Repository
public interface CartItemRepository extends JpaRepository<CartItemEntity, String>{
	List<CartItemEntity> findAllByCartId(String cartId);
	List<CartItemEntity> findByProductId(int productId);
	void deleteByCartId(String cartId);
}
