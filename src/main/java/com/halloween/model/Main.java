package com.halloween.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.halloween.service.impl.OrderService;

import java.sql.SQLException;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, JsonProcessingException {
//        SendMail.senMail("quan2020606122@gmail.com", "Shopping Halloween", "THANK YOU");
        OrderService orderService = new OrderService();
        orderService.updateOrder(101, orderService.addDays(new Date(), 2));
    }
}
