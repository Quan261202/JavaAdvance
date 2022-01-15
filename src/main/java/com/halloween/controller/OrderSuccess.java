package com.halloween.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.halloween.model.CartItem;
import com.halloween.model.Customer;
import com.halloween.service.ICartItemService;
import com.halloween.service.ICustomerService;
import com.halloween.service.IOrderService;
import com.halloween.service.IProductService;
import com.halloween.service.impl.CartItemService;
import com.halloween.service.impl.CustomerService;
import com.halloween.service.impl.OrderService;
import com.halloween.service.impl.ProductService;

@WebServlet("/OrderSuccess")
public class OrderSuccess extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static IProductService productService = new ProductService();
	private static IOrderService orderService = new OrderService();
	private static ICustomerService customerService = new CustomerService();
	private static ICartItemService cartItemService = new CartItemService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<String> id = new ArrayList<String>();
		Integer cusID = customerService.getCustomerID(request.getSession().getAttribute("name").toString());
		Integer orderID = orderService.getOrderID(cusID);
		Customer customer = customerService.getCustomer(cusID);
		List<CartItem> cartItems = cartItemService.getCartItemNotIn(request, id, orderID);
		// update láº¡i sá»‘ lÆ°á»£ng sáº£n pháº©m trong kho khi khĂ¡ch mua hĂ ng
		if(cartItems != null)
		{
			for (String amount : id) {
				productService.updateAmountProduct(Integer.parseInt(amount.substring(0, amount.indexOf(':'))),
						Integer.parseInt(amount.substring(amount.indexOf(':') + 1)));
			}
			if(orderService.updateOrder(orderID, orderService.addDays(new Date(), 2)))
				CartItem.date = orderService.addDays(new Date(), 2).toString();
			// remove cartItem khĂ´ng Ä‘c chon trong cart Ä‘c order
			if (cartItems != null && cartItems.size() > 0) {
				for (CartItem cartItem : cartItems)
					cartItemService.removeCartItem(cartItem.getProductID(), orderID);
				// update ngĂ y ship hĂ ng
				// táº¡o má»™t cart má»›i cho khĂ¡ch hĂ ng náº¿u nhá»¯ng Ä‘Æ¡n hĂ ng chÆ°a Ä‘c chá»�n
				int newOrderID = CartItemService.newDAO.saveCart(cusID);
				for (CartItem cartItem : cartItems)
					CartItemService.newDAO.saveCartItem(newOrderID, cartItem);
			}
			request.setAttribute("shippedDate", CartItem.date);
			request.setAttribute("orderID", orderID);
			request.setAttribute("customer", customer);
			request.getRequestDispatcher("orderSuccess.jsp").forward(request, response);
		}
		else{
			response.sendRedirect("loadProducts");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
}
