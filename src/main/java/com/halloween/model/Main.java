package com.halloween.model;

import java.sql.SQLException;

import com.halloween.mail.SendMail;

public class Main {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		SendMail.senMail("quan2020606122@gmail.com", "Shopping Halloween", "THANK YOU");
	}
}
