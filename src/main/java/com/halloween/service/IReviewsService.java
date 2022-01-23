package com.halloween.service;

import com.halloween.model.Reviews;

import java.util.List;

public interface IReviewsService {
	Double getTotalVote(Integer productID);

    Double getVoteFiveStart(Integer productID);

    List<Reviews> getReviewsOfProduct(Integer productID, Integer vote);

    List<Reviews> getReviewsOfProduct(Integer productID);

    Integer insert(Reviews reviews);

    Boolean updateTotalLike(Integer id, char operator);
}
