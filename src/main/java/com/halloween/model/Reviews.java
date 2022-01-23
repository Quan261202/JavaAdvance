package com.halloween.model;

import java.io.Serial;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reviews implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	private int id;
	private int customerID;
	private int productID;
	private String urlImage;
	private Date reviewsDate;
	private String content;
	private int vote;
	private String avatar;
	private String customerName;
	private int totalLike;
	private char operator;

	public Reviews() {
	}

	public Reviews(int customerID, int productID, String urlImage, Date reviewsDate, String content, int vote) {
		super();
		this.customerID = customerID;
		this.productID = productID;
		this.urlImage = urlImage;
		this.reviewsDate = reviewsDate;
		this.content = content;
		this.vote = vote;
	}

	public Reviews(int id, int customerID, int productID, String urlImage, Date reviewsDate, String content, int vote, char operator) {
		super();
		this.id = id;
		this.customerID = customerID;
		this.productID = productID;
		this.urlImage = urlImage;
		this.reviewsDate = reviewsDate;
		this.content = content;
		this.vote = vote;
		this.operator = operator;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTotalLike() {
		return totalLike;
	}

	public void setTotalLike(int totalLike) {
		this.totalLike = totalLike;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	public String getReviewsDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(reviewsDate);
	}

	public void setReviewsDate(Date reviewsDate) {
		this.reviewsDate = reviewsDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getVote() {
		return vote;
	}

	public void setVote(int vote) {
		this.vote = vote;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public char getOperator() {
		return operator;
	}

	public void setOperator(char operator) {
		this.operator = operator;
	}
}
