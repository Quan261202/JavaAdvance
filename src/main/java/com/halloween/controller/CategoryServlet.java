package com.halloween.controller;

import com.halloween.model.CategoryModel;
import com.halloween.service.impl.CategoryService;
import com.halloween.utils.HttpUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet("/Category")
public class CategoryServlet extends HttpServlet {

    private static final CategoryService CATEGORY_SERVICE = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CategoryModel categoryModel = Objects.requireNonNull(HttpUtil.of(req.getReader())).toModel(CategoryModel.class);
        CATEGORY_SERVICE.insert(categoryModel);

    }
}
