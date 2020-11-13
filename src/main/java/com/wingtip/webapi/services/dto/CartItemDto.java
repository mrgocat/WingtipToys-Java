package com.wingtip.webapi.services.dto;

import java.util.Date;


public class CartItemDto {
	private String id;
	private String cartId;
	private int quantity;
	private Date created;
	
	private ProductDto product;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public ProductDto getProduct() {
		return product;
	}

	public void setProduct(ProductDto product) {
		this.product = product;
	}
	
}
