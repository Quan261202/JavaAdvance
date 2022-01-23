package com.halloween.controller;

import com.halloween.constant.OrderMessage;
import com.halloween.mail.SendMail;
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

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serial;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/OrderSuccess")
public class OrderSuccess extends HttpServlet {

	@Serial
	private static final long serialVersionUID = 1L;

	private static final IProductService productService = new ProductService();
	private static final IOrderService orderService = new OrderService();
	private static final ICustomerService customerService = new CustomerService();
	private static final ICartItemService cartItemService = new CartItemService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<String> id = new ArrayList<>();
		Integer cusID = customerService.getCustomerID(request.getSession().getAttribute("name").toString());
		Integer orderID = orderService.getOrderID(cusID);
		Customer customer = customerService.getCustomer(cusID);
		List<CartItem> cartItems = cartItemService.getCartItemNotIn(request, id, orderID);
		if(cartItems != null)
		{
			for (String amount : id) {
				productService.updateAmountProduct(Integer.parseInt(amount.substring(0, amount.indexOf(':'))),
						Integer.parseInt(amount.substring(amount.indexOf(':') + 1)));
			}
			if(orderService.updateOrder(orderID, orderService.addDays(new Date(), 2)))
				CartItem.date = orderService.addDays(new Date(), 2).toString();
			if (cartItems.size() > 0) {
				for (CartItem cartItem : cartItems)
					cartItemService.removeCartItem(cartItem.getProductID(), orderID);
				int newOrderID = CartItemService.newDAO.saveCart(cusID);
				for (CartItem cartItem : cartItems)
					CartItemService.newDAO.saveCartItem(newOrderID, cartItem);
			}
			request.setAttribute("shippedDate", CartItem.date);
			request.setAttribute("orderID", orderID);
			request.setAttribute("customer", customer);
			SendMail.senMail(customer.getEmail(), "ShoppingHalloween", OrderMessage.SUCCESS);
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
