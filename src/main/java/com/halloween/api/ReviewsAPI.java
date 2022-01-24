package com.halloween.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.halloween.model.Reviews;
import com.halloween.service.IReviewsService;
import com.halloween.service.impl.ReviewsService;
import com.halloween.utils.HttpUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/api/reviews"})
public class ReviewsAPI extends HttpServlet {

    private static final IReviewsService reviewsService = new ReviewsService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        HttpUtil util = HttpUtil.of(request.getReader());
        if(util != null)
        {
            Reviews reviews = util.toModel(Reviews.class);
            if(reviewsService.insert(reviews) != null)
            {
                mapper.writeValue(response.getOutputStream(), "Reviews Success");
            }
            else mapper.writeValue(response.getOutputStream(), "Reviews Error");
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        HttpUtil util = HttpUtil.of(request.getReader());
        if(util != null)
        {
            Reviews reviews = util.toModel(Reviews.class);
            if(reviewsService.findOne(reviews.getCustomerID(), reviews.getId()))
            {
                if(reviewsService.removeLike(reviews.getCustomerID(), reviews.getId())){
                    mapper.writeValue(response.getOutputStream(), reviewsService.getTotalLikeOfReviews(reviews.getId()));
                }
            }
            else{
                if(reviewsService.insertLike(reviews.getCustomerID(), reviews.getId()) != null)
                {
                    mapper.writeValue(response.getOutputStream(), reviewsService.getTotalLikeOfReviews(reviews.getId()));
                }
            }
        }
    }
}
