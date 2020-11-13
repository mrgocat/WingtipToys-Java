package com.wingtip.webapi.services;

import java.util.List;

import com.wingtip.webapi.services.dto.ProductDto;

public interface ProductService {
	ProductDto getProduct(int id);
	List<ProductDto> getProducts();
	List<ProductDto> getProducts(String name, String value);
	
	
}
