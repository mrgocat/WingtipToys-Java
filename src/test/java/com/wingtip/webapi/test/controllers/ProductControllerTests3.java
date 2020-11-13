package com.wingtip.webapi.test.controllers;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;

import com.wingtip.webapi.controllers.ProductController;
import com.wingtip.webapi.services.ProductService;
import com.wingtip.webapi.services.dto.ProductDto;
import com.wingtip.webapi.services.impl.ProductServiceImpl;

//@WebMvcTest(ProductController.class)
//@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class ProductControllerTests3 {
//	@Autowired
//	private MockMvc mvc;
	@InjectMocks
	ProductController productController;
	
	@Mock
	ProductServiceImpl service;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getProductTest() throws Exception{
		ProductDto dto = new ProductDto();
		dto.setId(1);
		dto.setProductName("Luxury Car");
		
		Mockito.when(service.getProduct(dto.getId())).thenReturn(dto);
		
//		mvc.perform(MockMvcRequestBuilders.get("/api/v1/product/" + dto.getId())
//				.accept(MediaType.APPLICATION_JSON))
//				.andExpect(MockMvcResultMatchers.status().isOk())
//				.andExpect(MockMvcResultMatchers.jsonPath("$.product").exists());
//	
	}
}
