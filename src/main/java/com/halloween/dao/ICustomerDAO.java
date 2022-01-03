package com.halloween.dao;

import java.util.List;

import com.halloween.model.Customer;

public interface ICustomerDAO extends GenericDAO<Customer>{
	public Integer getCustomerID(String username);
	public Customer getCustomer(Integer customerID);
	public Integer checkLogin(String userName, String password);
	public Boolean isAdmin(Integer customerID);
	public Integer insertCustomer(Customer customer);
	public List<String> getListProvince();
	public List<String> getListDistrict(String province);
	public List<String> getListWard(String district);
}
