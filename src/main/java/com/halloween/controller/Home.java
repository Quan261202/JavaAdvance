package com.halloween.controller;

import com.halloween.service.*;
import com.halloween.service.impl.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Home extends HttpServlet {

    private static final IProductService productService = new ProductService();
    private static final ICategoryService categoryService = new CategoryService();
    private static final IOrderService orderService = new OrderService();
    private static final ICustomerService customerService = new CustomerService();
    private static final ICartItemService cartItemService = new CartItemService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        Map<String, Object> map = new HashMap<>();
        map.put("categoryOne", productService.getThreeItem(1, 0, 3));

    }
}
