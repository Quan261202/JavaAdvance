package com.halloween.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.halloween.model.CartItem;
import com.halloween.model.CustomerDetail;
import com.halloween.model.Products;
import com.halloween.service.ICartItemService;
import com.halloween.service.ICustomerService;
import com.halloween.service.IOrderService;
import com.halloween.service.IProductService;
import com.halloween.service.impl.CartItemService;
import com.halloween.service.impl.CustomerService;
import com.halloween.service.impl.OrderService;
import com.halloween.service.impl.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.Serial;
import java.util.List;

@WebServlet(name = "CartAPI", value = "/CartAPI")
public class CartAPI extends HttpServlet {

    @Serial
	private static final long serialVersionUID = 1L;
    private static final IProductService productService = new ProductService();
    private static final IOrderService orderService = new OrderService();
    private static final ICustomerService customerService = new CustomerService();
    private static final ICartItemService cartItemService = new CartItemService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        String username = request.getSession().getAttribute("name").toString();
        Integer customerID = customerService.getCustomerID(username);
        Integer orderID = orderService.getOrderID(customerID);
        List<CartItem> cartItems = cartItemService.loadCart(orderID);
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(cartItems);
        mapper.writeValue(response.getOutputStream(), json);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        if (request.getSession().getAttribute("name") == null) {
            response.setContentType("text/html");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        } else {
            Integer productID = Integer.parseInt(request.getParameter("id"));
            String username = request.getSession().getAttribute("name").toString();
            Integer customerID = customerService.getCustomerID(username);
            Integer orderID = orderService.getOrderID(customerID);
            List<Products> lists = productService.getAllItems();
            List<CartItem> cartItems = cartItemService.saveCart(customerID, orderID, productID, lists);
            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(cartItems);
            mapper.writeValue(response.getOutputStream(), json);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Integer productID = Integer.parseInt(request.getParameter("id"));
        Integer amount = Integer.parseInt(request.getParameter("amount"));
        CustomerDetail customerDetail = (CustomerDetail)request.getSession().getAttribute("CUSTOMER");
        Integer orderID = orderService.getOrderID(customerDetail.getCusID());
        if(cartItemService.updateCartItem(amount, productID, orderID))
            mapper.writeValue(response.getOutputStream(), "Update Success");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        String json = request.getParameter("data");
        CustomerDetail customerDetail = (CustomerDetail)request.getSession().getAttribute("CUSTOMER");
        Integer orderID = orderService.getOrderID(customerDetail.getCusID());
        Boolean isSuccess = false;
        if(json.indexOf(',') >= 0)
        {
            String[] cartsID = json.split(",");
            for (String id : cartsID) {
                isSuccess =  cartItemService.removeCartItem(Integer.parseInt(id), orderID);
            }
        }
        else{
            isSuccess =  cartItemService.removeCartItem(Integer.parseInt(json), orderID);
        }

        if(isSuccess) mapper.writeValue(response.getOutputStream(), "Remove Success");
    }
}
