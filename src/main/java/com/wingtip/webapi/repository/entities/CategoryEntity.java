package com.wingtip.webapi.repository.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="category")
public class CategoryEntity implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 6559798948506155813L;

	@Id
	private int id;
	
	private String categoryName;
	
	private String description;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	



}
