package com.halloween.service.impl;

import com.halloween.dao.ICustomerDAO;
import com.halloween.dao.impl.CustomerDAO;
import com.halloween.model.Customer;
import com.halloween.service.ICustomerService;

import java.util.List;

public class CustomerService implements ICustomerService{

	private static final ICustomerDAO newDAO = new CustomerDAO();
	
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
	public List<String> getListProvince(String key) {
		return newDAO.getListProvince(key);
	}

	@Override
	public List<String> getListDistrict(String province, String key) {
		return newDAO.getListDistrict(province, key);
	}

	@Override
	public List<String> getListWard(String district, String key) {
		return newDAO.getListWard(district, key);
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
