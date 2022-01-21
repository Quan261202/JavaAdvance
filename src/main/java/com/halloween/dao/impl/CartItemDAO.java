package com.halloween.dao.impl;

import com.halloween.dao.ICartItemDAO;
import com.halloween.mapper.CartItemMapper;
import com.halloween.model.CartItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartItemDAO extends AbstractDAO<CartItem> implements ICartItemDAO{

	@Override
	public List<CartItem> getAllCartItemOfOrder(Integer orderID, Integer customerID, Integer status) {
		String sql = "SELECT products.productID, products.productName, orderItem.price, orderItem.amount, products.urlImage FROM OrderItem INNER JOIN orders ON orderItem.orderID = orders.id INNER JOIN products ON products.productID = orderItem.productID where customerID = ? AND orders.status = ? AND orders.id = ?";
		return query(sql, new CartItemMapper(), customerID, status, orderID);
	}

	@Override
	public List<CartItem> loadCart(Integer orderID) {
		String sql = "select p.productID, p.productName, p.price, oi.amount, p.urlImage from orders o inner join customer c on o.customerID = c.customerID\r\n"
				+ "	inner join orderItem oi on o.id = oi.orderID inner join products p on oi.productID = p.productID where o.id = ? AND o.shippedDate IS NULL";
		return query(sql, new CartItemMapper(), orderID);
	}

	@Override
	public CartItem getOrderItemByID(Integer productID, Integer orderID) {
		String sql = "SELECT o.productID, p.productName, o.price, o.amount, p.urlImage, Orders.orderDate FROM OrderItem o INNER JOIN Products p ON o.productID = p.productID INNER JOIN Orders on o.orderID = Orders.id WHERE p.productID = ? AND o.orderID = ?";
		return query(sql, new CartItemMapper(), productID, orderID).get(0);
	}

	@Override
	public Boolean updateCartItem(CartItem cartItem) {
		String sql = "UPDATE OrderItem SET amount = ? WHERE productID = ?";
		return updateOrDelete(sql, cartItem.getQuantity(), cartItem.getProductID());
	}

	@Override
	public Integer saveCartItem(Integer orderID, CartItem cartItem) {
		String sql = "INSERT INTO orderItem(productID, orderID, amount, price) values(?, ?, ?, ?)";
		return insert(sql, cartItem.getProductID(), orderID, cartItem.getQuantity(), cartItem.getPrice());
	}

	@Override
	public Integer saveCart(Integer customerID) {
		String sql = "INSERT INTO Orders(customerID) VALUES(?)";
		return insert(sql, customerID);
	}

	@Override
	public Integer getCountCartItemCurrentOfCustomer(Integer orderID) {
		String sql = "SELECT COUNT(*) AS Total FROM Orders O INNER JOIN orderItem OI ON O.id = OI.orderID WHERE O.id = ? AND O.status = 0 group by O.id";
		return getSingleObject(sql, 1, Integer.class, orderID);
	}

	@Override
	public Boolean removeCartItem(Integer productID, Integer orderID) {
		String sql = "DELETE FROM OrderItem WHERE productID = ? AND orderID = ?";
		return updateOrDelete(sql, productID, orderID);
	}

	@Override
	public Boolean updateCartItem(Integer amount, Integer productID, Integer orderID) {
		String sql = "UPDATE OrderItem SET amount = ? WHERE productID = ? AND orderID = ?";
		return updateOrDelete(sql, amount, productID, orderID);
	}

	@Override
	public List<CartItem> getCartItemNotIn(Integer orderID, String param, List<String> integers) {
		List<CartItem> cartItems = new ArrayList<>();
		Connection con = getCon();
		String sql = "select p.productID, p.productName, p.price, oi.amount, p.urlImage from orders o inner join customer c on o.customerID = c.customerID inner join orderitem oi on o.id = oi.orderID inner join products p on oi.productID = p.productID where o.id = ? and  p.productID NOT IN" + param;
		PreparedStatement stm;
		try {
			stm = con.prepareStatement(sql);
			stm.setInt(1, orderID);
			int index = 2;
			for(String id : integers)
				stm.setInt(index++, Integer.parseInt(id.substring(0, id.indexOf(':'))));
			ResultSet resultSet = stm.executeQuery();
			while(resultSet.next()) cartItems.add(new CartItemMapper().mapRow(resultSet));
			closeCon(con, stm, resultSet);
			return cartItems;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
