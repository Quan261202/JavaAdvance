package com.halloween.dao;

import java.util.List;

import com.halloween.model.CategoryModel;

public interface ICategoryDAO extends GenericDAO<CategoryModel>{
	public String getCategoryName(Integer categoryID);
	public List<CategoryModel> getAllCategoryProduct();
}
