package com.wingtip.webapi.test.controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.wingtip.webapi.controllers.ProductController;
import com.wingtip.webapi.exception.ResourceNotFoundException;
import com.wingtip.webapi.repository.ProductRepository;
import com.wingtip.webapi.repository.entities.CategoryEntity;
import com.wingtip.webapi.repository.entities.ProductEntity;
import com.wingtip.webapi.services.CategoryService;
import com.wingtip.webapi.services.ProductService;
import com.wingtip.webapi.services.dto.ProductDto;
import com.wingtip.webapi.services.impl.ProductServiceImpl;

import static org.assertj.core.api.Assertions.*;

// @SpringBootTest
@WebMvcTest(ProductController.class)
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class ProductControllerTests {
	@Autowired
	private MockMvc mvc;

//	@InjectMocks
//	ProductController productController;
	
	@MockBean
	ProductService service;
	@MockBean
	CategoryService categoryService;
	
	ProductDto dto;
	
	@BeforeEach
	void setUp() throws Exception {
	//	MockitoAnnotations.initMocks(this);
		
		dto = new ProductDto();
		dto.setId(1);
		dto.setProductName("Luxury Car");
		dto.setImagePath("cars.png");
		dto.setUnitPrice(23.11);
		dto.setDescription("very good car toy.");
		CategoryEntity category = new CategoryEntity();
		category.setCategoryName("Cars");
		category.setId(101);
		dto.setCategory(category);
	}
	
	@Test
	public void getProductTest() throws Exception{
		
		Mockito.when(service.getProduct(dto.getId())).thenReturn(dto);
		
		mvc.perform(MockMvcRequestBuilders.get("/api/v1/product/" + dto.getId())
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(dto.getId()))
		.andExpect(MockMvcResultMatchers.jsonPath("$.productName").value(dto.getProductName()))
		.andExpect(MockMvcResultMatchers.jsonPath("$.category").exists());
	}
	@Test
	public void getProductTest_NotFound() throws Exception{
		
		Mockito.when(service.getProduct(1)).thenThrow(new ResourceNotFoundException(""));
		
		mvc.perform(MockMvcRequestBuilders.get("/api/v1/product/" + 1)
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

}

