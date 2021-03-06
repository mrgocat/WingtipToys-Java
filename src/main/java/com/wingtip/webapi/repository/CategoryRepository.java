package com.wingtip.webapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wingtip.webapi.repository.entities.CategoryEntity;

@Repository
public interface CategoryRepository  extends JpaRepository<CategoryEntity, Integer>{

}
