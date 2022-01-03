package com.halloween.service;

import java.util.List;

import com.halloween.model.CategoryModel;

public interface ICategoryService {
	public String getCategoryName(Integer categoryID);
	public List<CategoryModel> getAllCategoryProduct();
}
