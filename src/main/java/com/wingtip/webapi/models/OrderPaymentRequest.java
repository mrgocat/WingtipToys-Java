package com.wingtip.webapi.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class OrderPaymentRequest {
	private String paymentTransId;
	@NotNull
	@Min(value = 1, message = "Invalid amount.")
	private double amount;
	
	public String getPaymentTransId() {
		return paymentTransId;
	}
	public void setPaymentTransId(String paymentTransId) {
		this.paymentTransId = paymentTransId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
}
