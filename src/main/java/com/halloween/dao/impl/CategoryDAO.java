package com.halloween.dao.impl;

import com.halloween.dao.ICategoryDAO;
import com.halloween.mapper.CategoryMapper;
import com.halloween.model.CategoryModel;

import java.util.Date;
import java.util.List;

public class CategoryDAO extends AbstractDAO<CategoryModel> implements ICategoryDAO{

	@Override
	public String getCategoryName(Integer categoryID) {
		String sql = "SELECT categoryName FROM Category WHERE categoryID = ?";
		return getSingleObject(sql, 1, String.class, categoryID);
	}

	@Override
	public List<CategoryModel> getAllCategoryProduct() {
		String sql = "SELECT * FROM Category where deletedDate IS NULL";
		return query(sql, new CategoryMapper());
	}

	@Override
	public List<Integer> getAllCategoryID() {
		String sql = "SELECT categoryID FROM Category";
		return getListObject(sql, Integer.class);
	}

    @Override
    public Boolean delete(Integer categoryID) {
        String sql = "update Category set isDeleted = ?, deletedDate = ? where categoryID = ?";
		return updateOrDelete(sql, true, new Date(), categoryID);
    }

	@Override
	public boolean save(CategoryModel categoryModel) {
		return insert("INSERT INTO Category(categoryName, descriptions, createdDate) VALUES(?,?, ?)", categoryModel.getCategoryName(), categoryModel.getDescription(), new Date()) > 0;
	}
}
