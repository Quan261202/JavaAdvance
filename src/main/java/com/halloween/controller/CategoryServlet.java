package com.halloween.controller;

import com.halloween.model.CategoryModel;
import com.halloween.service.impl.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Category")
public class CategoryServlet extends HttpServlet {

    private static final CategoryService CATEGORY_SERVICE = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String categoryID = req.getParameter("categoryID");
        String categoryName = req.getParameter("categoryName");
        String description = req.getParameter("descriptions");
        if (categoryID == null) {
            CATEGORY_SERVICE.insert(new CategoryModel(categoryName, description));
        } else {
            CATEGORY_SERVICE.update(categoryID, categoryName, description);
        }
        resp.sendRedirect("Controller");
    }
}
