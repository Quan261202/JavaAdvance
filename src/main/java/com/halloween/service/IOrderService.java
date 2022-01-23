package com.halloween.service;

import com.halloween.model.RecentOrder;

import java.util.Date;
import java.util.List;

public interface IOrderService {
	Boolean deleteOrders(Integer orderID);
	Boolean updateOrderDate(Integer orderID);
	Boolean updateOrder(Integer orderID, Date shipDate);
	Boolean updateOrderSuccess();
	Integer getAmountOrderDelivered(Integer customerID);
	Integer getAmountOrdersByStatus(Integer customerID, Integer status);
	Integer getOrderID(Integer customerID);
	Integer getCountOrderItem(Integer orderID);
	List<Integer> getAllOrderIDOfCustomer(Integer customerID);
	Integer getOrderOfDay();
	List<RecentOrder> getRecentOrder();
	Double getRevenueOfDay();

	Date addDays(Date date, Integer days);
}
