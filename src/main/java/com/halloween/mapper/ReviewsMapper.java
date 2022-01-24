package com.halloween.mapper;

import com.halloween.model.Reviews;

import java.sql.ResultSet;
import java.util.Date;

public class ReviewsMapper implements INewMapper<Reviews>{
	@Override
	public Reviews mapRow(ResultSet resultSet) {
		try {
			Reviews reviews = new Reviews();
			reviews.setAvatar(resultSet.getString(6));
			reviews.setCustomerName(resultSet.getString(5));
			reviews.setContent(resultSet.getString(3));
			reviews.setUrlImage(resultSet.getString(1));
			reviews.setVote(resultSet.getInt(4));
			reviews.setId(resultSet.getInt(7));
			reviews.setReviewsDate(new Date(resultSet.getDate(2).getTime()));
			return reviews;
		} catch (Exception e) {
			return null;
		}
	}
}
