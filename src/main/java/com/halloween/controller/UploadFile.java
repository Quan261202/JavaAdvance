package com.halloween.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.Serial;
import java.nio.file.Files;
import java.nio.file.Path;


@MultipartConfig
@WebServlet(urlPatterns = {"/fileUpload"})
public class UploadFile extends HttpServlet {

    @Serial
	private static final long serialVersionUID = -2194633441438412303L;

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        Part part = request.getPart("file");
        String realPart = request.getServletContext().getRealPath("/image");
        String fileName = Path.of(part.getSubmittedFileName()).getFileName().toString();
        Path path = Path.of(realPart);
        if (!Files.exists(path)) {
            Files.createDirectory(path);
        }
        part.write(realPart + File.separator + fileName);
        System.out.println(realPart + File.separator + fileName);
        response.setStatus(200);
    }
}
