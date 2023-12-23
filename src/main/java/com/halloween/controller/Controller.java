package com.halloween.controller;

import com.halloween.model.CategoryModel;
import com.halloween.model.Products;
import com.halloween.service.ICategoryService;
import com.halloween.service.IProductService;
import com.halloween.service.impl.CategoryService;
import com.halloween.service.impl.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serial;
import java.util.List;
import java.util.Objects;

@MultipartConfig
@WebServlet("/Controller")
public class Controller extends HttpServlet {

	@Serial
	private static final long serialVersionUID = 1L;
	
	private static final IProductService productService = new ProductService();
	private static final ICategoryService categoryService = new CategoryService();
	private static final int ITEM_PER_PAGE = 5;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String a = request.getParameter("a");
		List<CategoryModel> categoryModels = categoryService.getAllCategoryProduct();
		int categoryID = 0;
		if(Objects.nonNull(categoryModels) && !categoryModels.isEmpty()) {
			categoryID = categoryModels.get(0).getCategoryID();
		}
		String query = request.getParameter("query");
		if (request.getParameter("id") != null) categoryID = Integer.parseInt(request.getParameter("id"));
		int count = productService.countProductByCategory(query, categoryID);
		int page = 0;
		int totalPage  = (int) Math.ceil(count / (ITEM_PER_PAGE * 1.0));

		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));

			if(page <= 0) {
				page = totalPage;
			}

			if(page >= totalPage) {
				page = 0;
			}
		}

		if (request.getParameter("a") == null) {
			List<Products> list = productService.getThreeItem(query, categoryID, ITEM_PER_PAGE, page * ITEM_PER_PAGE);
			request.setAttribute("product", list);
			request.setAttribute("map", categoryModels);
			request.setAttribute("categoryProduct", categoryID);
			request.setAttribute("page", page);
			request.setAttribute("totalPage", totalPage);
			request.getRequestDispatcher("admin.jsp").forward(request, response);
		} else {
			switch (a) {
				case "Category" -> {
					List<Products> list = productService.getThreeItem(query, categoryID, ITEM_PER_PAGE, page * ITEM_PER_PAGE);
					request.setAttribute("product", list);
					request.setAttribute("categoryProduct", categoryID);
					request.setAttribute("page", page);
					request.setAttribute("totalPage", totalPage);
					request.setAttribute("map", categoryModels);
					request.getRequestDispatcher("admin.jsp").forward(request, response);
				}
				case "displayCreate" -> {
					request.setAttribute("category", categoryModels);
					request.setAttribute("type", "Add Product");
					request.getRequestDispatcher("create.jsp").forward(request, response);
				}
				case "DisplayCreateCategory" -> {
					request.setAttribute("type", "Add Category");
					request.getRequestDispatcher("CreateCategory.jsp").forward(request, response);
				}
				case "displayUpdate" -> {
					int id = Integer.parseInt(request.getParameter("productID"));
					Products products = productService.findOne(id);
					request.setAttribute("category", categoryModels);
					request.setAttribute("product", products);
					request.setAttribute("type", "Update Product");
					request.getRequestDispatcher("create.jsp").forward(request, response);
				}
				case "Update" -> {
					int id = Integer.parseInt(request.getParameter("id"));
					String name = request.getParameter("name");
					double price = Double.parseDouble(request.getParameter("price"));
					int quantity = Integer.parseInt(request.getParameter("quantity"));
					String urlImage = request.getParameter("urlImage");
					Products products = new Products(id, name, price, urlImage, 1, quantity, categoryID);
					if (productService.update(products, products.getProductID())) {
						response.sendRedirect("Controller");
					} else {
						response.sendRedirect("update.jsp");
					}
				}
				case "Delete" -> {
					int productID = Integer.parseInt(request.getParameter("id"));
					if (productService.delete(productID))
						response.setStatus(200);
				}
				default -> {
				}
			}
		}
	}
}
