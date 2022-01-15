package com.halloween.api;

import java.io.IOException;
import java.io.Serial;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.halloween.service.ICustomerService;
import com.halloween.service.impl.CustomerService;
import com.halloween.utils.HttpUtil;

@WebServlet(urlPatterns = {"/api/address"})
public class AddressAPI extends HttpServlet {

	@Serial
	private static final long serialVersionUID = 1L;
	
	private static final ICustomerService customerService = new CustomerService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		ObjectMapper mapper = new ObjectMapper();
		List<String> provinces = customerService.getListProvince();
		mapper.writeValue(response.getOutputStream(), mapper.writerWithDefaultPrettyPrinter().writeValueAsString(provinces));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		ObjectMapper mapper = new ObjectMapper();
		String json = HttpUtil.of(request.getReader()).getValue();
		if(json != null)
		{
			if(json.contains("province")) {
				String province = json.substring(json.indexOf(':') + 2, json.indexOf('}') - 1);
				List<String> districts = customerService.getListDistrict(province);
				mapper.writeValue(response.getOutputStream(), mapper.writerWithDefaultPrettyPrinter().writeValueAsString(districts));
			}else if(json.contains("district"))
			{
				String district = json.substring(json.indexOf(':') + 2, json.indexOf('}') - 1);
				List<String> districts = customerService.getListWard(district);
				mapper.writeValue(response.getOutputStream(), mapper.writerWithDefaultPrettyPrinter().writeValueAsString(districts));
			}
		}
	}
}
