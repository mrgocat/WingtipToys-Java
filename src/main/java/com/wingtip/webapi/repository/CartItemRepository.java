package com.wingtip.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wingtip.webapi.repository.entities.CartItemEntity;

public interface CartItemRepository extends JpaRepository<CartItemEntity, String>{
	List<CartItemEntity> findAllByCartId(String cartId);
	void deleteByCartId(String cartId);
}
