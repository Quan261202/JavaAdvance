package com.halloween.controller;

import java.io.IOException;

import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.halloween.model.CategoryModel;
import com.halloween.model.Products;
import com.halloween.service.ICategoryService;
import com.halloween.service.IProductService;
import com.halloween.service.impl.CategoryService;
import com.halloween.service.impl.ProductService;

@MultipartConfig
@WebServlet("/Controller")
public class Controller extends HttpServlet {
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
		int count = 0;
		double totalPage = 0;
		List<CategoryModel> categoryModels = categoryService.getAllCategoryProduct();
		if (request.getParameter("a") == null) {
			count = productService.countProductByCategory(categoryID);
			totalPage = Math.ceil(count / 3.0);
			List<Products> list = null;
			list = productService.getThreeItem(categoryID, limit, offset);
			request.setAttribute("product", list);
			request.setAttribute("map", categoryModels);
			request.setAttribute("categoryProduct", categoryID);
			request.setAttribute("page", page);
			request.setAttribute("totalPage", totalPage);
			request.getRequestDispatcher("admin.jsp").forward(request, response);
		} else {
			if (request.getParameter("id") != null) {
				categoryID = Integer.parseInt(request.getParameter("id"));
			}
			count = productService.countProductByCategory(categoryID);
			totalPage = Math.ceil(count / 3.0);
			switch (a) {
			case "Category": {
				if (request.getParameter("id") != null) {
					if(count < limit) limit = count;
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
				break;
			}
			case "displayCreate": {
				request.setAttribute("category", categoryModels);
				request.getRequestDispatcher("create.jsp").forward(request, response);
				break;
			}
			case "displayUpdate": {
				int id = Integer.parseInt(request.getParameter("productID"));
				Products products = productService.findOne(id);
				request.setAttribute("category", categoryModels);
				request.setAttribute("product", products);
				request.getRequestDispatcher("create.jsp").forward(request, response);
				break;
			}
			case "Update": {
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
				break;
			}
			case "Delete": {
				int productID = Integer.parseInt(request.getParameter("id"));
				if(productService.delete(productID))
					response.setStatus(200);
				break;
			}
			case "next": {
				offset += 3;
				if(offset >= count) offset = 0;
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
				break;
			}
			case "previous": {
				if (page > 0) {
					page--;
					offset -= 3;
				} else if (page == 0) {
					page = (int)totalPage - 1;
					offset = page * 3;
				}
				List<Products> list = productService.getThreeItem(categoryID, limit, offset);
				request.setAttribute("product", list);
				request.setAttribute("categoryProduct", categoryID);
				request.setAttribute("page", page);
				request.setAttribute("map", categoryModels);
				request.setAttribute("totalPage", totalPage);
				request.getRequestDispatcher("admin.jsp").forward(request, response);
				break;
			}
			default:
				break;
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String a = request.getParameter("a");
		switch (a) {
		case "Create": {
			String name = request.getParameter("productName");
			double price = Double.parseDouble(request.getParameter("price"));
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			int status = Integer.parseInt(request.getParameter("status"));
			Part part = request.getPart("image");
			int category = Integer.parseInt(request.getParameter("categoryID"));
			String realPart = request.getServletContext().getRealPath("/image");
			String fileName = Path.of(part.getSubmittedFileName()).getFileName().toString();
			if (!Files.exists(Path.of(realPart))) {
				Files.createDirectory(Path.of(realPart));
			}
			part.write(realPart + "/" + fileName);
			Products products = new Products(0, name, price, "images/" + fileName, status, quantity, category);
			Integer productID = productService.save(products);
			if (productID != null)
				response.sendRedirect("Controller");
			break;
		}
		case "Search": {
			String name = request.getParameter("key");
			int categoryID = 0;
			if (request.getParameter("categoryID") == null) {
				categoryID = 1;
			} else {
				categoryID = Integer.parseInt(request.getParameter("categoryID"));
			}
			List<Products> lists = productService.findByName(name, categoryID);
			PrintWriter out = response.getWriter();
			String html = "<tr>\r\n" + "                    	<th>Image</th>\r\n"
					+ "                    	<th>ProductName</th>\r\n" + "                    	<th>Price</th>\r\n"
					+ "                    	<th>Quantity</th>\r\n" + "                    	<th>Status</th>\r\n"
					+ "                	</tr>";
			for (Products products : lists) {
				html += "<tr class=\"item\">\r\n" + "                    		<td><img src='" + products.getUrlImage()
						+ "' width=\"100px\" height=\"90px\" alt=\"\"></td>\r\n" + "                    		<td><p>"
						+ products.getProductName() + "</p></td>\r\n"
						+ "                    		<td><p class=\"price\">" + products.getPrice() + "</p></td>\r\n"
						+ "                    		<td><p class=\"quantity\">" + products.getQuantity()
						+ "</p></td>\r\n" + "                    		<td><p class=\"status\">" + products.getStatus()
						+ "</p></td>\r\n" + "							<td>\r\n"
						+ "                        		<div class=\"update\">\r\n"
						+ "                        			<a href= '" + products.getProductID()
						+ "' style=\"display: block;\" class=\"delete-product\" data-id='" + products.getProductID()
						+ "'>delete:" + products.getProductID() + "</a>\r\n"
						+ "                            		<a href=\"Controller?a=displayUpdate&productID="
						+ products.getProductID() + "\" style=\"display: block;\">update</a>\r\n"
						+ "                        		</div>\r\n" + "                    		</td>" + "</tr>";
			}
			out.println(html);
			break;
		}
		default:
			break;
		}
	}
}
