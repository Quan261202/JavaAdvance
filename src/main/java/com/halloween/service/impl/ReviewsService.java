package com.halloween.service.impl;

import com.halloween.dao.IReviewsDAO;
import com.halloween.dao.impl.ReviewsDAO;
import com.halloween.service.IReviewsService;

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
}
