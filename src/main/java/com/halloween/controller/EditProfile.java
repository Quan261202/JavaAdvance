package com.halloween.controller;

import java.io.IOException;
import java.io.Serial;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.halloween.model.CustomerDetail;
import com.halloween.service.ICustomerDetailService;
import com.halloween.service.ICustomerService;
import com.halloween.service.impl.CustomerDetailService;
import com.halloween.service.impl.CustomerService;
import com.halloween.utils.HttpUtil;
import com.halloween.utils.SessionUtil;

public class EditProfile extends HttpServlet {

	@Serial
	private static final long serialVersionUID = 1L;
	
	private static final ICustomerService customerService = new CustomerService();
	private static final ICustomerDetailService customerDetailService = new CustomerDetailService();
     
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		if(request.getSession().getAttribute("name") != null) {
			String name = request.getSession().getAttribute("name").toString();
			Integer customerID = customerService.getCustomerID(name);
			CustomerDetail customerDetail = customerDetailService.fyByCusID(customerID);
			String [] address = customerDetail.getAddress().split(", ");
			List<String> list = customerService.getListProvince();
			List<String> listDis = customerService.getListDistrict(address[3]);
			List<String> listWard = customerService.getListWard(address[2]);
			request.setAttribute("province", list);
			request.setAttribute("district", listDis);
			request.setAttribute("ward", listWard);
			request.setAttribute("customer", customerDetail);
			request.getRequestDispatcher("editProfile.jsp").forward(request, response);
		}
	}	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		ObjectMapper mapper = new ObjectMapper();
		CustomerDetail customerDetail = HttpUtil.of(request.getReader()).toModel(CustomerDetail.class);
		if(customerDetail != null)
		{
			if(customerDetailService.updateProfile(customerDetail))
			{
				SessionUtil.checkSessionUtil().putValue(request.getSession(), "CUSTOMER", customerDetail);
				mapper.writeValue(response.getOutputStream(), "update success");
			}
		}
	}
}
