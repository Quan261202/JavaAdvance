package com.halloween.dao;

import java.util.List;

import com.halloween.model.CategoryModel;

public interface ICategoryDAO extends GenericDAO<CategoryModel>{
	String getCategoryName(Integer categoryID);
	List<CategoryModel> getAllCategoryProduct();
	List<Integer> getAllCategoryID();
	Boolean delete(Integer categoryID);
}
