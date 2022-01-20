package com.halloween.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.halloween.model.CustomerDetail;
import com.halloween.service.ICustomerDetailService;
import com.halloween.service.impl.CustomerDetailService;
import com.halloween.utils.HttpUtil;
import com.halloween.utils.SessionUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serial;


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
		response.setContentType("application/json");
		ObjectMapper mapper = new ObjectMapper();
		CustomerDetail customerDetail = HttpUtil.of(request.getReader()).toModel(CustomerDetail.class);
 		if(customerDetail != null)
		{
			if(customerDetail.getPhone() == null)
			{
				String address = customerDetail.getAddress();
				if(customerDetailService.updateAddress(customerDetail.getCustomerID(), address))
				{
					mapper.writeValue(response.getOutputStream(), "Update success");
					customerDetail = (CustomerDetail) SessionUtil.checkSessionUtil().getValue(request.getSession(), "CUSTOMER");
					SessionUtil.checkSessionUtil().putValue(request.getSession(), "CUSTOMER", customerDetail);
				}
			}else{
				if(customerDetailService.updateAddress(customerDetail))
				{
					SessionUtil.checkSessionUtil().putValue(request.getSession(), "CUSTOMER", customerDetail);
					mapper.writeValue(response.getOutputStream(), "Update success");
				}else {
					mapper.writeValue(response.getOutputStream(), "Error");
				}
			}
		}
	}
}
