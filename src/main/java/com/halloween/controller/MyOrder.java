package com.halloween.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.halloween.model.CartItem;
import com.halloween.service.ICartItemService;
import com.halloween.service.ICustomerService;
import com.halloween.service.IOrderService;
import com.halloween.service.impl.CartItemService;
import com.halloween.service.impl.CustomerService;
import com.halloween.service.impl.OrderService;

@WebServlet("/MyOrder")
public class MyOrder extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static IOrderService orderService = new OrderService();
	private static ICustomerService customerService = new CustomerService();
	private static ICartItemService cartItemService = new CartItemService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		String name = request.getSession().getAttribute("name").toString();
		Integer cusID = customerService.getCustomerID(name);
		List<Integer> integers = orderService.getAllOrderIDOfCustomer(cusID);
		Integer status = 1;
		if (request.getAttribute("status") != null) {
			status = Integer.parseInt(request.getAttribute("status").toString());
		}
		Integer orderID = orderService.getOrderID(cusID);
		if(orderID != null)
		{
			Integer count = orderService.getAmountOrderDelivered(orderID);
			HashMap<Integer, List<CartItem>> hashMap = cartItemService.getAllCartItemOfOrder(integers, cusID, status);
			request.setAttribute("map", hashMap);
			request.setAttribute("status", status);
			request.setAttribute("count", count);
			request.getRequestDispatcher("myOrder.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		String name = request.getSession().getAttribute("name").toString();
		int cusID = customerService.getCustomerID(name);

		List<Integer> integers = orderService.getAllOrderIDOfCustomer(cusID);
		int status = 1;
		if (request.getParameter("status") != null) {
			status = Integer.parseInt(request.getParameter("status").toString());
			if (status < 4)
				status = 1;
			else if (status == 4)
				status = 2;
			else if (status == 5)
				status = 3;
		}
		HashMap<Integer, List<CartItem>> hashMap = cartItemService.getAllCartItemOfOrder(integers, cusID, status);
		String html = "";
		if (hashMap.size() > 0) {
			html = "<div class=\"store\">\r\n"
					+ "					<span><em class=\"fas fa-store\"></em>doanducminh79</span> <a href=\"\"><em\r\n"
					+ "						class=\"fas fa-comment-alt\"></em>chat</a> <a href=\"\"><em\r\n"
					+ "						class=\"fas fa-store\"></em>Xem shop</a>\r\n" + "				</div>";
			String text = "";
			if (status == 1) {
				text = "<div class=\"pay-function\">\r\n" + "	<a href=\"\">Chá»�</a>\r\n"
						+ "	<a href=\"\">LiĂªn há»‡ ngÆ°á»�i bĂ¡n</a>\r\n" + "	<a href=\"\">Há»§y Ä‘Æ¡n hĂ ng</a>\r\n"
						+ "</div>\r\n";
			} else if (status == 3) {
				text = "<div class=\"pay-function\">\r\n"
						+ "	<a style=\"background-color: #ee4d2d !important; opacity: 1\" href=\"\">Mua láº¡i</a>\r\n"
						+ "	<a href=\"\">LiĂªn há»‡ ngÆ°á»�i bĂ¡n</a>\r\n" + "	<a href=\"\">Chi tiáº¿t Ä‘Æ¡n há»§y</a>\r\n"
						+ "</div>\r\n";
			}
			for (Integer key : hashMap.keySet()) {
				double total = 0;
				for (CartItem cartItem : hashMap.get(key)) {
					html += "<div class=\"product-item\">\r\n" + "							<img src= '"
							+ cartItem.getUrlImage() + "' alt=\"\">\r\n"
							+ "							<div class=\"product-detail\">\r\n"
							+ "								<p>" + cartItem.getProductName() + "</p>\r\n"
							+ "								<span>x" + cartItem.getQuantity() + "</span>\r\n"
							+ "							</div>\r\n" + "							<p class=\"price\">"
							+ cartItem.getPrice() * cartItem.getQuantity() + "</p>\r\n" + "						</div>";
					total += cartItem.getPrice() * cartItem.getQuantity();
				}
				html += "<div class=\"pay\">\r\n"
						+ "						<span><img src=\"icons/gross.png\" alt=\"\"><a href=\"\"></a>Tá»•ng\r\n"
						+ "							sá»‘ tiá»�n: <span>$ </span><span>" + total + "</span></span>\r\n"
						+ text + "					</div>";
			}
		} else {
			html = "<div class=\"images\">\r\n"
					+ "                <img src=\"icons/5fafbb923393b712b96488590b8f781f.png\" alt=\"\">\r\n"
					+ "            </div>";
		}
		response.getWriter().println(html);
	}
}
