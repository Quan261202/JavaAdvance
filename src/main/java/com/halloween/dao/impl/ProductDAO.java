package com.halloween.dao.impl;

import com.halloween.dao.IProductDAO;
import com.halloween.mapper.ProductMapper;
import com.halloween.model.Products;

import java.util.List;

public class ProductDAO  extends AbstractDAO<Products> implements IProductDAO{

	@Override
	public List<Products> getAllByCategory(Integer categoryID) {
		String sql = "SELECT  * FROM Products WHERE category = ?";
		return query(sql, new ProductMapper(), categoryID);
	}
	
	@Override
	public Integer countProductByCategory(Integer categoryID) {
		String sql = "SELECT COUNT(*) FROM Products where category = ?";
		Integer count = getSingleObject(sql, 1, Integer.class, categoryID);
		return count == null ? 0 : count;
	}

	@Override
	public Integer save(Products products) {
		String sql = "INSERT INTO Products(productName, price, urlImage, status, quantity, category, shortDescription)"
				+ " VALUES(?, ?, ?, ?, ?, ?, ?)";
		return insert(sql, products.getProductName()
									, products.getPrice()
									, products.getUrlImage()
									, products.getStatus()
									, products.getQuantity()
									, products.getCategoryID()
									, products.getShortDescription());
	}

	@Override
	public Products findOne(Integer productID) {
		String sql = "SELECT * FROM Products WHERE ProductID = ?";
		return query(sql, new ProductMapper(),  productID).get(0);
	}

	@Override
	public boolean update(Products products, Integer productID) {
		String sql = "UPDATE Products SET productName = ?, price = ?, status = ?, quantity = ?, category = ?, shortDescription = ? WHERE productID = ?";
		return updateOrDelete(sql, products.getProductName()
						  							 , products.getPrice()
						  							 , products.getStatus()
						  							 , products.getQuantity()
						  							 , products.getCategoryID()
						  							 , products.getShortDescription()
						  							 , productID);
	}

	@Override
	public Boolean delete(Integer productID) {
		String sql = "DELETE FROM Products WHERE productID = ?";
		return updateOrDelete(sql, productID);
	}

    @Override
    public boolean deleteByCategoryID(Integer categoryID) {
        String sql = "delete from Products where category = ?";
		return updateOrDelete(sql, categoryID);
    }

    @Override
	public List<Products> getThreeItem(Integer category, Integer limit, Integer offset) {
		String sql = "SELECT * FROM Products WHERE category = ? LIMIT ? OFFSET ?";
		return query(sql, new ProductMapper(), category, limit, offset);
	}
	
	@Override
	public Boolean updateAmountProduct(Integer productID, Integer quantity) {
		String sql = "UPDATE Products SET quantity = quantity - ? where products.productID = ?";
		return updateOrDelete(sql, quantity, productID);
	}
	
	@Override
	public List<Products> findByName(String name, Integer categoryID) {
		String sql = "SELECT * FROM Products WHERE productName  LIKE '%" +name + "%'" + "AND category = ?";
		return query(sql, new ProductMapper(), categoryID);
	}


	@Override
	public Integer getCountProductSold(Integer productID) {
		String sql = "select sum(oi.amount) from orderItem oi inner join Orders o on oi.orderID = o.id where o.status = 2 and oi.productID = ?";
		return getSingleObject(sql, 1, Integer.class, productID);
	}

	@Override
	public List<Products> getAllItems() {
		String sql = "SELECT * FROM Products";
		return query(sql, new ProductMapper());
	}
}
