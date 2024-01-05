package com.halloween.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.halloween.model.Products;
import com.halloween.service.ICategoryService;
import com.halloween.service.IProductService;
import com.halloween.service.impl.CategoryService;
import com.halloween.service.impl.ProductService;
import com.halloween.utils.HttpUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serial;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@WebServlet("/api/product")
public class ProductAPI extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    private static final IProductService iProductService = new ProductService();
    private static final ICategoryService iCategoryService = new CategoryService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String fileName = "products_" + new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(new Date()).replaceAll("_", "/");

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");

        String categoryId = request.getParameter("categoryId");

        String query = request.getParameter("query");

        List<Products> products = iProductService.getAllItemsByQuery(categoryId, query);

        try (OutputStream outputStream = response.getOutputStream(); Workbook workbook = new XSSFWorkbook()) {

            Sheet sheet = workbook.createSheet("products");

            Row header = sheet.createRow(0);

            Font font = workbook.createFont();
            font.setFontName("Arial");
            font.setFontHeightInPoints((short) 16);
            font.setBold(true);

            Field[] fields = products.get(0).getClass().getDeclaredFields();

            for (int j = 0; j < fields.length; ++j) {
                Cell headerCell = header.createCell(j);
                headerCell.setCellValue(fields[j].getName());
            }

            for (int i = 0; i < products.size(); ++i) {
                Row row = sheet.createRow(i + 1);
                for (int j = 0; j < fields.length; ++j) {
                    fields[j].setAccessible(true);
                    Object value = fields[j].get(products.get(i));
                    Cell cell = row.createCell(j);
                    cell.setCellValue(Objects.isNull(value) ? "" : value.toString());
                }
            }
            workbook.write(outputStream);

        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        ObjectMapper mapper = new ObjectMapper();
        Products product = Objects.requireNonNull(HttpUtil.of(request.getReader())).toModel(Products.class);
        product.setUrlImage("image/" + product.getUrlImage());
        if (iProductService.save(product) != null) {
            mapper.writeValue(response.getOutputStream(), "Add Success");
        } else {
            mapper.writeValue(response.getOutputStream(), "Error");
        }
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        ObjectMapper mapper = new ObjectMapper();
        Products product = Objects.requireNonNull(HttpUtil.of(request.getReader())).toModel(Products.class);
        if (Objects.nonNull(product.getUrlImage()) && !product.getUrlImage().contains("image/")) {
            product.setUrlImage("image/" + product.getUrlImage());
        }
        if (iProductService.update(product, product.getProductID())) {
            mapper.writeValue(response.getOutputStream(), "Update Success");
        }
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        HttpUtil httpUtil = HttpUtil.of(request.getReader());
        assert httpUtil != null;
        String json = httpUtil.getValue();
        boolean isSuccess = false;
        if (json.contains("categoryID")) {
            Integer categoryID = Integer.parseInt(json.substring(json.indexOf(':') + 2, json.indexOf('}') - 1));
            isSuccess = iProductService.deleteByCategoryID(categoryID);
            iCategoryService.delete(categoryID);
        } else {
            if (json.indexOf('[') > 0) {
                String[] integers = json.substring(json.indexOf('[') + 1, json.indexOf(']')).split(",");
                for (String id : integers) {
                    isSuccess = iProductService.delete(Integer.parseInt(id));
                }
            } else {
                Integer id = Integer.parseInt(json.substring(json.indexOf(':') + 1, json.indexOf('}')));
                isSuccess = iProductService.delete(id);
            }
        }
        if (isSuccess) mapper.writeValue(response.getOutputStream(), json.contains("categoryID") ? "Delete Category Success" : "Delete Product Success");
        else mapper.writeValue(response.getOutputStream(), "Error");
    }
}
