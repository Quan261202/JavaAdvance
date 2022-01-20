package com.halloween.dao.impl;

import com.halloween.dao.ICustomerDAO;
import com.halloween.mapper.CustomerMapper;
import com.halloween.model.Customer;

import java.util.List;

public class CustomerDAO extends AbstractDAO<Customer> implements ICustomerDAO{

	@Override
	public Integer getCustomerID(String username) {
		String sql = "SELECT * FROM Customer WHERE customerName = ?";
		return getSingleObject(sql, 1, Integer.class, username);
	}

	@Override
	public Customer getCustomer(Integer customerID) {
		String sql = "SELECT * FROM Customer WHERE customerID = ?";
		return query(sql, new CustomerMapper(), customerID).get(0);
	}

	@Override
	public Integer checkLogin(String userName, String password) {
		String sql = "SELECT customerID FROM Customer WHERE customerName = ? AND password = ?";
		return getSingleObject(sql, 1, Integer.class, userName, password);
	}
	
	@Override
	public Boolean isAdmin(Integer customerID) {
		String sql = "SELECT customerID from Admins WHERE customerID = ?";
		return getSingleObject(sql, 1, Integer.class, customerID) != null;
	}

	@Override
	public Integer insertCustomer(Customer customer) {
		String sql = "INSERT INTO Customer(customerName, password, email) VALUES(?, ?, ?)";
		return insert(sql, customer.getUserName(), customer.getPassword(), customer.getEmail());
	}

	@Override
	public List<String> getListProvince(String key) {
		check = true;
		String str = "";
		if(key != null)
		{
			str = " where proName like '%" + key + "%'";
		}
		String sql = "SELECT proName FROM Province" + str;
		return getListObject(sql, String.class);
	}

	@Override
	public List<String> getListDistrict(String province, String key) {
		check = true;
		String str = "";
		if(key != null)
		{
			str = " and disName like '%" + key + "%'";
		}
		String sql = "SELECT d.disName FROM Province p INNER JOIN District d ON p.ID = d.proID WHERE p.proName = ?" + str;
		return getListObject(sql, String.class, province);
	}

	@Override
	public List<String> getListWard(String district, String key) {
		check = true;
		String str = "";
		if(key != null)
		{
			str = " and wardName like '%" + key + "%'";
		}
		String sql = "SELECT w.wardName  FROM Province P inner join District D on P.ID = D.proID\r\n"
				+ "					INNER JOIN Ward w ON d.ID = w.disID\r\n"
				+ "					WHERE  d.disName = N'" + district + "'" + str;
		return getListObject(sql, String.class);
	}
}
