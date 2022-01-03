package com.halloween.model;

public class CustomerDetail {
	private int cusID;
	private String name;
	private String address;
	private String phone;
	
	public CustomerDetail(int cusID, String name,  String phone, String address) {
		super();
		this.cusID = cusID;
		this.name = name;
		this.address = address;
		this.phone = phone;
	}

	public int getCusID() {
		return cusID;
	}

	public void setCusID(int cusID) {
		this.cusID = cusID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
}
