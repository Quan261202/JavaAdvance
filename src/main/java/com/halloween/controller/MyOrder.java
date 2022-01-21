package com.halloween.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.halloween.service.ICartItemService;
import com.halloween.service.ICustomerService;
import com.halloween.service.IOrderService;
import com.halloween.service.impl.CartItemService;
import com.halloween.service.impl.CustomerService;
import com.halloween.service.impl.OrderService;
import com.halloween.utils.HttpUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serial;
import java.util.List;
import java.util.Map;

@WebServlet("/MyOrder")
public class MyOrder extends HttpServlet {

	@Serial
	private static final long serialVersionUID = 1L;

	private static final IOrderService orderService = new OrderService();
	private static final ICustomerService customerService = new CustomerService();
	private static final ICartItemService cartItemService = new CartItemService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		String name = request.getSession().getAttribute("name").toString();
		Integer customerID = customerService.getCustomerID(name);
		List<Integer> integers = orderService.getAllOrderIDOfCustomer(customerID);
		int status = 1;
		if (request.getAttribute("status") != null) {
			status = Integer.parseInt(request.getAttribute("status").toString());
		}
		Integer orderID = orderService.getOrderID(customerID);
		if(orderID != null)
		{
			Integer count = orderService.getAmountOrderDelivered(orderID);
			Map<String, Object> hashMap = cartItemService.getAllOrdersOfCustomer(integers, customerID, status);
			request.setAttribute("map", hashMap);
			request.setAttribute("status", status);
			request.setAttribute("count", count);
			request.getRequestDispatcher("myOrder.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		ObjectMapper mapper = new ObjectMapper();
		String name = request.getSession().getAttribute("name").toString();
		int customerID = customerService.getCustomerID(name);
		HttpUtil util = HttpUtil.of(request.getReader());
		if(util != null) {
			String json = util.getValue();
			int status = Integer.parseInt(json.substring(json.indexOf(':') + 1, json.indexOf('}')));
			List<Integer> integers = orderService.getAllOrderIDOfCustomer(customerID);
			Map<String, Object> ordersOfCustomer = cartItemService.getAllOrdersOfCustomer(integers, customerID, status);
			if(ordersOfCustomer != null)
			{
				mapper.writeValue(response.getOutputStream(), mapper.writerWithDefaultPrettyPrinter().writeValueAsString(ordersOfCustomer));
			}
		}
	}
}
