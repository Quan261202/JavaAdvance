package com.halloween.controller;

import java.io.IOException;
import java.io.Serial;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.halloween.model.Products;
import com.halloween.service.IProductService;
import com.halloween.service.impl.ProductService;

@WebServlet("/Pagination")
public class Pagination extends HttpServlet {
	@Serial
	private static final long serialVersionUID = 1L;
	private static final IProductService productService = new ProductService();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		if(request.getParameter("page") != null)
		{
			ObjectMapper mapper = new ObjectMapper();
			int page = Integer.parseInt(request.getParameter("page"));
			int offset = (page - 1) * 3;
			Integer totalItem = productService.countProductByCategory(4);
			com.halloween.pagination.Pagination pagination = new com.halloween.pagination.Pagination(3.0, page, 2, totalItem, 4);
			int LIMIT = 3;
			List<Products> listFour = productService.getThreeItem(4, LIMIT, offset);
			String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(listFour);
			json += mapper.writeValueAsString(pagination.showPagination());
			mapper.writeValue(response.getOutputStream(), json);
		}
	}
}
