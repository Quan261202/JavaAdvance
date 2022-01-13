package com.halloween.model;

public class CustomerDetail {
	private int cusID;
	private String firstName;
	private String lastName;
	private String email;
	private String address;
	private String phone;
	private String avatar;

	public CustomerDetail(int cusID, String firstName, String lastName, String phone, String address) {
		super();
		this.cusID = cusID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phone = phone;
	}

	public CustomerDetail(int cusID, String firstName, String lastName, String phone, String address, String avatar,
			String email) {
		super();
		this.cusID = cusID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phone = phone;
		this.avatar = avatar;
		this.email = email;
	}

	public int getCusID() {
		return cusID;
	}

	public void setCusID(int cusID) {
		this.cusID = cusID;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
