package com.halloween.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpUtil {
	private String value;

	public HttpUtil(String value) {
		super();
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public <T> T toModel(Class<T> tClass)
	{
		try {
			return new ObjectMapper().readValue(this.value, tClass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public <T> List<T> toListModel(Class<T> tClass)
	{
		List<T> lists = new ArrayList<T>();
		try {
			lists.add(new ObjectMapper().readValue(this.value, tClass));
			return lists;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static HttpUtil of(BufferedReader reader)
	{
		StringBuilder sb = new StringBuilder();
		String line = "";
		try {
			while((line = reader.readLine()) != null) sb.append(line);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return new HttpUtil(sb.toString());
	}
}
