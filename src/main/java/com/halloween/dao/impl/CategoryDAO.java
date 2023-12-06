package com.halloween.dao.impl;

import com.halloween.dao.ICategoryDAO;
import com.halloween.mapper.CategoryMapper;
import com.halloween.model.CategoryModel;

import java.util.List;

public class CategoryDAO extends AbstractDAO<CategoryModel> implements ICategoryDAO{

	@Override
	public String getCategoryName(Integer categoryID) {
		String sql = "SELECT categoryName FROM Category WHERE categoryID = ?";
		return getSingleObject(sql, 1, String.class, categoryID);
	}

	@Override
	public List<CategoryModel> getAllCategoryProduct() {
		String sql = "SELECT * FROM Category";
		return query(sql, new CategoryMapper());
	}

	@Override
	public List<Integer> getAllCategoryID() {
		String sql = "SELECT categoryID FROM Category";
		return getListObject(sql, Integer.class);
	}

    @Override
    public Boolean delete(Integer categoryID) {
        String sql = "delete from Category where categoryID = ?";
		return updateOrDelete(sql, categoryID);
    }
}
