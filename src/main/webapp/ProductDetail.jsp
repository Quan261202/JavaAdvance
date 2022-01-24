<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:url var="urlAPI" value="/CartAPI"></c:url>
<c:url var="urlHome" value="/loadProducts"></c:url>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link rel="stylesheet" href="css/productDetail.css">
</head>

<body>
<div class="header"></div>
<div class="container">
    <div class="container-product">
        <div class="image">
            <div class="main-img">
                <img src="${product.urlImage}" alt="">
            </div>
            <div class="image-item">
                <img src="image/lenovo_20sm0015us_tb_15_iil_i7_1065g7_1558131.jpg" alt="">
                <img src="image/lenovo_20sm0015us_tb_15_iil_i7_1065g7_1558131.jpg" alt="">
                <img src="image/lenovo_20sm0015us_tb_15_iil_i7_1065g7_1558131.jpg" alt="">
                <img src="image/lenovo_20sm0015us_tb_15_iil_i7_1065g7_1558131.jpg" alt="">
            </div>
        </div>
        <div class="product-detail">
            <h3><span>Yêu thích</span> ${product.productName}</h3>
            <div class="vote">
                <a href="">
                    <span>${percentage}</span>
                    <c:if test="${countStar > 0}">
                        <c:forEach begin="1" end="${countStar}" step="1">
                            <i class="fas fa-star"></i>
                        </c:forEach>
                    </c:if>
                    <c:if test="${isOdd > 0}">
                        <i class="fas fa-star-half-alt"></i>
                    </c:if>
                </a>
                <a href=""><span>${total}<c:if test="${total >= 1000 }">k</c:if></span> Đánh giá</a>
                <a href=""><span>${sold}<c:if test="${sold >= 1000 }">k</c:if></span> Đã bán</a>
            </div>
            <div class="price">
                <p><span class="last-price">$${product.price + 2}</span> <span> ${product.price}$</span><span class="free">46% GIẢM</span></p>
            </div>
            <div class="ship">
                <span style="margin-right: 10px;">Vận chuyển</span>
                <div class="ship-detail">
                    <div class="content">
                        <img src="image/1cdd37339544d858f4d0ade5723cd477.png" alt="">
                    </div>
                    <div class="ship-address">
                        <div class="ship-address-detail">
                            <span style="opacity: .6;">Vận chuyển tới</span>
                            <span class="address">${CUSTOMER.address}</span>
                            <span id="icon" class="icon"></span>
                            <div class="chose-address">
                                <div class="search">
                                    <input type="hidden" id="customerID" name="" value="${CUSTOMER.customerID}">
                                    <input placeholder="Tìm" type="text" name="" id="search">
                                </div>
                                <div id="address" class="drop-address"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="quantity">
                <span class="quantity-title">Số lượng</span>
                <div class="update">
                    <span id="sub" style="cursor: pointer">-</span>
                    <span id="amount">1</span>
                    <span id="add" style="cursor: pointer">+</span>
                </div>
                <span style="opacity: .7;">${product.quantity} sản phẩm có sẵn</span>
            </div>
            <div class="function">
                <a href="" class="add-to-cart" id="add-to-cart" data-toggle="tooltip"
                   title="Thêm vào giỏ hàng!" data-id="${product.productID}"><em class="fas fa-cart-plus"></em>Thêm vào giỏ hàng</a>
                <a href="" class="buy-now">Mua ngay</a>
            </div>
        </div>
    </div>
    <div class="reviews">
        <h1>Đánh giá sản phẩm</h1>
        <div class="vote">
            <div class="vote-left">
                <p><span>5</span> trên 5</p>
                <a href="">
                    <em class="fas fa-star"></em>
                    <em class="fas fa-star"></em>
                    <em class="fas fa-star"></em>
                    <em class="fas fa-star"></em>
                    <em class="fas fa-star"></em>
                </a>
            </div>
            <div class="vote-right">
                <a href="">Tất cả (<span>12</span>)</a>
                <a href="">5 sao (<span>12</span>)</a>
                <a href="">4 sao (<span>12</span>)</a>
                <a href="">3 sao (<span>12</span>)</a>
                <a href="">2 sao (<span>12</span>)</a>
                <a href="">1 sao (<span>12</span>)</a>
            </div>
        </div>
        <c:forEach items="${reviews}" var="item">
            <div class="reviews-detail">
                <div class="reviews-detail-avatar">
                    <img src="${item.avatar}" alt="">
                </div>
                <div class="reviews-detail-content">
                    <p class="name">${item.customerName}</p>
                    <span class="star">
                        <c:forEach begin="1" end="${item.vote}" step="1">
                            <em class="fas fa-star"></em>
                        </c:forEach>
                    </span>
                    <p class="content">${item.content}</p>
                    <div class="images">
                        <img src="${item.urlImage}" alt="">
                        <img src="${item.urlImage}" alt="">
                        <img src="${item.urlImage}" alt="">
                    </div>
                    <p class="date" style="padding: 10px 0px;font-weight: 550">${item.getReviewsDate()}</p>
                    <div class="like-item">
                        <span class="like" <c:if test="${item.isLike() == true}">style="color: #ee4d2d;cursor: pointer"</c:if> <c:if test="${item.isLike() == false}">style="color: #ccc;cursor: pointer"</c:if>><i id="like" class="fas fa-thumbs-up"></i></span>
                        <span style="color: #ccc" class="total-like">
                            <c:if test="${item.totalLike > 0}">${item.totalLike}</c:if>
                            <c:if test="${item.totalLike < 1}"> ? Hữu ích</c:if>
                        </span>
                        <input type="hidden" id="ID" name="" value="${item.id}">
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="js/productDetail.js"></script>
<script type="text/javascript">
    $(document).ready(() => {
        $('#add-to-cart').on('click', (e) => {
            e.preventDefault()
            const quantity = $('#amount').text()
            const id = $('#add-to-cart').data('id')
            const json = {
                'productID': id,
                'quantity': quantity
            }
            $.ajax({
                type: 'POST',
                contentType: 'application/json',
                url: '${urlAPI}',
                data: JSON.stringify(json),
                success: (data) => {
                    alert(data)
                    window.location.href = '${urlHome}'
                }
            })
        })
    })
</script>
</body>
</html>