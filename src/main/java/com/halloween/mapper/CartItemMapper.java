package com.halloween.mapper;

import java.sql.ResultSet;

import com.halloween.model.CartItem;

public class CartItemMapper implements INewMapper<CartItem>{

	@Override
	public CartItem mapRow(ResultSet resultSet) {
		try {
			return new CartItem(resultSet.getInt(1), resultSet.getString(2), resultSet.getDouble(3), resultSet.getInt(4), resultSet.getString(5));
		} catch (Exception e) {
			return null;
		}
	}
}
