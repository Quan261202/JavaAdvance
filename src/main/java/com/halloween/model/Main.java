package com.halloween.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, JsonProcessingException {
//        SendMail.senMail("quan2020606122@gmail.com", "Shopping Halloween", "THANK YOU");
        ObjectMapper mapper = new ObjectMapper();
        String json = "{\"type\":\"province\",\"key\":\"nam địn\",\"name\":\"proName\"}";
        Address address = mapper.readValue(json, Address.class);
        System.out.println(address.getKey());
    }
}
