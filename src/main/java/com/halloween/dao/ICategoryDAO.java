package com.halloween.dao;

import com.halloween.model.CategoryModel;

import java.util.List;

public interface ICategoryDAO extends GenericDAO<CategoryModel>{
	String getCategoryName(Integer categoryID);
	List<CategoryModel> getAllCategoryProduct();
	List<Integer> getAllCategoryID();
	Boolean delete(Integer categoryID);

    boolean save(CategoryModel categoryModel);
}
