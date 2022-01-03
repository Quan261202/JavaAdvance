package com.halloween.mapper;

import java.sql.ResultSet;

import com.halloween.model.CategoryModel;

public class CategoryMapper implements INewMapper<CategoryModel>{

	@Override
	public CategoryModel mapRow(ResultSet resultSet) {
		try {
			return new CategoryModel(resultSet.getInt(1), resultSet.getString(2));
		} catch (Exception e) {
			return null;
		}
	}
}
