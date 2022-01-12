package com.halloween.dao;

import java.util.List;

import com.halloween.model.Products;

public interface IProductDAO extends GenericDAO<Products> {
    List<Products> getAllByCategory(Integer categoryID);

    List<Products> getAllItems();

    List<Products> getThreeItem(Integer category, Integer limit, Integer offset);

    Integer countProductByCategory(Integer categoryID);

    Integer save(Products products);

    boolean update(Products products, Integer productID);

    Boolean updateAmountProduct(Integer productID, Integer quantity);

    Boolean delete(Integer productID);

    boolean deleteByCategoryID(Integer categoryID);

    Products findOne(Integer productID);

    List<Products> findByName(String name, Integer categoryID);

    Integer getCountProductSold(Integer productID);
}
