<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Update Product</h1>
	<form action = "Controller" method = "get">
		<input type="hidden" name = "id" value="${p.productID}">
		<div><input type="text" name="name" value="${p.productName}"></div>
		<div><input type="text" name="price" value="${p.price}"></div>
		<div><img src="${p.urlImage}" width="150px" height="auto" name="image"></div>
		<div><input type="text" name="quantity" value="${p.quantity}"></div>
		<div><input type="text" name="status" value="1"></div>
		<input type="submit" name = "a" value="Update">
	</form>
</body>
</html>