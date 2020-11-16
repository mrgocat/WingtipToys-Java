package com.wingtip.webapi.models;

import javax.validation.constraints.NotBlank;

public class OrderUpdateRequest {
	@NotBlank(message="firstName is required.")
	private String firstName;
	@NotBlank(message="lastName is required.")
	private String lastName;
	@NotBlank(message="address is required.")
	private String address;
	@NotBlank(message="city is required.")
	private String city;

	private String state;
	@NotBlank(message="postalCode is required.")
	private String postalCode;
	@NotBlank(message="country is required.")
	private String country;
	private String phone;
	private String email;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
