package com.halloween.service;

import java.util.List;

import com.halloween.model.CategoryModel;

public interface ICategoryService {
	String getCategoryName(Integer categoryID);
	List<CategoryModel> getAllCategoryProduct();
	List<Integer> getAllCategoryID();
	Boolean delete(Integer categoryID);
}
