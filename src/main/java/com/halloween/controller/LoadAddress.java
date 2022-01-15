package com.halloween.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.halloween.service.ICustomerService;
import com.halloween.service.impl.CustomerService;

@WebServlet("/LoadAddress")
public class LoadAddress extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static ICustomerService customerService = new CustomerService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("address") != null) {
			String[] address = request.getParameter("address").split(", ");
			List<String> list = customerService.getListProvince();
			List<String> listDis = customerService.getListDistrict(address[3]);
			List<String> listWard = customerService.getListWard(address[2]);
			request.setAttribute("province", list);
			request.setAttribute("district", listDis);
			request.setAttribute("ward", listWard);
			request.getRequestDispatcher("changeAddress.jsp").forward(request, response);
		}
		else {
			List<String> list = customerService.getListProvince();
			request.setAttribute("province", list);
			request.getRequestDispatcher("changeAddress.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
}
