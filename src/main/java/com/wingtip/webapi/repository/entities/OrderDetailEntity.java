package com.wingtip.webapi.repository.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="orderdetails")
public class OrderDetailEntity  implements Serializable{
	private static final long serialVersionUID = 5505416223833425378L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
//	@ManyToOne // (fetch = FetchType.LAZY)
//	@JoinColumn(name="order_id")
//	private OrderEntity order;
	 
	@ManyToOne // (fetch = FetchType.LAZY)
	@JoinColumn(name="product_id")
	private ProductEntity product;
	private int quantity;
	private double unitPrice;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	/*
	 * public OrderEntity getOrder() { return order; } public void
	 * setOrder(OrderEntity order) { this.order = order; }
	 */
	public ProductEntity getProduct() {
		return product;
	}
	public void setProduct(ProductEntity product) {
		this.product = product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
}
