package com.halloween.dao.impl;

import com.halloween.dao.INewsDAO;
import com.halloween.model.News;

public class NewsDAO extends AbstractDAO<News> implements INewsDAO{
	@Override
	public Integer save(News news) {
		String sql = "insert into news(adminID, categoryID, title, shortDescription, content) values(?, ?, ?, ?, ?)";
		return insert(sql, news.getAdminID()
								    , news.getCategoryID()
								    , news.getTitle()
								    , news.getShortDescription()
								    , news.getContent());
	}
}
