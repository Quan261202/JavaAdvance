package com.halloween.model;

public class Products {
	private int productID;
	private String productName;
	private double price;
	private String urlImage;
	private int status;
	private int quantity;
	private int categoryID;
	
	public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	public Products() {
		
	}
	
	public Products(int productID, String productName, double price, String urlImage, int status, int quantity, int categoryID) {
		super();
		this.productID = productID;
		this.productName = productName;
		this.price = price;
		this.urlImage = urlImage;
		this.status = status;
		this.quantity = quantity;
		this.categoryID = categoryID;
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

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
