package com.wingtip.webapi.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.wingtip.webapi.repository.CategoryRepository;
import com.wingtip.webapi.repository.entities.CategoryEntity;
import com.wingtip.webapi.services.CategoryService;
import com.wingtip.webapi.services.dto.CategoryDto;

@Service
public class CategoryServiceImpl implements CategoryService {
	private CategoryRepository categoryRepository;

	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	@Override
	public List<CategoryDto> getCategories() {
		List<CategoryEntity> list = categoryRepository.findAll();
		return list.stream().map(c -> {
			CategoryDto dto = new CategoryDto();
			BeanUtils.copyProperties(c, dto);
			return dto;
		}).collect(Collectors.toList());
	}
}
