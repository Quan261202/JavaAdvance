package com.halloween.dao;

import com.halloween.model.News;

public interface INewsDAO extends GenericDAO<News>{
	Integer save(News news);
}
