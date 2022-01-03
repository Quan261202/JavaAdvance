package com.halloween.dao;

import java.util.List;

import com.halloween.model.CartItem;

public interface ICartItemDAO extends GenericDAO<CartItem>{
	public List<CartItem> getAllCartItemOfOrder(Integer orderID, Integer customerID, Integer status);
	public List<CartItem> getCartItemNotIn(Integer orderID, String param, List<String> integers);
	public Integer getCountCartItemCurrentOfCustomer(Integer orderID);
	public CartItem getOrderItemByID(Integer productID, Integer orderID);
	public List<CartItem> loadCart(Integer orderID);
	public Boolean updateCartItem(CartItem cartItem);
	public Boolean updateCartItem(Integer amount, Integer productID, Integer orderID);
	public  Integer saveCartItem(Integer orderID, CartItem cartItem);
	public  Integer saveCart(Integer customerID);
	public Boolean removeCartItem(Integer productID, Integer orderID);
}
