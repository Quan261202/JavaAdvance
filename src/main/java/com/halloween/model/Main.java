package com.halloween.model;

import com.halloween.mail.SendMail;
import java.sql.SQLException;


public class Main {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		//CustomerDetail customerDetail = new CustomerDetailService().getInfoCustomer(2);
		SendMail.senMail("quan2020606122@gmail.com", "Shopping Halloween", "THANK YOU");
	}
}
