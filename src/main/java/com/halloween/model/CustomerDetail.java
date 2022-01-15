package com.halloween.model;

public class CustomerDetail {
	private Integer customerID;
	private String firstName;
	private String lastName;
	private String email;
	private String address;
	private String phone;
	private String avatar;
	
	public CustomerDetail() {
		super();
	}

	public CustomerDetail(int customerID, String firstName, String lastName, String phone, String address, String avatar,
			String email) {
		super();
		this.customerID = customerID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phone = phone;
		this.avatar = avatar;
		this.email = email;
	}

	public Integer getCustomerID() {
		return customerID;
	}

	public void setCustomerID(Integer customerID) {
		this.customerID = customerID;
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	public String getFullName() {
		return this.firstName + " " + this.lastName;
	}
}
