<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url var="urlAPI" value="/api/product"></c:url>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<meta http-equiv="X-UA-Compatible" content="ie=edge" />
		<title>Product Page - Admin HTML Template</title>
		<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:400,700" />
		<!-- https://fonts.google.com/specimen/Roboto -->
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
		<!-- https://fontawesome.com/ -->
		<link rel="stylesheet" href="css/bootstrap.min.css">
		<!-- https://getbootstrap.com/ -->
		<link rel="stylesheet" href="css/addProduct.css">

	</head>

	<body id="reportsPage">
		<nav class="navbar navbar-expand-xl">
			<div class="container h-100">
				<a class="navbar-brand" href="index.html">
					<h1 class="tm-site-title mb-0">Product Admin</h1>
				</a>
				<a class="nav-link d-block" href="login.html">
					Admin, <b>Logout</b>
				</a>
			</div>
		</nav>
		<div class="container mt-5">
			<div class="row tm-content-row">
				<div class="col-sm-12 col-md-12 col-lg-8 col-xl-8 tm-block-col">
					<div class="tm-bg-primary-dark tm-block tm-block-products">
						<div class="tm-product-table-container">
							<table align="center" class="table table-hover tm-table-small tm-product-table">
								<thead>
									<tr align="center">
										<th scope="col">&nbsp;</th>
										<th scope="col">IMAGE</th>
										<th scope="col">PRODUCT NAME</th>
										<th scope="col">PRICE</th>
										<th scope="col">QUANTITY</th>
										<th colspan="2" scope="col">
											FUNCTIONS
											<a href="Controller?a=displayCreate" style="margin-left: 5px" data-toggle="tooltip" title="Add new product!">
												<i class="fas fa-plus-square tm-product-delete-icon"></i>
											</a>
										</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${product}" var="item">
										<tr align="center" class="item">
											<th scope="row"><input type="checkbox"/></th>
											<td><img src="${item.urlImage}" width="80px" height="70px" alt=""></td>
											<td><p>${item.productName}</p></td>
											<td><p class="price">${item.price}</p></td>
											<td><p class="quantity">${item.quantity}</p></td>
											<td>
												<a data-id="${item.productID}" href="#" class="tm-product-delete-link deleteProduct">
													<i class="far fa-trash-alt tm-product-delete-icon"></i>
												</a>
											</td>
											<td>
												<a href="Controller?a=displayUpdate&productID=${item.productID}" class="tm-product-delete-link">
													<i class="fas fa-pen-square tm-product-delete-icon"></i>
												</a>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<div style="text-align: center" class="paging">
							<b style="font-size: 24px">${page + 1}/${totalPage}</b>
							<a style="font-size: 30px;color: #e17009" href="Controller?a=previous&id=${categoryProduct}">
								<i class="fas fa-caret-square-left"></i>
							</a>
							<a style="font-size: 30px;color: #e17009" href="Controller?a=next&id=${categoryProduct}">
								<i class="fas fa-caret-square-right"></i>
							</a>
						</div>
						<!-- table container -->
					</div>
				</div>
				<div class="col-sm-12 col-md-12 col-lg-4 col-xl-4 tm-block-col">
					<div class="tm-bg-primary-dark tm-block tm-block-product-categories">
						<h2 class="tm-block-title">Product Categories</h2>
						<div class="tm-product-table-container">
							<table class="table tm-table-small tm-product-table">
								<tbody>
									<c:forEach items="${map}" var="item">
										<tr>
											<td class="tm-product-name"><a href="Controller?a=Category&id=${item.categoryID}">${item.categoryName}</a></td>
											<td class="text-center">
												<a href="#" class="tm-product-delete-link">
													<i class="far fa-trash-alt tm-product-delete-icon"></i>
												</a>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<!-- table container -->
						<button class="btn btn-primary btn-block text-uppercase mb-3">
							Add new category
						</button>
					</div>
				</div>
			</div>
		</div>

		<script src="JQUERY/jquery-3.3.1.min.js"></script>
		<!-- https://jquery.com/download/ -->
		<script src="JQUERY/bootstrap.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
		<!-- https://getbootstrap.com/ -->
		<script>
			$(document).ready(()=>{
				$('[data-toggle="tooltip"]').tooltip();

				const deleteProduct = $('.tm-product-delete-link.deleteProduct');
				for(let i = 0; i < deleteProduct.length; ++i)
				{
					$(deleteProduct[i]).on('click', (e)=>{
						e.preventDefault();
						const id = $(deleteProduct[i]).data('id');
						const object = {"id": id}
						$.ajax({
							type: 'DELETE',
							contentType: 'application/json',
							url: '${urlAPI}',
							data: JSON.stringify(object),
							success: (data)=>{
								alert(data)
								window.location.href = "/ShopingHalloween_war_exploded/Controller"
							}
						})
					})
				}
			})
		</script>
	</body>
</html>