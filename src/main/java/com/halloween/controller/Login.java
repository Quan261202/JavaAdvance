package com.halloween.controller;

import java.io.IOException;
import java.io.Serial;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.halloween.model.Customer;
import com.halloween.service.ICustomerDetailService;
import com.halloween.service.ICustomerService;
import com.halloween.service.impl.CustomerDetailService;
import com.halloween.service.impl.CustomerService;
import com.halloween.utils.SessionUtil;

@WebServlet(urlPatterns = {"/LoginServlet"})
public class Login extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    private static final ICustomerService customerService = new CustomerService();
    private static final ICustomerDetailService CUSTOMER_DETAIL_SERVICE = new CustomerDetailService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("loginFB") != null) {
            String name = request.getParameter("loginFB");
            Customer customer = Customer.getProfileFromFB(name);
            HttpSession session = request.getSession();
            session.setAttribute("name", customer.getUserName());
            session.setMaxInactiveInterval(60 * 60 * 24);
            request.setAttribute("img", customer.getUrlImage());
            request.getRequestDispatcher("loadProducts").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        String username = request.getParameter("name");
        String password = request.getParameter("password");
        Integer customerID = customerService.checkLogin(username, password);
        if (customerID != null) {
            HttpSession session = request.getSession();
            session.setAttribute("name", username);
            session.setMaxInactiveInterval(60 * 60 * 24);
            if (!customerService.isAdmin(customerID)) {
                SessionUtil.checkSessionUtil().putValue(session, "CUSTOMER", CUSTOMER_DETAIL_SERVICE.getInfoCustomer(customerID));
                response.sendRedirect("loadProducts");
            } else {
                response.sendRedirect("HomeAdmin");
            }
        } else {
            request.setAttribute("error", "invalid");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        }
    }
}
