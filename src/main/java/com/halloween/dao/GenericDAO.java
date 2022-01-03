package com.halloween.dao;

import java.util.List;

import com.halloween.mapper.INewMapper;

public interface GenericDAO <T> {
	List<T> query(String sql, INewMapper<T> mapper, Object... params);
	default <G> G getSingleObject(String sql, Integer columnIndex, Class<G> gClass, Object... params) { return null;}
	default <G> List<G> getListObject(String sql, Class<G> gClass, Object... params) { return null;}
	default Boolean updateOrDelete(String sql, Object... params) { return false; }
	Integer insert(String sql, Object... params);
}
