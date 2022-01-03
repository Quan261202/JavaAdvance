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
import com.halloween.service.ICustomerService;
import com.halloween.service.impl.CustomerDetailService;
import com.halloween.service.impl.CustomerService;
import com.halloween.utils.SessionUtil;


@WebServlet("/UpdateAddress")
public class UpdateAddress extends HttpServlet {

	@Serial
	private static final long serialVersionUID = 1L;
	
	private static final ICustomerService customerService = new CustomerService();
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
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		int cusID = customerService.getCustomerID(request.getSession().getAttribute("name").toString());
		CustomerDetail customerDetail = customerDetailService.fyByCusID(cusID);
		if (customerDetail != null) {
			customerDetail.setAddress(address);
			customerDetail.setName(name);
			customerDetail.setPhone(phone);
			SessionUtil.checkSessionUtil().putValue(request.getSession(), "CUSTOMER", customerDetail);
			customerDetailService.updateAddress(customerDetail);
		} else {
			customerDetail = new CustomerDetail(cusID, name, phone, address);
			customerDetailService.insertCustomerDetail(customerDetail);
		}
		mapper.writeValue(response.getOutputStream(), mapper.writeValueAsString(customerDetail));
	}
}
