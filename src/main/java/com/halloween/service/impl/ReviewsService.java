package com.halloween.service.impl;

import com.halloween.dao.IReviewsDAO;
import com.halloween.dao.impl.ReviewsDAO;
import com.halloween.model.Reviews;
import com.halloween.service.IReviewsService;

import java.util.List;

public class ReviewsService implements IReviewsService {
	
	private static final IReviewsDAO newDAO = new ReviewsDAO();
	
	@Override
	public Double getTotalVote(Integer productID) {
		return newDAO.getTotalVote(productID);
	}

	@Override
	public Double getVoteFiveStart(Integer productID) {
		return newDAO.getVoteFiveStart(productID);
	}

    @Override
    public List<Reviews> getReviewsOfProduct(Integer productID, Integer vote) {
        return newDAO.getReviewsOfProduct(productID , vote);
    }

    @Override
    public List<Reviews> getReviewsOfProduct(Integer productID) {
		return newDAO.getReviewsOfProduct(productID);
    }

    @Override
    public Integer insert(Reviews reviews) {
        return newDAO.insert(reviews);
    }

    @Override
    public Boolean updateTotalLike(Integer id, char operator) {
        return newDAO.updateTotalLike(id, operator);
    }
}
