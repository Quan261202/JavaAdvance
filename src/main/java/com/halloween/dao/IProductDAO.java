package com.halloween.dao;

import java.util.List;

import com.halloween.model.Products;

public interface IProductDAO extends GenericDAO<Products>{
	public List<Products> getAllByCategory(Integer categoryID);
	public List<Products> getAllItems();
	public List<Products> getThreeItem(Integer category, Integer limit, Integer offset);
	public Integer countProductByCategory(Integer categoryID);
	public Integer save(Products products);
	public boolean update(Products products, Integer productID);
	public Boolean updateAmountProduct(Integer productID, Integer quantity);
	public Boolean delete(Integer productID);
	public Products findOne(Integer productID);
	public List<Products> findByName(String name, Integer categoryID);
}
