<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:url var="urlAPI" value="/api/product"></c:url>
<c:url var="urlHome" value="/Controller"></c:url>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<meta http-equiv="X-UA-Compatible" content="ie=edge" />
		<title>Products</title>
		<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:400,700" />
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
		<link rel="stylesheet" href="css/bootstrap.min.css">
		<link rel="stylesheet" href="css/addProduct.css">
		<style>
			.tm-block {
				padding: 0 !important;
			}

			body {
				overflow-x: auto;
			}

			.container {
				max-width: 1450px;
			}

		</style>
	</head>

	<body id="">
		<nav class="navbar navbar-expand-xl">
			<div class="container h-100">
				<a class="navbar-brand" href="#">
					<h1 class="tm-site-title mb-0">Product Admin</h1>
				</a>
				<a class="nav-link d-block" href="LogOut">
					Admin, <b>Logout</b>
				</a>
			</div>
		</nav>
		<div class="container mt-5">
			<div class="input-group mb-4">
				<div class="form-outline" data-mdb-input-init>
					<input placeholder="search ..." type="search" id="search-value" class="form-control" />
				</div>
				<button type="button" id="search" class="btn btn-primary" data-mdb-ripple-init>
					<i class="fas fa-search"></i>
				</button>

				<button type="button" id="export" class="btn btn-primary ml-3" data-mdb-ripple-init>
					<i class="far fa-solid fa-file"></i>
				</button>

			</div>
			<div class="row tm-content-row">
				<div class="col-sm-12 col-md-12 col-lg-9 col-xl-9 tm-block-col">
					<div class="tm-bg-primary-dark tm-block tm-block-products">
						<div class="tm-product-table-container">
							<table align="center" class="table table-hover tm-table-small tm-product-table">
								<thead>
									<tr align="center">
										<th scope="col">&nbsp;</th>
										<th scope="col">ID</th>
										<th scope="col">IMAGE</th>
										<th scope="col">PRODUCT NAME</th>
										<th scope="col">PRICE</th>
										<th scope="col">QUANTITY</th>
										<th scope="col">CREATED DATE</th>
										<th scope="col">DELETED DATE</th>
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
											<th scope="row">${item.productID}</th>
											<td><img src="${item.urlImage}" width="80px" height="70px" alt=""></td>
											<td><p>${item.productName}</p></td>
											<td><p class="price">${item.price}</p></td>
											<td><p class="quantity">${item.quantity}</p></td>
											<td><p class="quantity"><fmt:formatDate value="${item.createdDate}" pattern="dd/MM/yyyy HH:mm:ss" /></p></td>
											<td><p class="quantity"><fmt:formatDate value="${item.deletedDate}" pattern="dd/MM/yyyy HH:mm:ss" /></p></td>
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
							<a style="font-size: 30px;color: #e17009" href="Controller?page=${page - 1}&id=${categoryProduct}">
								<i class="fas fa-caret-square-left"></i>
							</a>
							<a style="font-size: 30px;color: #e17009" href="Controller?page=${page + 1}&id=${categoryProduct}">
								<i class="fas fa-caret-square-right"></i>
							</a>
						</div>
					</div>
				</div>
				<div class="col-sm-12 col-md-12 col-lg-3 col-xl-3 tm-block-col">
					<div class="tm-bg-primary-dark tm-block tm-block-product-categories">
						<h2 class="tm-block-title">Product Categories</h2>
						<div class="tm-product-table-container">
							<table class="table tm-table-small tm-product-table">
								<tbody>
									<c:forEach items="${map}" var="item">
										<tr>
											<td class="tm-product-name"><a href="Controller?a=Category&id=${item.categoryID}">${item.categoryName}</a></td>
											<td class="text-center">
												<a href="Controller?a=displayUpdateCategory&categoryID=${item.categoryID}" class="tm-product-delete-link">
													<i class="fas fa-pen-square tm-product-delete-icon"></i>
												</a>
												<a href="#${item.categoryID}" class="tm-product-delete-link deleteCategory" id="deleteCategory">
													<i class="far fa-trash-alt tm-product-delete-icon"></i>
												</a>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<!-- table container -->
						<a href="Controller?a=DisplayCreateCategory" class="btn btn-primary btn-block text-uppercase mb-3">
							Add new category
						</a>
					</div>
				</div>
			</div>
		</div>

		<script src="JQUERY/jquery-3.3.1.min.js"></script>
		<script src="JQUERY/bootstrap.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
		<script>
			$(document).ready(()=>{

				const buildSearchQuery = (query) => {
					const url = window.location.href
					let newUrl = url
					if(url.includes("query")) {
						newUrl =  url.substring(0, url.indexOf("query"))
					}
					if(newUrl.includes("?")) {
						newUrl += "&query=" + query
					} else {
						newUrl += "?query=" + query
					}
					return newUrl
				}


				$('#search').on('click', () => {
					const value = $('#search-value').val()
					window.location.href =buildSearchQuery(value)
				})

				$('#export').on('click', () => {
					const query = new URLSearchParams(window.location.search)
					let url = '${urlAPI}';
					if(query.get("id") != null) {
						url += '?categoryId=' + query.get("id") + '&';
					}
					if (query.get("query") != null) {
						url += '?query=' + query.get("query") + '&';
					}
					window.location.href = url.substring(0, url.length - 1);
				})


				// delete category
				const deleteCategory = $('.deleteCategory')
				for(let i = 0; i < deleteCategory.length; ++i)
				{
					// delete from the products.
					$(deleteCategory[i]).on('click', (e)=>{
						e.preventDefault()
						const categoryID = $(deleteCategory[i]).attr('href')[1]
						const object = {"categoryID": categoryID}
						$.ajax({
							method: "delete",
							contentType: "application/json",
							url: '${urlAPI}',
							data: JSON.stringify(object),
							success: (data)=>{
								alert(data)
								window.location.href = "${urlHome}"
							}
						})
					})
				}
				// delete product
				const deleteProduct = $('.tm-product-delete-link.deleteProduct');
				console.log(deleteProduct)
				for(let i = 0; i < deleteProduct.length; ++i)
				{
					$(deleteProduct[i]).on('click', (e)=>{
						e.preventDefault()
						const id = $(deleteProduct[i]).data('id');
						const object = {"id": id}
						$.ajax({
							type: 'DELETE',
							contentType: 'application/json',
							url: '${urlAPI}',
							data: JSON.stringify(object),
							success: (data)=>{
								alert(data)
								window.location.href = "${urlHome}"
							}
						})
					})
				}
				$('[data-toggle="tooltip"]').tooltip();
			})
		</script>
	</body>
</html>