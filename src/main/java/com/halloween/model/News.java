package com.halloween.model;

import java.io.Serializable;
import java.util.Date;

public class News implements Serializable{

	private static final long serialVersionUID = 1L;
	private int adminID;
	private int categoryID;
	private Date createDate;
	private String title;
	private String shortDescription;
	private String content;
	
	public News() {
	}
	
	public News(int adminID, int categoryID, Date createDate, String title, String shortDescription, String content) {
		super();
		this.adminID = adminID;
		this.categoryID = categoryID;
		this.createDate = createDate;
		this.title = title;
		this.shortDescription = shortDescription;
		this.content = content;
	}

	public int getAdminID() {
		return adminID;
	}

	public void setAdminID(int adminID) {
		this.adminID = adminID;
	}

	public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
