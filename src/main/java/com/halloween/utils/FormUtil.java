package com.halloween.utils;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

public class FormUtil {
	@SuppressWarnings("deprecation")
	public static <T> T toModel(Class<T> tClass, HttpServletRequest request)
	{
		T model = null;
		try {
			model = tClass.newInstance();
			BeanUtils.populate(model, request.getParameterMap());
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return model;
	}
}
