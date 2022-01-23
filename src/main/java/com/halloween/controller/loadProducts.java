package com.halloween.controller;

import com.halloween.model.Products;
import com.halloween.pagination.Pagination;
import com.halloween.service.*;
import com.halloween.service.impl.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serial;
import java.util.List;


public class loadProducts extends HttpServlet {
	@Serial
	private static final long serialVersionUID = 1L;
	public static final int LIMIT = 3;
	
	private static final IProductService productService = new ProductService();
	private static final ICategoryService categoryService = new CategoryService();
	private static final IOrderService orderService = new OrderService();
	private static final ICustomerService customerService = new CustomerService();
	private static final ICartItemService cartItemService = new CartItemService();
	private static int offsetNew = 0;
	private static int pageNew = 1;
	private static int offsetCandy = 0;
	private static int pageCandy = 1;
	private static final Integer CANDY = 4;
	private static final Integer NEW = 3;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer orderID;
		int categoryID = 4;
		int page = 1;
		if(request.getParameter("page") != null)
		{
			page = Integer.parseInt(request.getParameter("page"));
		}
		if(request.getParameter("category") != null)
			categoryID = Integer.parseInt(request.getParameter("category"));
		if(categoryID == NEW){
			pageNew = page;
			offsetNew = (pageNew - 1) * 3;
		}else if(categoryID == CANDY){
			pageCandy = page;
			offsetCandy = (pageCandy - 1) * 3;
		}
		Integer count;
		Integer totalItem = productService.countProductByCategory(categoryID);
		List<Products> listTwo = productService.getThreeItem(NEW, LIMIT, offsetNew);
		List<Products> listFour = productService.getThreeItem(CANDY, LIMIT, offsetCandy);
		String categoryTwo = categoryService.getCategoryName(NEW);
		String categoryFour = categoryService.getCategoryName(CANDY);
		com.halloween.pagination.Pagination pagination = new com.halloween.pagination.Pagination(3.0, pageCandy, 2, totalItem, CANDY);
		Pagination pagination1 = new Pagination(3.0, pageNew, 2, totalItem, NEW);
		if(request.getSession().getAttribute("name") != null)
		{
			String name = request.getSession().getAttribute("name").toString();
			orderID = orderService.getOrderID(customerService.getCustomerID(name));
			if(orderID !=null) {
				count  = cartItemService.getCountCartItemCurrentOfCustomer(orderID);
				request.setAttribute("cartItems", cartItemService.loadCart(orderID));
				request.setAttribute("count", count);
			}
			orderService.updateOrderSuccess();
		}
		request.setAttribute("listTwo" , listTwo);
		request.setAttribute("listFour" , listFour);
		request.setAttribute("nameTwo" , categoryTwo);
		request.setAttribute("pagination" , pagination.showPagination());
		request.setAttribute("pagination1" , pagination1.showPagination());
		request.setAttribute("nameFour" , categoryFour);
		if(request.getParameter("img") != null)
			request.setAttribute("img", request.getParameter("img"));
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		if(request.getSession().getAttribute("name") != null)
		{
			String name = request.getSession().getAttribute("name").toString();
			Integer customerID = customerService.getCustomerID(name);
			if(customerID != null)
			{
				Integer orderID = orderService.getOrderID(customerID);
				if(orderID != null)
				{
					int count = cartItemService.getCountCartItemCurrentOfCustomer(orderID);
					response.getWriter().println(count);
				}
			}
		}
	}
}

