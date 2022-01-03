package com.halloween.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.halloween.dao.IOrderDAO;
import com.halloween.dao.impl.OrderDAO;
import com.halloween.model.RecentOrder;
import com.halloween.service.IOrderService;

public class OrderService implements IOrderService{

	private static final IOrderDAO newDAO = new OrderDAO();
	
	@Override
	public Boolean deleteOrders(Integer orderID) {
		return newDAO.deleteOrders(orderID);
	}

	@Override
	public Boolean updateOrderDate(Integer orderID) {
		return newDAO.updateOrderDate(orderID);
	}

	@Override
	public Boolean updateOrder(Integer orderID, Date shipDate) {
		return newDAO.updateOrder(orderID, shipDate);
	}

	@Override
	public Boolean updateOrder() {
		return newDAO.updateOrder();
	}

	@Override
	public Boolean updateOrderSuccess() {
		return newDAO.updateOrderSuccess();
	}

	@Override
	public Integer getAmountOrderDelivered(Integer customerID) {
		return newDAO.getAmountOrderDelivered(customerID);
	}

	@Override
	public Integer getOrderID(Integer customerID) {
		return newDAO.getOrderID(customerID);
	}

	@Override
	public Integer getCountOrderItem(Integer orderID) {
		return newDAO.getCountOrderItem(orderID);
	}

	@Override
	public List<Integer> getAllOrderIDOfCustomer(Integer customerID) {
		return newDAO.getAllOrderIDOfCustomer(customerID);
	}

	@Override
	public Integer getOrderOfDay() {
		return newDAO.getOrderOfDay();
	}

    @Override
    public List<RecentOrder> getRecentOrder() {
		return newDAO.getRecentOrder();
    }

    @Override
	public Double getRevenueOfDay() {
		return newDAO.getRevenueOfDay();
	}
	
	@Override
	public Date addDays(Date date, Integer days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }
}
