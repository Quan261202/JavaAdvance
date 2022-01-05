package com.halloween.service.impl;

import java.util.List;

import com.halloween.dao.ICategoryDAO;
import com.halloween.dao.impl.CategoryDAO;
import com.halloween.model.CategoryModel;
import com.halloween.service.ICategoryService;

public class CategoryService implements ICategoryService{

	private ICategoryDAO newDAO = new CategoryDAO();
	
	@Override
	public String getCategoryName(Integer categoryID) {
		return newDAO.getCategoryName(categoryID);
	}

	@Override
	public List<CategoryModel> getAllCategoryProduct() {
		return newDAO.getAllCategoryProduct();
	}

	@Override
	public List<Integer> getAllCategoryID() {
		return newDAO.getAllCategoryID();
	}
}
