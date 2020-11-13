package com.wingtip.webapi.repository.entities;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="cartitems")
public class CartItemEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4748161205088078194L;
	@Id
	private String id;
	private String cartId;
	private int quantity;
	private Date created;
	
	@ManyToOne // (fetch = FetchType.LAZY)
	@JoinColumn(name="product_id")
	private ProductEntity product;

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

	public ProductEntity getProduct() {
		return product;
	}

	public void setProduct(ProductEntity product) {
		this.product = product;
	}
	
	
}
