package com.halloween.dao.impl;

import com.halloween.dao.ICustomerDetailDAO;
import com.halloween.mapper.CustomerDetailMapper;
import com.halloween.model.CustomerDetail;

public class CustomerDetailDAO extends AbstractDAO<CustomerDetail> implements ICustomerDetailDAO{
	@Override
	public CustomerDetail fyByCusID(Integer customerID) {
		String sql = "SELECT * FROM CustomerDetail WHERE customerID = ?";
		return query(sql, new CustomerDetailMapper(), customerID).get(0);
	}

	@Override
	public Integer insertCustomerDetail(CustomerDetail customer) {
		String sql = "INSERT INTO CustomerDetail VALUE(?, ?, ?, ?, ?, ?, ?)";
		return insert(sql, customer.getCustomerID()
									, customer.getFirstName()
									, customer.getLastName()
									, customer.getPhone()
									, customer.getAddress()
									, customer.getAvatar()
									, customer.getEmail());
	}

	@Override
	public CustomerDetail getInfoCustomer(Integer customerID) {
		String sql = "SELECT * FROM CustomerDetail WHERE customerID = ?";
		return query(sql, new CustomerDetailMapper(), customerID).get(0);
	}

	@Override
		public Boolean updateProfile(CustomerDetail customer) {
			String sql = "UPDATE CustomerDetail SET firstName = ?, lastName = ? , phone = ? , address = ?, avatar = ?, email = ? "
					+ "WHERE customerID = ? ";
			return updateOrDelete(sql, customer.getFirstName()
					                      				, customer.getLastName()
					                      				, customer.getPhone()
					                      				, customer.getAddress()
					                      				, customer.getAvatar()
					                      				, customer.getEmail()
					                      				, customer.getCustomerID());
	}
	
	@Override
	public Boolean updateAddress(CustomerDetail customer) {
		String sql = "UPDATE CustomerDetail SET firstName = ?, lastName = ? , phone = ? , address = ? "
				+ "WHERE customerID = ? ";
		return updateOrDelete(sql, customer.getFirstName()
				                      				, customer.getLastName()
				                      				, customer.getPhone()
				                      				, customer.getAddress()
				                      				, customer.getCustomerID());
	}
}
