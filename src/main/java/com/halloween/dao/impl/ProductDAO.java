package com.halloween.dao.impl;

import com.halloween.dao.IProductDAO;
import com.halloween.mapper.ProductMapper;
import com.halloween.model.Products;

import java.util.Date;
import java.util.List;

public class ProductDAO  extends AbstractDAO<Products> implements IProductDAO{

	@Override
	public List<Products> getAllByCategory(Integer categoryID) {
		String sql = "SELECT  * FROM Products WHERE category = ? AND deletedDate IS null";
		return query(sql, new ProductMapper(), categoryID);
	}
	
	@Override
	public Integer countProductByCategory(String query, Integer categoryID) {
		if(query == null) {
			query = "";
		}
		String sql = "SELECT COUNT(*) FROM Products where productName LIKE '%" + query + "%' AND category = ?";
		Integer count = getSingleObject(sql, 1, Integer.class, categoryID);
		return count == null ? 0 : count;
	}

	@Override
	public Integer save(Products products) {
		String sql = "INSERT INTO Products(productName, price, urlImage, createdDate, status, quantity, category, shortDescription)"
				+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		return insert(sql, products.getProductName()
									, products.getPrice()
									, products.getUrlImage()
									, new Date()
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
		String sql = "UPDATE Products SET productName = ?, urlImage = ?, price = ?, status = ?, quantity = ?, category = ?, shortDescription = ?, updatedDate = ? WHERE productID = ?";
		return updateOrDelete(sql, products.getProductName(), products.getUrlImage()
						  							 , products.getPrice()
						  							 , products.getStatus()
						  							 , products.getQuantity()
						  							 , products.getCategoryID()
						  							 , products.getShortDescription()
													 , new Date()
						  							 , productID);
	}

	@Override
	public Boolean delete(Integer productID) {
		String sql = "UPDATE Products SET deletedDate = ?, isDeleted = ? WHERE productID = ?";
		return updateOrDelete(sql, new Date(), true, productID);
	}

    @Override
    public boolean deleteByCategoryID(Integer categoryID) {
        String sql = "UPDATE Products SET deletedDate = ?, isDeleted = ? WHERE category = ?";
		return updateOrDelete(sql, new Date(), true, categoryID);
    }

    @Override
	public List<Products> getThreeItem(String query, Integer category, Integer limit, Integer offset) {
		if(query == null) {
			query = "";
		}
		String sql = "SELECT * FROM Products WHERE productName like '%" + query +  "%' AND category = ? AND deletedDate IS null LIMIT ? OFFSET ?";
		return query(sql, new ProductMapper(), category, limit, offset);
	}
	
	@Override
	public Boolean updateAmountProduct(Integer productID, Integer quantity) {
		String sql = "UPDATE Products SET quantity = quantity - ? where products.productID = ?";
		return updateOrDelete(sql, quantity, productID);
	}
	
	@Override
	public List<Products> findByName(String name, Integer categoryID) {
		String sql = "SELECT * FROM Products WHERE productName LIKE '%" +name + "%'" + "AND category = ?";
		return query(sql, new ProductMapper(), categoryID);
	}


	@Override
	public Integer getCountProductSold(Integer productID) {
		String sql = "select sum(oi.amount) from orderItem oi inner join Orders o on oi.orderID = o.id where o.status = 2 and oi.productID = ?";
		return getSingleObject(sql, 1, Integer.class, productID);
	}

	@Override
	public List<Products> getAllItemsByQuery(String categoryId, String query) {
		if(query == null) {
			query = "";
		}
		String sql = "SELECT * FROM Products WHERE productName like '%" + query +  "%' AND deletedDate IS null";
		if(categoryId != null) {
			sql += " AND category = ?";
			return query(sql, new ProductMapper(), Integer.parseInt(categoryId));
		}
		return query(sql, new ProductMapper());
	}

	@Override
	public List<Products> getAllItems() {
		String sql = "SELECT * FROM Products";
		return query(sql, new ProductMapper());
	}

}
