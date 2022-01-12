package com.halloween.dao;

import com.halloween.model.Reviews;

public interface IReviewsDAO extends GenericDAO<Reviews>{
	Double getTotalVote(Integer productID);

    Double getVoteFiveStart(Integer productID);
}
