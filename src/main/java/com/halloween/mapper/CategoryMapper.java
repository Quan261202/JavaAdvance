package com.halloween.mapper;

import com.halloween.model.CategoryModel;

import java.sql.ResultSet;

public class CategoryMapper implements INewMapper<CategoryModel>{

	@Override
	public CategoryModel mapRow(ResultSet resultSet) {
		try {
			return new CategoryModel(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
		} catch (Exception e) {
			return null;
		}
	}
}
