package com.halloween.mapper;

import java.sql.ResultSet;
import com.halloween.model.Products;

public class ProductMapper implements INewMapper<Products> {

	@Override
	public Products mapRow(ResultSet resultSet) {
		try {
			return new Products(resultSet.getInt(1), resultSet.getString(2), resultSet.getDouble(3),
					resultSet.getString(4), resultSet.getInt(5), resultSet.getInt(6), resultSet.getInt(7), resultSet.getString(8));
		} catch (Exception e) {
			return null;
		}
	}
}
