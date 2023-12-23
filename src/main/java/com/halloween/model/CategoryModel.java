package com.halloween.model;

import java.util.Date;

public class CategoryModel {
	private Integer categoryID;
	private String categoryName;
	private String description;

	private Date createdDate;

	private Date deletedDate;

	private boolean isDeleted;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public CategoryModel(Integer categoryID, String categoryName) {
		super();
		this.categoryID = categoryID;
		this.categoryName = categoryName;
	}

	public CategoryModel(String categoryName, String description) {
		super();
		this.categoryName = categoryName;
		this.description = description;
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
