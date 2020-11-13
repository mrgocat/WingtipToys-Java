package com.wingtip.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wingtip.webapi.repository.entities.ProductEntity;
import com.wingtip.webapi.services.dto.ProductDto;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer>{
	List<ProductEntity> findAllByCategoryId(int categoryId);
	List<ProductEntity> findAllByProductNameContains(String name);
}
