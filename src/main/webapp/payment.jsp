<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css" />
<link rel="stylesheet" href="css/payment.css">
<title>Thông tin thanh toán</title>
</head>

<body>
	<div class="header">
		<div class="logo">
			<img src="image/ghost-castle (1).png" alt="">
		</div>
		<div class="status">
			<div class="title">
				<span>Đăng Nhập</span> <span>Địa chỉ giao hàng</span> <span>Thanh
					toán và đặt mua</span>
			</div>
			<div class="processbar">
				<div class="percent"></div>
			</div>
		</div>
		<div class="contact">
			<a href=""><em class="fas fa-phone animate__tada"></em></a>
			<div>
				<p>0375081107</p>
				<span>8h-21h, cả T7 và CN</span>
			</div>
		</div>
	</div>
	<div class="message">
		<p>Do ảnh hưởng của dịch Covid-19, một số khu vực có thể nhận hàng
			chậm hơn dự kiến. Bên mình đang nỗ lực giao các đơn hàng trong thời
			gian sớm nhất. Cám ơn sự thông cảm của quý khách!</p>
	</div>
	<div class="payments">
		<h1>1. Chọn hình thức giao hàng</h1>
		<div class="delivery">
			<div class="delivery-main">
				<div class="delivery-form">
					<div class="tooltip">
						<a href=""><span></span></a> <a href=""><span>TIKI</span><span>FAST</span></a>
						<a href=""><span>Giao hàng tiết kiệm</span></a> <a href="">-22k<span>FREESHIP
						</span><span>+</span></a>
					</div>
					<div class="order">
						<div>
							<c:forEach items="${cartItems}" var="item">
								<div style="display: flex; align-items: center; justify-content: flex-start; margin-bottom: 5px" class="">
									<img class="urlImage" style="margin-right: 10px" src="${item.urlImage}" alt="${item.productID}:${item.quantity}">
									<div class="name">
										<p>${item.productName}</p>
										<span>SL: x${item.quantity}</span> <span
											style="margin-left: 20px !important;">${item.price * item.quantity}</span>
									</div>
								</div>
							</c:forEach>
						</div>
						<div class="ship">
							<p>${date}</p>
							<p>Được giao bởi TikiNOW Smart Logistics (giao từ Hà Nội)</p>
							<a href=""><span>TIKI</span><span>FAST</span></a>
						</div>
						<div class="free">
							<p>Phí giao hàng</p>
							<span>1$</span>
						</div>
					</div>
				</div>
				<h1 style="margin-top: 5px">2.Chọn hình thức thanh toán</h1>
				<div class="container">
					<div class="container-item">
						<label><input type="radio" name="pay" id=""><span></span></label>
						<a href=""> <img src="https://frontend.tikicdn.com/_desktop-next/static/img/icons/checkout/icon-payment-method-cod.svg" alt="">
						</a>
						<p>Thanh toán khi nhận hàng</p>
					</div>
					<div class="container-item">
						<label><input type="radio" name="pay" id=""><span></span></label>
						<a href=""> <img style="width: 30px; height: auto;"
							src="https://salt.tikicdn.com/ts/upload/76/80/08/62e0faf2af2869ba93da5f79a9dc4c4b.png"
							alt="">
						</a>
						<p>Thanh toán bằng ViettelPay</p>
					</div>
				</div>
				<div class="btn-pay">
					<a href="OrderSuccess?id=">Đặt hàng</a>
				</div>
			</div>
			<div class="customer-info">
				<div class="customer-info-item">
					<div>
						<span>Địa chỉ giao hàng</span> <a>Sửa</a>
					</div>
					<h3>${customerInfo.name}</h3>
					<p style="margin-bottom: 8px;">${customerInfo.address}</p>
					<p>Điện thoại: ${customerInfo.phone}</p>
				</div>
				<div class="customer-info-item">
					<div>
						<span>Shop khuyến mại</span> <span>Có thể chọn 1 trong hai</span>
					</div>
					<a class="free" href=""><em class="fas fa-percentage"></em>Chọn
						hoặc nhập khuyến mãi</a>
				</div>
				<div class="customer-info-item order">
					<div>
						<div class="amount">
							<p>Đơn hàng</p>
							<span>1 sản phẩm<a href="">Xem thông tin</a></span>
							<span class="drop"></span>
						</div>
						<a>Sửa</a>
					</div>
					<div class="detail">
						<c:set var="total" value="0"></c:set>
						<c:forEach items="${cartItems}" var="item">
							<div class="detail-one">
								<span>${item.quantity}x <a href="">${item.productName}</a></span><span>${item.quantity*item.price}</span>
							</div>
							<c:set var="total" value="${total + item.quantity*item.price}"></c:set>
						<p>Cung cấp bởi Tiki Trading</p>
						</c:forEach>
					</div>
					<div class="price">
						<span>Tạm tính</span> <span>${total}$</span>
					</div>
					<div class="price">
						<span><em class="fas fa-truck"></em> Phí vận chuyển</span> <span>1.0$</span>
					</div>
					<div class="price">
						<span>Miễn phí</span> <span style="color: #fd6872">-${free}$</span>
					</div>
					<div class="price bottom">
						<span>Thành tiền</span> <span>${total + 1 - free}$</span>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script type="text/javascript" src="js/payment.js"></script>
</body>
</html>