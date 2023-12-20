package com.halloween.mapper;

import com.halloween.model.Products;

import java.sql.ResultSet;

public class ProductMapper implements INewMapper<Products> {

	@Override
	public Products mapRow(ResultSet resultSet) {
		try {
			return new Products(resultSet.getInt(1), resultSet.getString(2), resultSet.getDouble(3),
					resultSet.getString(4), resultSet.getDate(5), resultSet.getDate(6), resultSet.getDate(7), resultSet.getBoolean(8), resultSet.getInt(9), resultSet.getInt(10), resultSet.getInt(11), resultSet.getString(12));
		} catch (Exception e) {
			return null;
		}
	}
}
