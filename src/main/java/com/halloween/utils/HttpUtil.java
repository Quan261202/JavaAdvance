package com.halloween.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;

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
			System.out.println(this.value);
			return new ObjectMapper().readValue(this.value, tClass);
		} catch (Exception e) {
//			e.printStackTrace();
		}
		return null;
	}
	
	public static HttpUtil of(BufferedReader reader)
	{
		StringBuilder sb = new StringBuilder();
		String line;
		try {
			while((line = reader.readLine()) != null) sb.append(line);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return new HttpUtil(sb.toString());
	}
}
