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

@MultipartConfig
@WebServlet("/Controller")
public class Controller extends HttpServlet {

	@Serial
	private static final long serialVersionUID = 1L;
	
	private static final IProductService productService = new ProductService();
	private static final ICategoryService categoryService = new CategoryService();
	
	public static int page = 0;
	public static int offset = 0;
	public static int limit = 3;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String a = request.getParameter("a");
		int categoryID = 1;
		int count;
		double totalPage;
		List<CategoryModel> categoryModels = categoryService.getAllCategoryProduct();
		if (request.getParameter("a") == null) {
			count = productService.countProductByCategory(categoryID);
			totalPage = Math.ceil(count / 3.0);
			List<Products> list;
			list = productService.getAllByCategory(categoryID);
			request.setAttribute("product", list);
			request.setAttribute("map", categoryModels);
			request.setAttribute("categoryProduct", categoryID);
			request.setAttribute("page", page);
			request.setAttribute("totalPage", totalPage);
			request.getRequestDispatcher("admin.jsp").forward(request, response);
		} else {
			if (request.getParameter("id") != null) categoryID = Integer.parseInt(request.getParameter("id"));
			count = productService.countProductByCategory(categoryID);
			totalPage = Math.ceil(count / 3.0);
			switch (a) {
				case "Category" -> {
					if (request.getParameter("id") != null) {
						if (count < limit) limit = count;
						List<Products> list = productService.getThreeItem(categoryID, limit, offset);
						request.setAttribute("product", list);
						page = 0;
						offset = 0;
						limit = 3;
						request.setAttribute("categoryProduct", categoryID);
						request.setAttribute("page", page);
						request.setAttribute("totalPage", totalPage);
						request.setAttribute("map", categoryModels);
						request.getRequestDispatcher("admin.jsp").forward(request, response);
					}
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
					Products products = new Products(id, name, price, "image", 1, quantity, categoryID);
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
				case "next" -> {
					offset += 3;
					if (offset >= count) offset = 0;
					List<Products> list = productService.getThreeItem(categoryID, limit, offset);
					page++;
					if (page == totalPage) {
						page = 0;
					}
					request.setAttribute("product", list);
					request.setAttribute("categoryProduct", categoryID);
					request.setAttribute("page", page);
					request.setAttribute("totalPage", totalPage);
					request.setAttribute("map", categoryModels);
					request.getRequestDispatcher("admin.jsp").forward(request, response);
				}
				case "previous" -> {
					if (page > 0 && offset >= 3) {
						page--;
						offset -= 3;
					} else if (page == 0) {
						page = (int) totalPage - 1;
						offset = page * 3;
					}
					List<Products> list = productService.getThreeItem(categoryID, limit, offset);
					request.setAttribute("product", list);
					request.setAttribute("categoryProduct", categoryID);
					request.setAttribute("page", page);
					request.setAttribute("map", categoryModels);
					request.setAttribute("totalPage", totalPage);
					request.getRequestDispatcher("admin.jsp").forward(request, response);
				}
				default -> {
				}
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
}
