package com.halloween.mapper;

import java.sql.ResultSet;
import java.util.Date;
import com.halloween.model.Reviews;

public class ReviewsMapper implements INewMapper<Reviews>{
	@Override
	public Reviews mapRow(ResultSet resultSet) {
		try {
			return new Reviews(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3), new Date(resultSet.getDate(4).getTime()), resultSet.getString(5), resultSet.getInt(6));
		} catch (Exception e) {
			return null;
		}
	}
}
