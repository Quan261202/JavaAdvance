package com.halloween.dao.impl;

import com.halloween.dao.IReviewsDAO;
import com.halloween.model.Reviews;

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
}
