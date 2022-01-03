package com.halloween.dao.impl;

import com.halloween.dao.ICustomerDetailDAO;
import com.halloween.mapper.CustomerDetailMapper;
import com.halloween.model.CustomerDetail;

public class CustomerDetailDAO extends AbstractDAO<CustomerDetail> implements ICustomerDetailDAO{
	@Override
	public CustomerDetail fyByCusID(Integer customerID) {
		String sql = "SELECT * FROM CustomerDetail WHERE cusID = ?";
		return query(sql, new CustomerDetailMapper(), customerID).get(0);
	}

	@Override
	public Integer insertCustomerDetail(CustomerDetail customer) {
		String sql = "INSERT INTO CustomerDetail VALUE(?, ?, ?, ?)";
		return insert(sql, customer.getCusID(), customer.getName(), customer.getPhone(), customer.getAddress());
	}

	@Override
	public CustomerDetail getInfoCustomer(Integer customerID) {
		String sql = "SELECT * FROM CustomerDetail WHERE cusID = ?";
		return query(sql, new CustomerDetailMapper(), customerID).get(0);
	}

	@Override
		public Boolean updateAddress(CustomerDetail customer) {
			String sql = "UPDATE CustomerDetail SET cusName = ? , phone = ? , address = ? "
					+ "WHERE cusID = ? ";
			return updateOrDelete(sql, customer.getName(), customer.getPhone(), customer.getAddress(), customer.getCusID());
	}
}
