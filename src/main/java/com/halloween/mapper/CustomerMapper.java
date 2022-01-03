package com.halloween.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.halloween.model.Customer;

public class CustomerMapper implements INewMapper<Customer>{

	@Override
	public Customer mapRow(ResultSet resultSet) {
		try {
			return new Customer(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
