package com.halloween.service;

import com.halloween.model.CartItem;
import com.halloween.model.Products;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

public interface ICartItemService {
    HashMap<Integer, List<CartItem>> getAllCartItemOfOrder(List<Integer> integers, Integer customerID, Integer status);

    List<CartItem> getCartItemNotIn(HttpServletRequest request, List<String> integers, Integer orderID);

    Integer getCountCartItemCurrentOfCustomer(Integer orderID);

    List<CartItem> getAllOrderItemByID(String[] productID, Integer orderID);

    List<CartItem> loadCart(Integer orderID);

    Boolean updateCartItem(CartItem cartItem);

    Boolean updateCartItem(Integer amount, Integer productID, Integer orderID);

    List<CartItem> saveCart(Integer customerID, Integer orderID, Integer quantity, Integer productID, List<Products> lists);

    Boolean removeCartItem(Integer productID, Integer orderID);
}
