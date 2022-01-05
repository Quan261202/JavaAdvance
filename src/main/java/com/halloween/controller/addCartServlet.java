package com.halloween.controller;

import java.io.IOException;
import java.io.Serial;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.halloween.model.CartItem;
import com.halloween.model.Products;
import com.halloween.service.ICartItemService;
import com.halloween.service.ICustomerService;
import com.halloween.service.IOrderService;
import com.halloween.service.IProductService;
import com.halloween.service.impl.CartItemService;
import com.halloween.service.impl.CustomerService;
import com.halloween.service.impl.OrderService;
import com.halloween.service.impl.ProductService;

public class addCartServlet extends HttpServlet {

    @Serial
    private static final long serialVersionUID = 1L;

    private static final IProductService productService = new ProductService();
    private static final IOrderService orderService = new OrderService();
    private static final ICustomerService customerService = new CustomerService();
    private static final ICartItemService cartItemService = new CartItemService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        ObjectMapper mapper = new ObjectMapper();
        HttpSession session = request.getSession();
        if (session.getAttribute("name") == null) {
            response.setContentType("text/html");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        } else {
            int productID = Integer.parseInt(request.getParameter("id"));
            String username = session.getAttribute("name").toString();
            int customerID = customerService.getCustomerID(username);
            int orderID = orderService.getOrderID(customerID);
            List<Products> lists = productService.getAllItems();
            List<CartItem> cartItems = cartItemService.saveCart(customerID, orderID, productID, lists);
            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(cartItems);
            mapper.writeValue(response.getOutputStream(), json);
        }
    }
}
