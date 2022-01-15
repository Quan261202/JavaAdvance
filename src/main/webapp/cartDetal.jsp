<%@page import="com.halloween.model.Customer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url var="urlHome" value="CartDetails"></c:url>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Cart Detail</title>
	<link rel="stylesheet" href="css/cartdetails.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
</head>

<body>
	<div class="header"></div>
	<div class="cartdetails">
		<p>GIỎ HÀNG</p>
		<c:set var="amount" value="0"></c:set>
		<c:forEach items="${cart}" var="a">
			<c:set var="amount" value="${amount + 1}"></c:set>
		</c:forEach>
		<div class="container">
			<div class="container-item one">
				<div class="container-item-one">
					<div class="check all">
						<input type="checkbox" name="check" id=""> <label
							for="check">Tất cả (<span class="amount">${amount}</span> sản phẩm)
						</label>
					</div>
					<div class="right">
						<a href="">Đơn giá</a> <a href="">Số lượng</a> <a href="">Thành
							tiền</a> <a href=""><em class="fas fa-trash-alt removeAll"></em></a>
					</div>
				</div>
				<div class="freeship">
                    <div class="processbar-content">
                        <span>......</span>
                        <span>-3$</span>
                        <span>-5$</span>
                    </div>
                    <div class="processbar">
                        <div class="one"></div>
                        <div class="processbar-content">
                            <span>Mua</span>
                            <span>39$</span>
                            <span>100$</span>
                        </div>
                        <p>FREESHIP<span>+</span></p>
                    </div>
                </div>
				<div class="listCartItems">
					<c:forEach items="${cart}" var="item">
						<div class="container-item-two">
							<div class="check">
								<input type="checkbox" name="check" id="check">
								<img src="${item.urlImage}" alt="">
							</div>
							<div class="right">
								<p class="prices">${item.price}<span></span>
								</p>
								<div class="amount">
									<span data-id="${item.productID}" class="sub" style="border-radius: 2px 0px 0 2px;">-</span>
									<span class="quantity">${item.quantity}</span>
									<span data-id="${item.productID}" class="add" style="border-radius: 0 2px 2px 0; border-right: .5px solid black;">+</span>
								</div>
								<p class="money">${item.quantity * item.price}</p>
								<a class="removeCart" data-id="${item.productID}" href="#"><em class="fas fa-trash-alt"></em></a>
							</div>
						</div>
						<c:set var="amount" value="${amount + 1}"></c:set>
					</c:forEach>
				</div>
			</div>
			<div class="container-item">
				<div class="address">
					<div class="address-one">
						<p style="width: 150px !important;">Giao tới</p>
						<c:if test="${customer.address != null}">
							<a href="LoadAddress?address=${customer.address}">Thay đổi</a>
						</c:if>
						<c:if test="${customer.address == null}">
							<a href="LoadAddress">Thay đổi</a>
						</c:if>
					</div>
					<p>
						${customer.firstName} ${customer.lastName } | <span>${customer.phone}</span>
					</p>
					<p class="address-detail">
						<c:if test="${customer.address == null}">
							Vui lòng nhập địa chỉ giao hàng
						</c:if>
						${customer.address}
					</p>
				</div>
				<div class="free">
					<div class="free-one">
						<p>Khuyến Mãi</p>
						<a href="">Có thể chọn 2</a>
					</div>
					<a href=""><em class="fas fa-percentage"></em>Chọn hoặc nhập
						Khuyến mãi</a>
				</div>
				<div class="pay">
					<div class="pay-item">
						<p>Tạm tính</p>
						<p>
							<span class="totalMoney">0</span>$
						</p>
					</div>
					<div class="pay-item">
						<p>Giảm giá</p>
						<p class="frees">
							<span>0</span>$
						</p>
					</div>
					<div class="pay-item">
						<p>Tổng cộng</p>
						<p>
							<span class="lastPrice">Vui lòng chọn sản phẩm</span>
						</p>
					</div>
					<p class="tax">Đã bao gồm VAT nếu có</p>
				</div>
				<div class="btn">
					<a class="btn-pay" href="PayMent?id=">Mua hàng(<span class="count"></span>)</a>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script type="text/javascript" src="js/choseCart.js"></script>
	<script>
		$(document).ready(()=>{
			const checkAllCarts = $('.container-item-two .check input');
			$('.removeAll').on('click', (e)=>{
				e.preventDefault();
				const cartsChecked = $('.removeCart');
				const dataID = []
				for(let i = 0; i < cartsChecked.length; ++i)
				{
					if($(checkAllCarts[i]).is(":checked"))
					{
						dataID.push($(cartsChecked[i]).data('id'))
					}
				}
				$.ajax({
				 	method: 'DELETE',
				 	url: 'CartAPI?data=' + dataID,
				 	success: (data)=>{
				 		alert(data)
						window.location.href = "${urlHome}"
				 	}
				})
			})
		})
	</script>
</body>
</html>