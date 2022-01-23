<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>My Order</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<link rel="stylesheet" href="css/myOrder.css">
</head>
<body>
	<div class="header"></div>
	<div class="main">
		<div class="main-left">
			<ul>
				<li><img src="icons/resume.png" alt=""><a href="">Tài
						khoản của tôi</a></li>
				<li><img src="icons/package.png" alt=""><a href="">Đơn
						mua</a></li>
				<li><img src="icons/bell.png" alt=""><a href="">Thông
						báo</a></li>
				<li><img src="icons/log-out.png" alt=""><a href="">Đăng
						xuất</a></li>
			</ul>
		</div>
		<div class="main-right">
			<ul>
				<li><a href="">Tất cả</a></li>
				<li><a href="">Chờ xác nhận</a></li>
				<li><a href="">Chờ lấy hàng</a></li>
				<li><a href="">Đang giao<span id="count" style="color: #ee4d2d">(${count})</span></a></li>
				<li><a href="">Đã giao</a></li>
				<li><a href="">Đã huỷ</a></li>
			</ul>
			<div class="search">
				<em class="fas fa-search"></em> <input type="text" name="search"
					placeholder="tìm kiếm theo tên shop ID hoặc tên sản phẩm...">
			</div>
			<div class="product" id="product">
				<div class="store">
					<span><em class="fas fa-store"></em>doanducminh79</span> <a href=""><em
						class="fas fa-comment-alt"></em>chat</a> <a href=""><em
						class="fas fa-store"></em>Xem shop</a>
				</div>
				<c:forEach items="${map}" var="item">
					<c:set var="total" value="0"></c:set>
					<c:forEach items="${item.value}" var="cart">
						<div class="product-item">
							<img src="${cart.urlImage}" alt="">
							<div class="product-detail">
								<p>${cart.productName}</p>
								<span>x${cart.quantity}</span>
							</div>
							<p  class="price">${cart.quantity * cart.price}</p>
							<c:set var="total" value="${total + cart.quantity * cart.price}"></c:set>
						</div>
					</c:forEach>
					<div class="pay">
						<span><img src="icons/gross.png" alt=""><a href=""></a>Tổng
							số tiền: <span>$ </span><span>${total}</span></span>
						<div class="pay-function">
							<c:if test="${status == 1}">
								<a href="">Chờ</a>
								<a href="">Liên hệ người bán</a>
								<a href="">Hủy đơn hàng</a>
							</c:if>
							<c:if test="${status == 3}">
								<a style="background-color: #ee4d2d !important; opacity: 1"
									href="">Mua lại</a>
								<a href="">Liên hệ người bán</a>
								<a href="">Chi tiết đơn hàng</a>
							</c:if>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="js/myOrder.js"></script>	
</body>
</html>