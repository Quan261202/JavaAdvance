package com.halloween.model;


public class RecentOrder {
	public static final String PENDING = "Pending";
	public static final String RETURNS = "Return";
	public static final String DELIVERED = "Delivered";
	private String name;
	private double price;
	private String status;
	private String colorStatus;
	public RecentOrder(String name, double price, String status, String colorStatus) {
		super();
		this.name = name;
		this.price = price;
		this.status = status;
		this.colorStatus = colorStatus;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getColorStatus() {
		return colorStatus;
	}
	public void setColorStatus(String colorStatus) {
		this.colorStatus = colorStatus;
	}
}
