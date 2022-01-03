package com.halloween.model;

import java.util.Date;

public class OrderModel {
	private Integer orderID;
	private Integer customerID;
	private Integer status;
	private Date orderDate;
	private Date shipDate;
	
	public OrderModel(Integer orderID, Integer customerID, Integer status, Date orderDate) {
		super();
		this.orderID = orderID;
		this.customerID = customerID;
		this.status = status;
		this.orderDate = orderDate;
	}
	
	public OrderModel(Integer orderID, Integer customerID, Integer status, Date orderDate, Date shipDate) {
		super();
		this.orderID = orderID;
		this.customerID = customerID;
		this.status = status;
		this.orderDate = orderDate;
		this.shipDate = shipDate;
	}

	public Integer getOrderID() {
		return orderID;
	}
	
	public void setOrderID(Integer orderID) {
		this.orderID = orderID;
	}
	
	public Integer getCustomerID() {
		return customerID;
	}
	
	public void setCustomerID(Integer customerID) {
		this.customerID = customerID;
	}
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getShipDate() {
		return shipDate;
	}

	public void setShipDate(Date shipDate) {
		this.shipDate = shipDate;
	}
}
