package com.halloween.dao.impl;
import java.util.List;

import com.halloween.dao.IProductDAO;
import com.halloween.mapper.ProductMapper;
import com.halloween.model.Products;

public class ProductDAO  extends AbstractDAO<Products> implements IProductDAO{

	@Override
	public List<Products> getAllByCategory(Integer categoryID) {
		String sql = "SELECT  * FROM Products WHERE category = ?";
		return query(sql, new ProductMapper(), categoryID);
	}
	
	@Override
	public Integer countProductByCategory(Integer categoryID) {
		String sql = "SELECT COUNT(*) FROM Products where category = ?";
		return (Integer) getSingleObject(sql, 1, Integer.class, categoryID);
	}

	@Override
	public Integer save(Products products) {
		String sql = "INSERT INTO Products(productName, price, urlImage, status, quantity, category)"
				+ " VALUES(?, ?, ?, ?, ?, ?)";
		return insert(sql, products.getProductName()
									, products.getPrice()
									, products.getUrlImage()
									, products.getStatus()
									, products.getQuantity()
									, products.getCategoryID());
	}

	@Override
	public Products findOne(Integer productID) {
		String sql = "SELECT * FROM Products WHERE ProductID = ?";
		return query(sql, new ProductMapper(),  productID).stream().findFirst().get();
	}

	@Override
	public boolean update(Products products, Integer productID) {
		String sql = "UPDATE products SET productName = ?, price = ?, status = ?, quantity = ?, category = ? WHERE productID = ?";
		return updateOrDelete(sql, products.getProductName()
						  							 , products.getPrice()
						  							 , products.getStatus()
						  							 , products.getQuantity()
						  							 , products.getCategoryID()
						  							 , productID);
	}

	@Override
	public Boolean delete(Integer productID) {
		String sql = "DELETE FROM Products WHERE productID = ?";
		return updateOrDelete(sql, productID);
	}

	@Override
	public List<Products> getThreeItem(Integer category, Integer limit, Integer offset) {
		String sql = "SELECT * FROM Products WHERE category = ? LIMIT ? OFFSET ?";
		return query(sql, new ProductMapper(), category, limit, offset);
	}
	
	@Override
	public Boolean updateAmountProduct(Integer productID, Integer quantity) {
		String sql = "UPDATE products SET quantity = quantity - ? where products.productID = ?";
		return updateOrDelete(sql, quantity, productID);
	}
	
	@Override
	public List<Products> findByName(String name, Integer categoryID) {
		String sql = "SELECT * FROM Products WHERE productName  LIKE '%" +name + "%'" + "AND category = ?";
		return query(sql, new ProductMapper(), categoryID);
	}

	@Override
	public List<Products> getAllItems() {
		String sql = "SELECT * FROM Products";
		return query(sql, new ProductMapper());
	}
}
