package com.halloween.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.halloween.mail.SendMail;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, JsonProcessingException {
        SendMail.senMail("vuquan261202@gmail.com", "Shopping Halloween", "THANK YOU");
    }
}
