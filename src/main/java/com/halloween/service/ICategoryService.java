package com.halloween.service;

import com.halloween.model.CategoryModel;

import java.util.List;

public interface ICategoryService {
	String getCategoryName(Integer categoryID);
	List<CategoryModel> getAllCategoryProduct();
	List<Integer> getAllCategoryID();
	Boolean delete(Integer categoryID);

	boolean insert(CategoryModel categoryModel);
}
