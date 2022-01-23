package com.halloween.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.halloween.model.CartItem;
import com.halloween.service.ICartItemService;
import com.halloween.service.ICustomerService;
import com.halloween.service.IOrderService;
import com.halloween.service.impl.CartItemService;
import com.halloween.service.impl.CustomerService;
import com.halloween.service.impl.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serial;
import java.util.List;

public class removeCart extends HttpServlet {

	@Serial
	private static final long serialVersionUID = 1L;

	private static final IOrderService orderService = new OrderService();
	private static final ICustomerService customerService = new CustomerService();
	private static final ICartItemService cartItemService = new CartItemService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		ObjectMapper mapper = new ObjectMapper();
		int productID = Integer.parseInt(request.getParameter("id"));
		if (request.getSession().getAttribute("name") != null) {
			String name = request.getSession().getAttribute("name").toString();
			int orderID = orderService.getOrderID(customerService.getCustomerID(name));
			if (cartItemService.removeCartItem(productID, orderID)) {
				if (orderService.getCountOrderItem(orderID) == 0) {
					orderService.deleteOrders(orderID);
				}
				List<CartItem> cartItems = cartItemService.loadCart(orderID);
				String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(cartItems);
				mapper.writeValue(response.getOutputStream(), json);
			}
		}
	}
}
