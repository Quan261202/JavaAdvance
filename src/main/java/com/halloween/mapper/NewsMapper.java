package com.halloween.mapper;

import java.sql.ResultSet;
import java.util.Date;

import com.halloween.model.News;

public class NewsMapper implements INewMapper<News> {
	@Override
	public News mapRow(ResultSet resultSet) {
		try {
			return new News(resultSet.getInt(1), resultSet.getInt(2), new Date(resultSet.getDate(3).getTime()), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6));
		} catch (Exception e) {
			return null;
		}
	}
}
