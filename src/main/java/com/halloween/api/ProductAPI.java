package com.halloween.api;

import java.io.IOException;
import java.io.Serial;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.halloween.model.Products;
import com.halloween.service.ICategoryService;
import com.halloween.service.IProductService;
import com.halloween.service.impl.CategoryService;
import com.halloween.service.impl.ProductService;
import com.halloween.utils.HttpUtil;

@WebServlet("/api/product")
public class ProductAPI extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    private static final IProductService iProductService = new ProductService();
    private static final ICategoryService iCategoryService = new CategoryService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        List<Integer> integers = iCategoryService.getAllCategoryID();
        HashMap<String, List<Products>> data = new HashMap<>();
        for(Integer categoryID : integers)
            data.put("category" + categoryID, iProductService.getAllByCategory(categoryID));
        String jsons = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(data);
        mapper.writeValue(response.getOutputStream(), jsons);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        ObjectMapper mapper = new ObjectMapper();
        Products product = HttpUtil.of(request.getReader()).toModel(Products.class);
        if (iProductService.save(product) != null) {
            mapper.writeValue(response.getOutputStream(), "{Add Success}");
        } else {
            mapper.writeValue(response.getOutputStream(), "{Error}");
        }
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        ObjectMapper mapper = new ObjectMapper();
        Products product = HttpUtil.of(request.getReader()).toModel(Products.class);
        if (iProductService.update(product, product.getProductID())) {
            mapper.writeValue(response.getOutputStream(), "{Update Success}");
        }
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        HttpUtil httpUtil = HttpUtil.of(request.getReader());
        String json = httpUtil.getValue();
        Boolean isSuccess = false;
        if (json.indexOf('[') > 0) {
            String[] integers = json.substring(json.indexOf('[') + 1, json.indexOf(']')).split(",");
            for (String id : integers) {
                isSuccess = iProductService.delete(Integer.parseInt(id));
            }
        } else {
            Integer id = Integer.parseInt(json.substring(json.indexOf(':') + 1, json.indexOf('}')));
            isSuccess = iProductService.delete(id);
        }
        if (isSuccess) mapper.writeValue(response.getOutputStream(), "Delete Success");
        else mapper.writeValue(response.getOutputStream(), "Error");
    }
}
