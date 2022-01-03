package com.halloween.filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "AuthenticationFilter", urlPatterns = {"/*"})
public class AuthenticationFilter implements Filter {
    public void init(FilterConfig config) {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        request.setCharacterEncoding("UTF-8");
        if(request.getRequestURI().contains("/HomeAdmin"))
        {
            if(request.getSession().getAttribute("CUSTOMER") == null)
            {
                chain.doFilter(request, response);
            }
            else {
                response.sendRedirect("Login.jsp");
            }
        }
        else{
            chain.doFilter(request, response);
        }
    }
}
