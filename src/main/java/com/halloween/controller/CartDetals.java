package com.halloween.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.halloween.model.CartItem;
import com.halloween.model.CustomerDetail;
import com.halloween.service.ICartItemService;
import com.halloween.service.ICustomerDetailService;
import com.halloween.service.ICustomerService;
import com.halloween.service.IOrderService;
import com.halloween.service.impl.CartItemService;
import com.halloween.service.impl.CustomerDetailService;
import com.halloween.service.impl.CustomerService;
import com.halloween.service.impl.OrderService;

@WebServlet(urlPatterns = {"/CartDetails"})
public class CartDetals extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ICustomerService customerService = new CustomerService();
	private static IOrderService orderService = new OrderService();
	private static ICustomerDetailService customerDetailService = new CustomerDetailService();
	private static ICartItemService cartItemService = new CartItemService();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<CartItem> cartItems = null;
		if(request.getSession().getAttribute("name") != null)
		{
			String name = request.getSession().getAttribute("name").toString();
			Integer customerID = customerService.getCustomerID(name);
			Integer orderID = orderService.getOrderID(customerID);
			if (orderID != null) cartItems = cartItemService.loadCart(orderID);
			CustomerDetail customerDetail = customerDetailService.getInfoCustomer(customerID); 
			request.setAttribute("cart", cartItems);
			if(customerDetail != null)	
				request.setAttribute("customer", customerDetail);
			request.getRequestDispatcher("cartDetal.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
