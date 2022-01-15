package com.halloween.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.halloween.dao.IOrderDAO;
import com.halloween.model.OrderModel;
import com.halloween.model.RecentOrder;

public class OrderDAO extends AbstractDAO<OrderModel> implements IOrderDAO{
	@Override
	public Boolean updateOrderDate(Integer orderID) {
		String sql = "UPDATE Orders SET OrderDate = current_timestamp() where id = ?";
		return updateOrDelete(sql, orderID);
	}

	@Override
	public Boolean updateOrder(Integer orderID, Date shipDate) {
		String sql = "UPDATE Orders SET shippedDate = ?, status = 1 WHERE id = ?";
		return updateOrDelete(sql, shipDate, orderID);
	}
	
	@Override
	public Boolean updateOrder() {
		String sql = "UPDATE Orders SET status = 2 WHERE day(ShippedDate) <= day(current_timestamp()) AND status NOT IN(2, 3)";
		return updateOrDelete(sql);
	}

	@Override
	public Integer getAmountOrderDelivered(Integer customerID) {
		String sql = "SELECT COUNT(*) FROM Orders WHERE  day(current_timestamp()) - day(Orders.orderDate) >= 1 and day(current_timestamp()) < day(orders.ShippedDate) AND status = 1 AND customerID = ?";
		return getSingleObject(sql, 1, Integer.class, customerID);
	}
	
	@Override
	public List<Integer> getAllOrderIDOfCustomer(Integer customerID) {
		String sql = "SELECT id FROM Orders WHERE customerID = ? AND status > 0";
		return getListObject(sql, Integer.class, customerID);
	}

    @Override
    public Integer getOrderOfDay() {
		String sql = "SELECT COUNT(*) AS Total FROM Orders O INNER JOIN orderItem OI ON O.id = OI.orderID WHERE O.status = 1 AND date_format(O.OrderDate, \"%M %d %Y\") = date_format(current_timestamp(), \"%M %d %Y\")";
		return getSingleObject(sql, 1, Integer.class);
    }

    @Override
	public Boolean updateOrderSuccess() {
		String sql = "Update Orders SET status = 2 WHERE ShippedDate < OrderDate";
		return updateOrDelete(sql);
	}
	
	@Override
	public Integer getOrderID(Integer customerID) {
		String sql = "SELECT id FROM Orders WHERE customerID = ? AND shippedDate IS NULL";
		return getSingleObject(sql, 1, Integer.class, customerID);
	}
	
	@Override
	public Boolean deleteOrders(Integer orderID) {
		String sql = "DELETE FROM Orders WHERE id = ?";
		return updateOrDelete(sql, orderID);
	}
	
	@Override
	public Integer getCountOrderItem(Integer orderID) {
		String sql = "SELECT COUNT(*) FROM OrderItem WHERE orderID = ?";
		return getSingleObject(sql, 1, Integer.class, orderID);
	}
	
	@Override
	public Double getRevenueOfDay() {
		String sql = "SELECT SUM(OI.amount * OI.price) AS Total FROM Orders O INNER JOIN Orderitem OI ON O.id = OI.orderID WHERE O.status = 1 AND date_format(O.OrderDate, \"%M %d %Y\") = date_format(current_timestamp(), \"%M %d %Y\")";
		return getSingleObject(sql, 1, Double.class);
	}

    @Override
    public List<RecentOrder> getRecentOrder() {
		Connection connection = getCon();
		StringBuilder sql = new StringBuilder("select cdt.firstName, cdt.lastName, cdt.avatar, SUM(price) as totalPrice, o.status from orders o inner join customerDetail cdt on o.customerID = cdt.cusID \r\n" + "	inner join orderItem od on od.orderID = o.id where o.status > 0 group by o.customerID, o.id");
		List<RecentOrder> recentOrders = null;
		try {
			PreparedStatement stm = connection.prepareStatement(sql.toString());
			ResultSet resultSet = stm.executeQuery();
			recentOrders = new ArrayList<>();
			while (resultSet.next())
			{
				String colorSta = "";
				String status = "";
				if(resultSet.getInt(5) == 1)
				{
					colorSta = "#FFFC3F";
					status = RecentOrder.PENDING;
				}else if(resultSet.getInt(5) == 2)
				{
					colorSta = "#82FA42";
					status = RecentOrder.DELIVERED;
				}
				else {
					colorSta = "#FA1707";
					status = RecentOrder.RETURNS;
				}
				recentOrders.add(new RecentOrder(resultSet.getString(1) + " " +  resultSet.getString(2), resultSet.getDouble(4), status, colorSta, resultSet.getString(3)));
			}
			closeCon(connection, stm, resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return recentOrders;
    }
}
