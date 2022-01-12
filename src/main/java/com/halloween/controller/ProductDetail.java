package com.halloween.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.halloween.model.Products;
import com.halloween.service.IProductService;
import com.halloween.service.IReviewsService;
import com.halloween.service.impl.ProductService;
import com.halloween.service.impl.ReviewsService;


public class ProductDetail extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final IProductService productService = new ProductService();
	private final IReviewsService reviewsService = new ReviewsService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF8");
        if(request.getParameter("productID") != null)
        {
            Integer productID = Integer.parseInt(request.getParameter("productID"));
            Products product = productService.findOne(productID);
            Double totalVote = reviewsService.getTotalVote(productID);
            Double voteFiveStar = reviewsService.getVoteFiveStart(productID);
            Integer countProductSold = productService.getCountProductSold(productID);
            if(countProductSold == null) request.setAttribute("sold", 0);
            else request.setAttribute("sold", countProductSold);
            Double percentage = getCountStar(totalVote, voteFiveStar);
            request.setAttribute("product", product);
            request.setAttribute("countStar", percentage);
            request.setAttribute("total", totalVote.intValue());
            if(((voteFiveStar / totalVote) % 0.2 != 0) && voteFiveStar.doubleValue() != totalVote.doubleValue())
            {
                request.setAttribute("isOdd", 1);
            }
            request.getRequestDispatcher("ProductDetail.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private double getCountStar(Double totalVote, Double voteFiveStar)
    {
        double percentage = (voteFiveStar / totalVote) * 10;
        if(percentage == 10) return 5;
        else if(percentage < 10 && percentage >= 8) return 4;
        else if(percentage >= 6) return 3;
        else if(percentage >= 4) return 2;
        else if(percentage >= 2) return 1;
        else return 0;
    }
}
