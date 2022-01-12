package com.halloween.service.impl;

import java.util.List;


import com.halloween.dao.IProductDAO;
import com.halloween.dao.impl.ProductDAO;
import com.halloween.model.Products;
import com.halloween.service.IProductService;

public class ProductService implements IProductService {

	private static final IProductDAO newDAO = new ProductDAO();
	
	@Override
	public List<Products> getAllByCategory(Integer categoryID) {
		return newDAO.getAllByCategory(categoryID);
	}
	
	@Override
	public Integer countProductByCategory(Integer categoryID) {
		return newDAO.countProductByCategory(categoryID);
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
	public List<Products> getThreeItem(Integer category, Integer limit, Integer offset) {
		return newDAO.getThreeItem(category, limit, offset);
	}

	@Override
	public Boolean updateAmountProduct(Integer productID, Integer quantity) {
		return newDAO.updateAmountProduct(productID, quantity);
	}

	@Override
	public List<Products> findByName(String name, Integer categoryID) {
		return newDAO.findByName(name, categoryID);
	}

    @Override
    public Integer getCountProductSold(Integer productID) {
        return newDAO.getCountProductSold(productID);
    }

    @Override
	public List<Products> getAllItems() {
		return newDAO.getAllItems();
	}
}
