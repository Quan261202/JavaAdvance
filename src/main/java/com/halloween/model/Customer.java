package com.halloween.model;

import java.util.List;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.json.JsonObject;
import com.restfb.types.User;

public class Customer {
	private int id;
	private String userName;
	private String password;
	private String email;
	private String urlImage;

	public Customer() {}
	public Customer(int id, String userName, String password, String email) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.email = email;
	}
	public String getUserName() {
		return userName;
	}
	public String getUrlImage() {
		return urlImage;
	}
	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public static Customer getProfileFromFB(String accessToken) {
		FacebookClient client = new DefaultFacebookClient(accessToken, Version.VERSION_3_2);
		User user = client.fetchObject("me", User.class);
		Customer customer = new Customer();
		JsonObject jsonObject = client.fetchObjects(List.of("me"), JsonObject.class, Parameter.with("fields", "id, email, picture"));
		String profile = jsonObject.toString();
		customer.setUserName(user.getName());
		System.out.println(profile);
		customer.setEmail(profile.substring(profile.indexOf("\"email\":") + 9, profile.indexOf("\",\"picture\":")));
		customer.setUrlImage(profile.substring(profile.indexOf("\"url\":") + 7, profile.indexOf("width") - 3));
		return customer;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
