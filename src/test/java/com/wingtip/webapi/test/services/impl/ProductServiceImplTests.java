package com.wingtip.webapi.test.services.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import com.wingtip.webapi.exception.ResourceNotFoundException;
import com.wingtip.webapi.repository.ProductRepository;
import com.wingtip.webapi.repository.entities.CategoryEntity;
import com.wingtip.webapi.repository.entities.ProductEntity;
import com.wingtip.webapi.services.ProductService;
import com.wingtip.webapi.services.dto.ProductDto;
import com.wingtip.webapi.services.impl.ProductServiceImpl;

import static org.assertj.core.api.Assertions.*;

// @SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class ProductServiceImplTests {
	@InjectMocks
	ProductServiceImpl productService;
	
	@Mock
	ProductRepository productRepository;
	
	ProductEntity entity;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		entity = new ProductEntity();
		entity.setId(1);
		entity.setProductName("Car Toy");
		entity.setImagePath("cars.png");
		entity.setUnitPrice(23.11);
		entity.setDescription("very good car toy.");
		CategoryEntity category = new CategoryEntity();
		category.setCategoryName("Cars");
		category.setId(101);
		entity.setCategory(category);
	}
	
	@Test
	public void getProductTest() {
		
		Mockito.when(productRepository.getOne(entity.getId())).thenReturn(entity);
		
		ProductDto dto = productService.getProduct(entity.getId());

		assertThat(dto).isNotNull();
		assertThat(dto.getProductName()).isEqualTo(entity.getProductName());
		
	}
	@Test
	public void getProductTest_ResourceNotFoundException() {
		
		Mockito.when(productRepository.getOne(entity.getId())).thenReturn(null);
		
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			productService.getProduct(entity.getId());
		});
		
	}
}

