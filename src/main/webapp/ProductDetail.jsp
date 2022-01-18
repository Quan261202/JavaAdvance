<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:url var="urlAPI" value="/CartAPI"></c:url>
<c:url var="urlHome" value="/loadProducts"></c:url>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product Detail</title>
    <link rel="stylesheet" href="css/productDetail.css">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
</head>
<body>

<div class="container">
    <div class="card">
        <div id="shopping">
            <i class="fas fa-cart-plus"><sup id="items-added"></sup></i>
        </div>
        <div class="images">
            <h2>${product.productName}</h2>
            <div class="slider">
                <img id="big-image" src="${product.urlImage}" alt="">
            </div>
        </div>
        <div class="infos">
            <h1>${product.productName}</h1>
            <div class="reviews">
                <c:if test="${countStar > 0}">
                    <c:forEach begin="1" end="${countStar}" step="1">
                        <i class="fas fa-star"></i>
                    </c:forEach>
                </c:if>
                <c:if test="${isOdd > 0}">
                    <i class="fas fa-star-half-alt"></i>
                </c:if>
                | ${total}<c:if test="${total >= 1000 }">k</c:if><span style="color: #808080; font-size: 16px"> Lượt đánh
						giá</span> | ${sold}
                <c:if test="${sold >= 1000 }">k</c:if>
                <span style="color: #808080; font-size: 16px">Đã bán</span>
            </div>
            <div class="price">
                <h3>${product.price}$</h3>
                <h3>${product.price + 2}$</h3>
            </div>
            <div id="more-infos">
                <h5 class="choose">Description</h5>
                <h5 class="choose">Basic Info</h5>
                <h5 class="choose">Caliber</h5>
            </div>
            <div id="info-content">
                <p class="paragraph" style="display: block;">${product.shortDescription}</p>
                <p class="paragraph" style="display: none;">
                    CALIBER DUW 2002 manual <br> MOVEMENT HEIGHT 3.6 mm <br>
                    DIAMETER 32.6 by 22.6 mm <br> POWER RESERVE up to 84 hours <br>
                    JEWELS 23
                </p>
                <p class="paragraph" style="display: none;">Lorem Repellendus
                    ullam odit placeat, non rem eaque. ipsum dolor sit amet
                    consectetur adipisicing elit. Repellendus Repellendus ullam odit
                    placeat, non rem eaque.ullam odit placeat, non rem eaque.</p>
            </div>
            <div class="quantity">
                <h3>QUANTITY</h3>
                <input type="number" name="items" id="counter" min="1" value="1">
            </div>
            <div class="buttons">
                <a id="add-to-cart" class="addCart" data-toggle="tooltip"
                   title="Thêm vào giỏ hàng!" data-id="${product.productID}"><i
                        class="fas fa-shopping-cart"></i>Add To Cart</a>
                <button>BUY NOW</button>
            </div>
        </div>
    </div>
</div>
<script src="js/productDetail.js"></script>
<script type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript">
    $(document).ready(() => {
        $('#add-to-cart').on('click', (e) => {
            e.preventDefault();
            const quantity = $('#counter').val()
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
