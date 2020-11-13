package com.wingtip.webapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wingtip.webapi.repository.entities.CategoryEntity;

public interface CategoryRepository  extends JpaRepository<CategoryEntity, Integer>{

}
