package com.halloween.service.impl;

import com.halloween.dao.ICategoryDAO;
import com.halloween.dao.impl.CategoryDAO;
import com.halloween.model.CategoryModel;
import com.halloween.service.ICategoryService;

import java.util.List;

public class CategoryService implements ICategoryService{

	private final ICategoryDAO newDAO = new CategoryDAO();    
	
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

    @Override
    public Boolean delete(Integer categoryID) {
        return newDAO.delete(categoryID);
    }

	@Override
	public boolean insert(CategoryModel categoryModel) {
		return newDAO.save(categoryModel);
	}

	@Override
	public CategoryModel findOne(int id) {
		return newDAO.findOne(id);
	}

	@Override
	public void update(String categoryID, String categoryName, String description) {
		newDAO.update(categoryID, categoryName, description);
	}
}
