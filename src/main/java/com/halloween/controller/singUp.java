package com.halloween.controller;

import java.io.IOException;
import java.io.Serial;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.halloween.model.Customer;
import com.halloween.service.impl.CustomerService;
import com.halloween.utils.HttpUtil;

public class singUp extends HttpServlet {

	@Serial
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		ObjectMapper mapper = new ObjectMapper();
		HttpUtil httpUtil = HttpUtil.of(request.getReader());
		if(httpUtil != null)
		{
			String json = httpUtil.getValue();
			Customer customer = mapper.readValue(json, Customer.class);
			Integer integer = new CustomerService().insertCustomer(customer);
			if(integer != null) mapper.writeValue(response.getOutputStream(), "Sign Up Success");
		}
	}
}
