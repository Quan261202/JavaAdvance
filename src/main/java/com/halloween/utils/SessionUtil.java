package com.halloween.utils;

import javax.servlet.http.HttpSession;

public class SessionUtil {
	private static SessionUtil sessionUtil = null;
	
	public static SessionUtil checkSessionUtil()
	{
		if(sessionUtil == null)
			sessionUtil = new SessionUtil();
		return sessionUtil;
	}
	
	public void putValue(HttpSession session, String key, Object value) {
		session.setAttribute(key, value);
	}
	
	public void getValue(HttpSession session, String key) {
		session.getAttribute(key);
	}
	
	public void removeValue(HttpSession session, String key) {
		session.removeAttribute(key);
	}
}
