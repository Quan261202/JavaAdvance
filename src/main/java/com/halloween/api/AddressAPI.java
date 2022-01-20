package com.halloween.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.halloween.model.Address;
import com.halloween.service.ICustomerService;
import com.halloween.service.impl.CustomerService;
import com.halloween.utils.HttpUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serial;
import java.util.List;

@WebServlet(urlPatterns = {"/api/address"})
public class AddressAPI extends HttpServlet {

	@Serial
	private static final long serialVersionUID = 1L;
	
	private static final ICustomerService customerService = new CustomerService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		ObjectMapper mapper = new ObjectMapper();
		List<String> provinces = customerService.getListProvince(null);
		mapper.writeValue(response.getOutputStream(), mapper.writerWithDefaultPrettyPrinter().writeValueAsString(provinces));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		ObjectMapper mapper = new ObjectMapper();
		HttpUtil util = HttpUtil.of(request.getReader());
		if(util != null)
		{
			String json = util.getValue();
			if(json != null)
			{
				if(json.contains("key"))
				{
					Address address = mapper.readValue(json, Address.class);
					List<String> list;
					if(address.getType().equals("province"))
					{
						list = customerService.getListProvince(address.getKey());
					}else if(address.getType().equals("district"))
					{
						list = customerService.getListDistrict(address.getName(), address.getKey());
					}else{
						list = customerService.getListWard(address.getName(), address.getKey());
					}
					mapper.writeValue(response.getOutputStream(), mapper.writerWithDefaultPrettyPrinter().writeValueAsString(list));
				}
				else if(json.contains("province")) {
					String province = subStr(json);
					List<String> districts = customerService.getListDistrict(province, null);
					mapper.writeValue(response.getOutputStream(), mapper.writerWithDefaultPrettyPrinter().writeValueAsString(districts));
				}else if(json.contains("district"))
				{
					String district = subStr(json);
					List<String> districts = customerService.getListWard(district, null);
					mapper.writeValue(response.getOutputStream(), mapper.writerWithDefaultPrettyPrinter().writeValueAsString(districts));
				}
			}
		}
	}

	private String subStr(String value)
	{
		return value.substring(value.indexOf(':') + 2, value.indexOf('}') - 1);
	}
}
