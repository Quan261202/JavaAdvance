package com.halloween.service;

import com.halloween.model.Reviews;

import java.util.List;

public interface IReviewsService {
	Double getTotalVote(Integer productID);

    Double getVoteFiveStart(Integer productID);

    List<Reviews> getReviewsOfProduct(Integer productID, Integer vote);

    List<Reviews> getReviewsOfProduct(Integer productID, Integer customerID, Object... param);

    Integer insert(Reviews reviews);

    Integer insertLike(Integer customerID, Integer reviewsID);

    Boolean removeLike(Integer customerID, Integer reviewsID);

    Integer getTotalLikeOfReviews(Integer reviewsID);

    Boolean findOne(Integer customerID, Integer reviewsID);
}
