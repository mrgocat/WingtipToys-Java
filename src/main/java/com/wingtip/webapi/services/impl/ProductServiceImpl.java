package com.wingtip.webapi.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.wingtip.webapi.exception.BadRequestException;
import com.wingtip.webapi.exception.ResourceNotFoundException;
import com.wingtip.webapi.repository.ProductRepository;
import com.wingtip.webapi.repository.entities.ProductEntity;
import com.wingtip.webapi.services.ProductService;
import com.wingtip.webapi.services.dto.ProductDto;

@Service
public class ProductServiceImpl implements ProductService{

	private ProductRepository productRepository;

	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	@Override
	public ProductDto getProduct(int id) {
		Optional<ProductEntity> optional = productRepository.findById(id);
		if(!optional.isPresent()) throw new ResourceNotFoundException("ID-" + id);
		ProductDto dto = new ProductDto();
		BeanUtils.copyProperties(optional.get(), dto);
		return dto;
	}
	@Override
	public List<ProductDto> getProducts(String name, String value){
		List<ProductEntity> entityList = null;
		if("category".equals(name)) {
			int categoryId = 0; 
			try {
				categoryId = Integer.parseInt(value);
			}catch(NumberFormatException ex) {
				throw new BadRequestException("Wrong Request.");
			}
			entityList =  productRepository.findAllByCategoryId(categoryId);
		}else if("name".equals(name)){
			entityList =  productRepository.findAllByProductNameContains(value);
		}else {
			throw new BadRequestException("Wrong Request2.");
		}
		return entityList.stream().map(e -> {
			ProductDto dto = new ProductDto();
			BeanUtils.copyProperties(e, dto);
			return dto;
		}).collect(Collectors.toList());
	}
	@Override
	public List<ProductDto> getProducts() {
		List<ProductEntity> entityList = productRepository.findAll();
		
		List<ProductDto> dtoList = new ArrayList<>(); 
		entityList.stream()
			.forEach(e -> { 
				ProductDto dto = new ProductDto(); BeanUtils.copyProperties(e, dto);
				dtoList.add(dto); 
		 });
		
		/*
		 * List<ProductDto> dtoList2 = entityList.stream().map(e -> { ProductDto dto =
		 * new ProductDto(); BeanUtils.copyProperties(e, dto); return dto;
		 * }).collect(Collectors.toList());
		 */

		return dtoList;
	}

}
