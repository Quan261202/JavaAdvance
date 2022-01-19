package com.halloween.model;

import com.halloween.dao.impl.ReviewsDAO;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        SendMail.senMail("quan2020606122@gmail.com", "Shopping Halloween", "THANK YOU");
        List<Reviews> list = new ReviewsDAO().getReviewsOfProduct(100, 5);
        for (Reviews reviews : list) {
            System.out.println(reviews);
        }
    }
}
