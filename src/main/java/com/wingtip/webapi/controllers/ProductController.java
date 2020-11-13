package com.wingtip.webapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wingtip.webapi.services.CategoryService;
import com.wingtip.webapi.services.ProductService;
import com.wingtip.webapi.services.dto.CategoryDto;
import com.wingtip.webapi.services.dto.ProductDto;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	@GetMapping("{id}")
	public ProductDto getProduct(@PathVariable int id) {
		return productService.getProduct(id);
	}
//	@CrossOrigin(origins="*")
	@GetMapping
	public List<ProductDto> getProducts(@RequestParam(value="name", required = false) String name, 
			@RequestParam(value="value", required = false) String value) {
		if(name != null && value != null) {
			return productService.getProducts(name, value);
		}else {
			return productService.getProducts();
		}
	}
	@GetMapping("category")
	public List<CategoryDto> GetProductCategory(){
		return categoryService.getCategories();
	}
}
