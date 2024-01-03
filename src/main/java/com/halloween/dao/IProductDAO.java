package com.halloween.dao;

import com.halloween.model.Products;

import java.util.List;

public interface IProductDAO extends GenericDAO<Products> {
	
    List<Products> getAllByCategory(Integer categoryID);

    List<Products> getAllItems();

    List<Products> getThreeItem(String query, Integer category, Integer limit, Integer offset);

    Integer countProductByCategory(String query, Integer categoryID);

    Integer save(Products products);

    boolean update(Products products, Integer productID);

    Boolean updateAmountProduct(Integer productID, Integer quantity);

    Boolean delete(Integer productID);

    boolean deleteByCategoryID(Integer categoryID);

    Products findOne(Integer productID);

    List<Products> findByName(String name, Integer categoryID);

    Integer getCountProductSold(Integer productID);

    List<Products> getAllItemsByQuery(String categoryId, String query);
}
