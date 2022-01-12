package com.halloween.service;

public interface IReviewsService {
	Double getTotalVote(Integer productID);

    Double getVoteFiveStart(Integer productID);
}
