package com.halloween.mapper;

import java.sql.ResultSet;

public interface INewMapper <T>{
	public T mapRow(ResultSet resultSet);
}
