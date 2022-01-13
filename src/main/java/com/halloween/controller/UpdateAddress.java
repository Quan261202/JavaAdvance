package com.halloween.controller;

import java.io.IOException;
import java.io.Serial;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.halloween.model.CustomerDetail;
import com.halloween.service.ICustomerDetailService;
import com.halloween.service.impl.CustomerDetailService;
import com.halloween.utils.HttpUtil;


@WebServlet("/UpdateAddress")
public class UpdateAddress extends HttpServlet {

	@Serial
	private static final long serialVersionUID = 1L;
	
	private static final ICustomerDetailService customerDetailService = new CustomerDetailService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		ObjectMapper mapper = new ObjectMapper();
		CustomerDetail customerDetail = HttpUtil.of(request.getReader()).toModel(CustomerDetail.class);
		if(customerDetail != null)
		{
			if(customerDetailService.updateAddress(customerDetail))
			{
				mapper.writeValue(response.getOutputStream(), "Update success");
			}else {
				mapper.writeValue(response.getOutputStream(), "Error");
			}
		}
	}
}
