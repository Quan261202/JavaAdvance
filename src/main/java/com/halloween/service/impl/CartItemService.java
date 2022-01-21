package com.halloween.service.impl;

import com.halloween.dao.ICartItemDAO;
import com.halloween.dao.impl.CartItemDAO;
import com.halloween.model.CartItem;
import com.halloween.model.Products;
import com.halloween.service.ICartItemService;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class CartItemService implements ICartItemService{
	
	public static ICartItemDAO newDAO = new CartItemDAO();
	
	@Override
	public Map<String, Object> getAllOrdersOfCustomer(List<Integer> integers, Integer customerID, Integer status) {
		Map<String, Object> hashMap = new HashMap<>();
		for (Integer integer : integers) {
			List<CartItem> cartItems = newDAO.getAllCartItemOfOrder(integer, customerID, status);
			if (cartItems.size() > 0) {
				hashMap.put(integer.toString(), cartItems);
			}
		}
		return hashMap;
	}

	@Override
	public Integer getCountCartItemCurrentOfCustomer(Integer orderID) {
		return newDAO.getCountCartItemCurrentOfCustomer(orderID);
	}

	@Override
	public List<CartItem> getAllOrderItemByID(String[] productID, Integer orderID) {
		List<CartItem> cartItems = new ArrayList<>();
		for (String item : productID)
			cartItems.add(newDAO.getOrderItemByID(Integer.parseInt(item), orderID));
		return cartItems;
	}

	@Override
	public List<CartItem> loadCart(Integer orderID) {
		return newDAO.loadCart(orderID);
	}

	@Override
	public Boolean updateCartItem(CartItem cartItem) {
		return newDAO.updateCartItem(cartItem);
	}

	@Override
	public List<CartItem> saveCart(Integer customerID, Integer orderID, Integer quantity, Integer productID, List<Products> lists) {
		List<CartItem> cartItems;
		if (orderID == null) {
			cartItems = new ArrayList<>();
			orderID = newDAO.saveCart(customerID);
		} else {
			cartItems = newDAO.loadCart(orderID);
		}
		for (Products products : lists) {
			if (products.getProductID() == productID) {
				if (products.getStatus() > 0 && products.getQuantity() > 0) {
					int index = findByID(productID, cartItems);
					if (index != -1) {
						cartItems.get(index).setQuantity(cartItems.get(index).getQuantity() + quantity);
						newDAO.updateCartItem(cartItems.get(index));
					} else {
						cartItems.add(new CartItem(productID, products.getProductName(), products.getPrice(), quantity,
								products.getUrlImage()));
						newDAO.saveCartItem(orderID, new CartItem(productID, products.getProductName(), products.getPrice(), 1,
								products.getUrlImage()));
					}
				}
				break;
			}
		}
		return cartItems;
	}

	private int findByID(Integer productID, List<CartItem> cartItems) {
		for(int i = 0; i < cartItems.size(); ++i)
			if(cartItems.get(i).getProductID() == productID)
				return i;
		return -1;
	}

	@Override
	public Boolean removeCartItem(Integer productID, Integer orderID) {
		return newDAO.removeCartItem(productID, orderID);
	}

	@Override
	public Boolean updateCartItem(Integer amount, Integer productID, Integer orderID) {
		return newDAO.updateCartItem(amount, productID, orderID);
	}

	@Override
	public List<CartItem> getCartItemNotIn(HttpServletRequest request, List<String> integers, Integer orderID) {
		if(request.getParameter("id") != null)
		{
			if(request.getParameter("id").indexOf(',') > 0)
			{
				String[] temp = request.getParameter("id").split(",");
				integers.addAll(Arrays.asList(temp));
			}
			else integers.add(request.getParameter("id"));
			StringBuilder pram = new StringBuilder("(");
			for(int i = 0; i < integers.size(); ++i)
			{
				if(i == integers.size() - 1) pram.append("?)");
				else pram.append("?,");
			}
			return newDAO.getCartItemNotIn(orderID, pram.toString(), integers);
		}
		return null;
	}
}
