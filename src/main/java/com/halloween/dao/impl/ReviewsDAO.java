package com.halloween.dao.impl;

import com.halloween.dao.IReviewsDAO;
import com.halloween.mapper.ReviewsMapper;
import com.halloween.model.Reviews;

import java.util.List;

public class ReviewsDAO extends AbstractDAO<Reviews> implements IReviewsDAO{
	
	@Override
	public Double getTotalVote(Integer productID) {
		String sql = "select count(*) from reviews where productID = ?";
		return getSingleObject(sql, 1, Double.class, productID);
	}

	@Override
	public Double getVoteFiveStart(Integer productID) {
		String sql = "select count(*) from reviews where vote = 5 and productID = ?";
		return getSingleObject(sql, 1, Double.class, productID);
	}

    @Override
    public List<Reviews> getReviewsOfProduct(Integer productID, Integer vote) {
		String sql = "select urlImage, reviewDate, content, vote, concat(firstname, ' ', lastName), avatar from reviews inner join customerDetail on reviews.customerID = customerDetail.customerID where productID = ? and vote = ?";
		return query(sql, new ReviewsMapper(), productID, vote);
    }

	@Override
	public List<Reviews> getReviewsOfProduct(Integer productID) {
		String sql = "select urlImage, reviewDate, content, vote, concat(firstname, ' ', lastName), avatar from reviews inner join customerDetail on reviews.customerID = customerDetail.customerID where productID = ?";
		return query(sql, new ReviewsMapper(), productID);
	}
}
