package com.wingtip.webapi.services.dto;

import com.wingtip.webapi.repository.entities.CategoryEntity;

public class ProductDto {
	private int id;
	private String productName;
	private String description;
	private String imagePath;
	private double unitPrice;
	private CategoryEntity category;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public CategoryEntity getCategory() {
		return category;
	}
	public void setCategory(CategoryEntity category) {
		this.category = category;
	}
	
}
