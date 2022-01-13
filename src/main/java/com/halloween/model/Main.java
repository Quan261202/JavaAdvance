package com.halloween.model;

import com.halloween.mail.SendMail;
import java.sql.SQLException;

public class Main {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		final int age = 10;
		System.out.println(age);
		SendMail.senMail("quan2020606122@gmail.com", "Shopping Halloween", "THANK YOU");
	}
}
