package com.halloween.model;

import java.util.Date;

public class CategoryModel {
	private Integer categoryID;
	private String categoryName;
	private String descriptions;

	private Date createdDate;

	private Date updatedDate;

	private Date deletedDate;

	private boolean isDeleted;

	public String getDescriptions() {
		return descriptions;
	}

	public void setDescription(String descriptions) {
		this.descriptions = descriptions;
	}

	public CategoryModel(Integer categoryID, String categoryName, String descriptions) {
		super();
		this.categoryID = categoryID;
		this.categoryName = categoryName;
		this.descriptions = descriptions;
	}

	public CategoryModel(String categoryName, String descriptions) {
		super();
		this.categoryName = categoryName;
		this.descriptions = descriptions;
	}
	
	public Integer getCategoryID() {
		return categoryID;
	}
	
	public void setCategoryID(Integer categoryID) {
		this.categoryID = categoryID;
	}
	
	public String getCategoryName() {
		return categoryName;
	}
	
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
}
