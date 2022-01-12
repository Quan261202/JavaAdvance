package com.halloween.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.halloween.model.CartItem;
import com.halloween.model.Products;

public interface ICartItemService {
	public HashMap<Integer, List<CartItem>> getAllCartItemOfOrder(List<Integer> integers, Integer customerID, Integer status);
	public List<CartItem> getCartItemNotIn(HttpServletRequest request, List<String> integers, Integer orderID);
	public Integer getCountCartItemCurrentOfCustomer(Integer orderID);
	public List<CartItem> getAllOrderItemByID(String[] productID, Integer orderID);
	public List<CartItem> loadCart(Integer orderID);
	public Boolean updateCartItem(CartItem cartItem);
	public Boolean updateCartItem(Integer amount, Integer productID, Integer orderID);
	public List<CartItem> saveCart(Integer customerID, Integer orderID, Integer quantity, Integer productID, List<Products> lists);
	public Boolean removeCartItem(Integer productID, Integer orderID);
}
