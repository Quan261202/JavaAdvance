package com.halloween.service.impl;

import java.util.List;

import com.halloween.dao.ICustomerDAO;
import com.halloween.dao.impl.CustomerDAO;
import com.halloween.model.Customer;
import com.halloween.service.ICustomerService;

public class CustomerService implements ICustomerService{

	private static ICustomerDAO newDAO = new CustomerDAO();
	
	@Override
	public Integer getCustomerID(String username) {
		return newDAO.getCustomerID(username);
	}

	@Override
	public Customer getCustomer(Integer customerID) {
		return newDAO.getCustomer(customerID);
	}

	@Override
	public Integer insertCustomer(Customer customer) {
		return newDAO.insertCustomer(customer);
	}

	@Override
	public List<String> getListProvince() {
		return newDAO.getListProvince();
	}

	@Override
	public List<String> getListDistrict(String province) {
		return newDAO.getListDistrict(province);
	}

	@Override
	public List<String> getListWard(String district) {
		return newDAO.getListWard(district);
	}

	@Override
	public Integer checkLogin(String userName, String password) {
		return newDAO.checkLogin(userName, password);
	}

	@Override
	public Boolean isAdmin(Integer customerID) {
		return newDAO.isAdmin(customerID);
	}
}
