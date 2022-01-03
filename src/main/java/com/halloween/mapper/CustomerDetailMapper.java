package com.halloween.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.halloween.model.CustomerDetail;

public class CustomerDetailMapper implements INewMapper<CustomerDetail>{

	@Override
	public CustomerDetail mapRow(ResultSet resultSet) {
		try {
			return new CustomerDetail(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
