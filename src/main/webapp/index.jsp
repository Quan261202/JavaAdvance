<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home</title>
    <link rel="stylesheet" href="https://unpkg.com/aos@next/dist/aos.css" />

    <link rel="stylesheet" href="https://unpkg.com/swiper@7/swiper-bundle.min.css" />

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link rel="stylesheet" href="css/style.css">
    <style type="text/css">
		.pagination{
			display: flex;
			align-items: center;
			justify-content: center;
		}
		.pagination a {
  			color: white;
  			float: left;
  			padding: 5px 16px;
  			text-decoration: none;
  			font-size: 20px;
  			border: .5px #ccc solid;
		}
		.pagination :nth-child(1){
			border-bottom-left-radius: 5px;
			border-top-left-radius: 5px;
		}

		.pagination :last-child{
			border-bottom-right-radius: 5px;
			border-top-right-radius: 5px;
		}

		.pagination a.active{
			background-color: #ef7819;
			color: white;
		}

		.pagination a:hover {
			background-color: #ef7819;
			color: white;
		}
    </style>
</head>
<body>
    <header class="header active">
        <a href="" class="logo"><em class="fas fa-ghost"></em>logo</a>
        <nav class="navbar">
            <a href="" class="active">Home</a>
            <a href="#product">Products</a>
            <a href="#about">About</a>
            <a href="#arrival">Arrivals</a>
            <a href="#contact">Contact</a>
            <a href="#blog">Blogs</a>
        </nav>
        <div class="icons">
			<div id="menu-btn" class="fas fa-bars"></div>
			<div id="search-btn" class="fas fa-search"></div>
			<a id="cart" class="fas fa-shopping-cart">
				<c:if test="${count > 0}">
					<span class="message">${count}</span>
				</c:if>
			</a>
			<c:set var="class" value="fas fa-user"></c:set>
			<c:if test="${img != null}">
				<c:set var="class" value="null"></c:set>
			</c:if>
            <a href="Login.jsp" id="user" class="<c:if test="${empty name}">fas fa-user</c:if>">
				<c:choose>
					<c:when test="${img != null}">
						<img style="width: 30px;height: 30px;border-radius: 50%" alt="" src="${img}">
						<c:set var="check" value="true"></c:set>
					</c:when>
					<c:when test="${name != null}">
                        <img style="width: 40px;height: 40px;border-radius: 50%;transform: translateY(10%)" alt="" 
                        src="<c:if test="${not empty CUSTOMER }">${CUSTOMER.avatar}</c:if>">
						<c:set var="check" value="true"></c:set>
					</c:when>
				</c:choose>
			</a>
			<c:if test="${check}">
				<a href="#" id="out" class="fas fa-angle-down"></a>
			</c:if>
		</div>
        <div class="form-search">
            <input type="text" id="search" placeholder="search here .....">
            <label for="search" class="fas fa-search"></label>
        </div>
        <div class="dropdown">
        	<a href="myOrder.jsp"><em class="fas fa-scroll"></em>Đơn hàng của tôi</a>
        	<a href=""><em class="fas fa-envelope"></em>Thông báo của tôi</a>
        	<a href="editProfile.jsp"><em class="fas fa-user"></em>Tài khoản của tôi</a>
        	<a href="LogOut"><em class="fas fa-sign-out-alt"></em>Đăng xuất</a>
    	</div>
        <div class="cart">
        	<c:set var="total" value="0"></c:set>
			<c:forEach items="${cartItems}" var="cart">
				<div class="item">
					<div class="image">
						<img src="${cart.urlImage}" alt="" width="80px" height="60px">
					</div>
					<div class="detail">
						<h3>${cart.productName}</h3>
						<p class="prices">${cart.quantity}:${cart.quantity * cart.price}</p>
						<c:set var="total" value="${total + cart.quantity * cart.price}"></c:set>
					</div>
					<a href="#" class="removeCart fas fa-times" data-id="${cart.productID}"></a>
				</div>
			</c:forEach>
			<h3 class="price">
				Total:<span>$${total}</span>
			</h3>
			<a href="CartDetails" class="btn">Check Cart</a>
		</div>
    </header>
    <section class="menu-home" id="head">
        <div class="slider">
            <div class="box active" style="background: url('image/home-slider-1.jpg');">
                <div class="content">
                    <h3>Happy Halloween</h3>
                    <p>special Discount</p>
                    <button class="btn">Shop now</button>
                </div>
                <div class="left item">
                    <a class="fas fa-chevron-left"></a>
                </div>
                <div class="right item">
                    <a class="fas fa-chevron-right"></a>
                </div>
            </div>
        </div>
    </section>
    <section class="products" id="product">
        <h3 class="heading">Our<span> ${nameFour}</span></h3>
        <div class="items product">
        	<c:forEach items="${listFour}" var="item">
        		<div class="item">
						<div class="icons">
							<a class="fas fa-shopping-cart addCart" data-id="${item.productID}"></a>
							<a href="ProductDetail?productID=${item.productID}" class="fas fa-eye"></a>
							<a href="" class="fas fa-share"></a>
						</div>
						<div class="image">
							<img src="${item.urlImage}" alt="">
						</div>
						<div class="content">
							<h3>${item.productName}</h3>
							<h3>
								${item.price}<span> $95.99</span>
							</h3>
						</div>
					</div>
        	</c:forEach>
        </div>
        <div class="pagination">${pagination}</div>
    </section>
    <section class="about" id="about" style="background: url('image/about-bt.jpg'); background-position: center;">
        <div class="content">
            <h3>ABOUT US</h3>
            <p>
                Lorem Ipsum Dolor Sit Amet Consectetur Adipisicing Elit. Magni, Adipisci Neque! Veritatis Laboriosam
                Odio Corporis, Quo Fugit Architecto. Veritatis In Dicta Qui Odit Soluta Sequi Ipsam Facere Eum Numquam!
                Obcaecati.
            </p>
            <button class="btn">
                Read More
            </button>
        </div>
    </section>
    <section class="products" id="arrival">
        <h3 class="heading">Our <span> ${nameTwo }</span></h3>
        <div class="items arrivals">
            <c:forEach items="${listTwo}" var="item">
        		<div class="item">
						<div class="icons">
							<a class="fas fa-shopping-cart addCart" data-toggle="tooltip" title="Thêm vào giỏ hàng!" data-id="${item.productID}"></a>
                            <a href="ProductDetail?productID=${item.productID}" class="fas fa-eye"></a>
							<a href="" class="fas fa-share"></a>
						</div>
						<div class="image">
							<img src="${item.urlImage}" alt="">
						</div>
						<div class="content">
							<h3>${item.productName}</h3>
							<h3>
								${item.price}<span> $95.99</span>
							</h3>
						</div>
					</div>
        	</c:forEach>
        </div>
        <div class="pagination">${pagination1}</div>
    </section>
    <section class="contact" id="contact">
        <h1>
            Contact<span> Us</span>
        </h1>
        <div class="row">
            <iframe title="" class="map" src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3723.4736630784155!2d105.7329181141579!3d21.053735992302798!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31345457e292d5bf%3A0x20ac91c94d74439a!2zVHLGsOG7nW5nIMSQ4bqhaSBo4buNYyBDw7RuZyBuZ2hp4buHcCBIw6AgTuG7mWk!5e0!3m2!1svi!2s!4v1633955166105!5m2!1svi!2s" allowfullscreen="" loading="lazy"></iframe>
            <form action="">
                <h3>Tell Us Something!</h3>
                <input type="text" name="name" id="name" style="--delay:.3s" placeholder="your name">
                <input type="text" name="mail" id="mail" style="--delay:.4s" placeholder="your email">
                <input type="number" name="number" style="--delay:.5s" placeholder="your number">
                <label>
                    <textarea name="message" style="--delay:.6s" cols="30" rows="4" placeholder="your message"></textarea>
                </label>
                <button class="btn" style="--delay:.7s">Send Message</button>
            </form>
        </div>
    </section>
    <section class="blogs" id="blog">
        <h1>Our<span> Blogs</span></h1>
        <div class="blog-items">
            <div class="item one">
                <div class="img">
                    <img src="image/blog-1.jpg" alt="">
                </div>
                <div class="content">
                    <h3>
                        Believe In The Magic Of Halloween.
                    </h3>
                    <p>
                        Lorem, Ipsum Dolor Sit Amet Consectetur Adipisicing Elit. Earum, Harum!
                    </p>
                    <a href="" class="btn">Read More</a>
                    <div class="icons">
                        <a href=""><em class="fas fa-calendar"></em> 1st May, 20201</a>
                        <a href=""><em class="fas fa-user"></em> By Admin</a>
                    </div>
                </div>
            </div>
            <div class="item two">
                <div class="img">
                    <img src="image/blog-2.jpg" alt="">
                </div>
                <div class="content">
                    <h3>
                        Believe In The Magic Of Halloween.
                    </h3>
                    <p>
                        Lorem, Ipsum Dolor Sit Amet Consectetur Adipisicing Elit. Earum, Harum!
                    </p>
                    <a href="" class="btn">read More</a>
                    <div class="icons">
                        <a href=""><em class="fas fa-calendar"></em> 1st May, 20201</a>
                        <a href=""><em class="fas fa-user"></em> By Admin</a>
                    </div>
                </div>
            </div>
            <div class="item three">
                <div class="img">
                    <img src="image/blog-3.jpg" alt="">
                </div>
                <div class="content">
                    <h3>
                        Believe In The Magic Of Halloween.
                    </h3>
                    <p>
                        Lorem, Ipsum Dolor Sit Amet Consectetur Adipisicing Elit. Earum, Harum!
                    </p>
                    <a href="" class="btn">Read More</a>
                    <div class="icons">
                        <a href=""><em class="fas fa-calendar"></em> 1st May, 20201</a>
                        <a href=""><em class="fas fa-user"></em> By Admin</a>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <section class="footer">
        <div class="container">
            <div class="box-contain left">
                <h3>Our Branches</h3>
                <a href=""><em class="fas fa-map-marker-alt"></em>Inda</a>
                <a href=""><em class="fas fa-map-marker-alt"></em>USA</a>
                <a href=""><em class="fas fa-map-marker-alt"></em>Russia</a>
                <a href=""><em class="fas fa-map-marker-alt"></em>France</a>
                <a href=""><em class="fas fa-map-marker-alt"></em>Japan</a>
            </div>
            <div class="box-contain left">
                <h3>Quick Links</h3>
                <a href="#head"><em class="fas fa-arrow-right"></em>Home</a>
                <a href="#head"><em class="fas fa-arrow-right"></em>Products</a>
                <a href="#head"><em class="fas fa-arrow-right"></em>About</a>
                <a href="#head"><em class="fas fa-arrow-right"></em>Arrivals</a>
                <a href="#head"><em class="fas fa-arrow-right"></em>Contact</a>
                <a href="#head"><em class="fas fa-arrow-right"></em>Blogs</a>
            </div>
            <div class="box-contain">
                <h3>Contact Us</h3>
                <a href=""><em class="fas fa-phone"></em> +123-456-7890</a>
                <a href=""><em class="fas fa-phone"></em> +111-222-3333</a>
                <a href=""><em class="fas fa-envelope"></em> quan2020606122@gmail.com</a>
                <a href=""><em class="fas fa-map-marker-alt"></em> Mumbai, India - 400104</a>
            </div>
            <div class="box-contain">
                <h3>Contact Us</h3>
                <a href=""><em class="fab fa-facebook-f"></em> Facebook</a>
                <a href=""><em class="fab fa-twitter"></em> Twitter</a>
                <a href=""><em class="fab fa-instagram"></em> Instagram</a>
                <a href=""><em class="fab fa-linkedin"></em> Linkedin</a>
                <a href=""><em class="fab fa-pinterest"></em> Pinterest</a>
            </div>
        </div>
        <div class="creadit">
            created by <span>mr. Cày Code Dạo</span> | All Rights Reserved
        </div>
    </section>
    <script src="./JQUERY/jquery-3.6.0.min.js"></script>
    <script src="js/script.js"></script>
    <script type="text/javascript" src="js/removeCart.js"></script>
    <script type="text/javascript" src="js/loadMore.js"></script>
</body>
</html>