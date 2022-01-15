<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url var="url" value="/UpdateAddress"></c:url>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link rel="stylesheet" href="css/changeAddress.css">
    <title>Change Address</title>
</head>
<body>
	
    <div class="header">
        <div class="logo">
            <img src="image/ghost-castle (1).png" alt="">
        </div>
        <div class="status">
            <div class="title">
                <span>Đăng Nhập</span>
                <span>Địa chỉ giao hàng</span>
                <span>Thanh toán và đặt mua</span>
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
    <div class="container">
        <h3>2. Địa chỉ giao hàng</h3>
        <p>Chọn địa chỉ giao hàng có sẵn bên dưới:</p>
        <div class="address">
            <div class="address-name">
                <h3><c:if test="${not empty CUSTOMER}">
                    ${CUSTOMER.getFullName()}
                </c:if></h3>
                <p style="border: none !important;color: black;">Mặc định</p>
            </div>
            <p class="content-add">
                <c:if test="${not empty CUSTOMER}">
                    ${CUSTOMER.address}
                </c:if>
            </p>
            <p>Điện thoại: <c:if test="${not empty CUSTOMER}">
                ${CUSTOMER.phone}
            </c:if></p>
            <a href="">Giao đến địa chỉ này</a>
            <a class="change" href="">Sửa</a>
        </div>
    </div>
    <p class="noname">Bạn muốn giao hàng đến địa chỉ khác? <a href="">Thêm địa chỉ giao hàng mới</a></p>
    <div class="changeAddress">
        <form class="form" id="form" action="">
            <div class="" id="name">
                <label class="labels">Name</label>
                <div>
                	<input type="hidden" name = "customerID" value="${CUSTOMER.customerID}">
                	<input type="text" name = "firstName" class="form-control" placeholder="first name" value="${CUSTOMER.firstName }">
					<input type="text" name = "lastName" class="form-control" value="${CUSTOMER.lastName }" placeholder="last name">
				</div>
            </div>
            <div class="item two">
                <label for="">Điện thoại di động</label>
                <input class="phone" type="text" name="phone" placeholder="enter your ..." value="<c:if test="${not empty CUSTOMER}">${CUSTOMER.phone}</c:if>">
            </div>
            <div class="item two">
                <label for="">Email</label>
                <input class="phone" type="text" name="email" placeholder="enter your email" value="<c:if test="${not empty CUSTOMER}">${CUSTOMER.email}</c:if>">
            </div>
            <div class="item two">
                <label for="">Tỉnh/Thành phố</label>
                <select class="province" name="" id="province" style="width: 200px;">
                	<c:forEach items="${province}" var="pro">
                		<option value="${pro}" <c:if test="${CUSTOMER.address.contains(pro)}">selected="selected"</c:if>>${pro}</option>
                	</c:forEach>
                </select>
            </div>

            <div class="item two">
                <label for="">Quận/Huyện</label>
                <select class="district" name="" id="district" style="width: 200px;">
                	<c:forEach items="${district}" var="dis">
                		<option value="${dis}" <c:if test="${CUSTOMER.address.contains(dis)}">selected="selected"</c:if>>${dis}</option>
                	</c:forEach>
                </select>
            </div>

            <div class="item two">
                <label for="">Phường/Xã</label>
                <select class="ghost" name="" id="ward" style="width: 200px;">
                	<c:forEach items="${ward}" var="wa">
                		<option value="${wa}" <c:if test="${CUSTOMER.address.contains(wa)}">selected="selected"</c:if>>${wa}</option>
                	</c:forEach>
                </select>
            </div>

            <div class="item two">
                <label for="">Địa chỉ cụ thể</label>
                <textarea class="item-addressDetails" name="address" id="address" cols="30" rows="3"></textarea>
            </div>
            <div class="radio">
                <label for="type">Loại địa chỉ</label>
                <input type="radio" name="type" id="type">
                <label for="type">Nhà riêng/chung cư</label>
                <input type="radio" name="type">
                <label for="type">Cơ quan/ công ty</label>
            </div>
            <div class="bottom">
                <input type="checkbox" name="" id="">
                <label for="">Sử dụng địa chỉ này làm mặc định</label>
            </div>
            <div class="btn">
                <a class="cancel-address" href="">Huỷ bỏ</a>
                <a class="update-address" href="">Cập nhật</a>
            </div>
        </form>
    </div>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="js/changeAddress.js"></script>
</body>
</html>