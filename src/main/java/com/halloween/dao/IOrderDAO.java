package com.halloween.dao;

import com.halloween.model.OrderModel;
import com.halloween.model.RecentOrder;

import java.util.Date;
import java.util.List;

public interface IOrderDAO extends GenericDAO<OrderModel>{
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
	Double getRevenueOfDay();
	List<RecentOrder> getRecentOrder();
}
