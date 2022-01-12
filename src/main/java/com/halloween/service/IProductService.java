package com.halloween.service;

import java.util.List;

import com.halloween.model.Products;

public interface IProductService {
    List<Products> getAllItems();

    List<Products> getAllByCategory(Integer categoryID);

    List<Products> getThreeItem(Integer category, Integer limit, Integer offset);

    Integer countProductByCategory(Integer categoryID);

    Integer save(Products products);

    Boolean update(Products products, Integer productID);

    Boolean updateAmountProduct(Integer productID, Integer quantity);

    Boolean delete(Integer productID);

    boolean deleteByCategoryID(Integer categoryID);

    Products findOne(Integer productID);

    List<Products> findByName(String name, Integer categoryID);

    Integer getCountProductSold(Integer productID);
}
