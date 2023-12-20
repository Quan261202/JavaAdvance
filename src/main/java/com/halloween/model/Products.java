package com.halloween.model;

import java.util.Date;

public class Products {
	private int productID;
	private String productName;
	private double price;
	private String urlImage;
	private Date createdDate;

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Date getDeletedDate() {
		return deletedDate;
	}

	public void setDeletedDate(Date deletedDate) {
		this.deletedDate = deletedDate;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean deleted) {
		isDeleted = deleted;
	}

	private Date updatedDate;

	public Products(int productID, String productName, double price, String urlImage, Date createdDate, Date updatedDate, Date deletedDate, boolean isDeleted, int status, int quantity, int categoryID, String shortDescription) {
		this.productID = productID;
		this.productName = productName;
		this.price = price;
		this.urlImage = urlImage;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.deletedDate = deletedDate;
		this.isDeleted = isDeleted;
		this.status = status;
		this.quantity = quantity;
		this.categoryID = categoryID;
		this.shortDescription = shortDescription;
	}

	private Date deletedDate;
	private boolean isDeleted;
	private int status;
	private int quantity;
	private int categoryID;
	private String shortDescription;

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
	
	public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
}
