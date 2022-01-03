package com.halloween.model;

public class CartItem {
	public static String date = null;
	private int productID;
	private String productName;
	private double price;
	private int quantity;
	private String urlImage;
	public CartItem(int productID, String productName, double price, int quantity, String urlImage) {
		super();
		this.productID = productID;
		this.productName = productName;
		this.price = price;
		this.quantity = quantity;
		this.urlImage = urlImage;
	}
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getUrlImage() {
		return urlImage;
	}
	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}
}
