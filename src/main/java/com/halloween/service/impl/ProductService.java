package com.halloween.service.impl;

import com.halloween.dao.IProductDAO;
import com.halloween.dao.impl.ProductDAO;
import com.halloween.model.Products;
import com.halloween.service.IProductService;

import java.util.List;

public class ProductService implements IProductService {

	private static final IProductDAO newDAO = new ProductDAO();
	
	@Override
	public List<Products> getAllByCategory(Integer categoryID) {
		return newDAO.getAllByCategory(categoryID);
	}

	@Override
	public Integer save(Products products) {
		return newDAO.save(products);
	}
	
	@Override
	public Boolean update(Products products, Integer productID) {
		return newDAO.update(products, productID);
	}

	@Override
	public Products findOne(Integer productID) {
		return newDAO.findOne(productID);
	}

	@Override
	public Boolean delete(Integer productID) {
		return newDAO.delete(productID);
	}

    @Override
    public boolean deleteByCategoryID(Integer categoryID) {
        return newDAO.deleteByCategoryID(categoryID);
    }

    @Override
	public List<Products> getThreeItem(String query, Integer category, Integer limit, Integer offset) {
		return newDAO.getThreeItem(query, category, limit, offset);
	}

	@Override
	public List<Products> getThreeItem(Integer category, Integer limit, Integer offset) {
		return newDAO.getThreeItem("", category, limit, offset);
	}

	@Override
	public Integer countProductByCategory(String query, Integer categoryID) {
		return newDAO.countProductByCategory(query, categoryID);
	}

	@Override
	public List<Products> getAllItemsByQuery(String categoryId, String query) {
		return newDAO.getAllItemsByQuery(categoryId, query);
	}

	@Override
	public List<Products> getAllItems() {
		return newDAO.getAllItems();
	}
}
