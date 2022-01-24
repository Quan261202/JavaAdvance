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
    public List<Reviews> getReviewsOfProduct(Integer productID, Integer customerID, Object... param) {
        List<Reviews> list = newDAO.getReviewsOfProduct(productID);
        for(Reviews reviews : list) {
            reviews.setTotalLike(newDAO.getTotalLikeOfReviews(reviews.getId()));
            reviews.setLike(newDAO.findOne(customerID, reviews.getId()));
        }
        return list;
    }

    @Override
    public Integer insert(Reviews reviews) {
        return newDAO.insert(reviews);
    }

    @Override
    public Integer insertLike(Integer customerID, Integer reviewsID) {
        return newDAO.insertLike(customerID, reviewsID);
    }

    @Override
    public Boolean removeLike(Integer customerID, Integer reviewsID) {
        return newDAO.removeLike(customerID, reviewsID);
    }

    @Override
    public Integer getTotalLikeOfReviews(Integer reviewsID) {
        return newDAO.getTotalLikeOfReviews(reviewsID);
    }

    @Override
    public Boolean findOne(Integer customerID, Integer reviewsID) {
        return newDAO.findOne(customerID, reviewsID);
    }
}
