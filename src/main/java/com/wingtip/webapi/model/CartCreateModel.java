package com.wingtip.webapi.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class CartCreateModel {
	private String id;
	private String cartId;
	
	@NotBlank(message = "Quantity is required")
	@Min(value = 1, message = "Quantity min value is 1.")
	private int quantity;
	@NotBlank(message = "ProductId is required")
	@Min(value = 1, message = "Invalid productId.")
	private int productId;
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
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
}
