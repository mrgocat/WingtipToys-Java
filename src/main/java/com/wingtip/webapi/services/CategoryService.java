package com.wingtip.webapi.services;

import java.util.List;

import com.wingtip.webapi.services.dto.CategoryDto;

public interface CategoryService {
	List<CategoryDto> getCategories();
}
