package com.halloween.controller;

import com.halloween.service.ICartItemService;
import com.halloween.service.ICustomerService;
import com.halloween.service.IOrderService;
import com.halloween.service.impl.CartItemService;
import com.halloween.service.impl.CustomerService;
import com.halloween.service.impl.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serial;

@WebServlet("/UpdateCart")
public class UpdateCart extends HttpServlet {

	@Serial
	private static final long serialVersionUID = 1L;
	
	private static final IOrderService orderService = new OrderService();
	private static final ICustomerService customerService = new CustomerService();
	private static final ICartItemService cartItemService = new CartItemService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int productID = Integer.parseInt(request.getParameter("id"));
		int amount = Integer.parseInt(request.getParameter("amount"));
		String username = request.getSession().getAttribute("name").toString();
		Integer customerID = customerService.getCustomerID(username);
		Integer orderID = orderService.getOrderID(customerID);
		if(cartItemService.updateCartItem(amount, productID, orderID))
			response.setStatus(200);
	}
}
