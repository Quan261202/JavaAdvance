var btnSearch = document.querySelector('.header .icons #search-btn');
btnSearch.onclick = () =>{
    document.querySelector('.header .form-search').classList.toggle('active');
    document.querySelector('.header .navbar').classList.remove('active');
    document.querySelector('.header .cart').classList.remove('active');
    document.querySelector('.header .login').classList.remove('active');
}

var menu = document.querySelector("#menu-btn");
menu.onclick = ()=>{
    document.querySelector('.header .navbar').classList.toggle('active');
    document.querySelector('.header .form-search').classList.remove('active');
    document.querySelector('.header .cart').classList.remove('active');
    document.querySelector('.header .login').classList.remove('active');
}

var element = {
    one: "Up To 30% Of",
    two: "On All Customer",
    three: "Limited Offer",
    four: "Don't Miss It"
}

var i = 2;

function changeContent(index)
{
    if(index == 2){
        $(".box .content h3").text(`${element.one}`);
        $(".box .content p").text(`${element.two}`);
    }
    else if(index == 3)
    {
        $(".box .content h3").text(`${element.three}`);
        $(".box .content p").text(`${element.four}`);
    }
    else{
        $(".box .content h3").text("Happy Halloween");
        $(".box .content p").text("Special Discount");
    }
}

var section = document.querySelectorAll('section');
var nav = document.querySelectorAll('.header .navbar a');

window.onscroll = ()=>{
    section.forEach(sec=>{
        let top = window.scrollY;
        let height = sec.offsetHeight;
        let offset = sec.offsetTop - 300;
        let id = sec.getAttribute('id');
        if(top > offset && top < offset + height)
        {
            nav.forEach(link=>{
                link.classList.remove('active');
                document.querySelector('.header .navbar a[href*='+id+']').classList.add('active');
            });
        }
    })
}

var cart = document.querySelector('.header .icons #cart');
cart.onclick = ()=>{
    document.querySelector('.header .cart').classList.toggle('active');
    document.querySelector('.header .form-search').classList.remove('active');
    document.querySelector('.header .login').classList.remove('active');
    document.querySelector('.header .navbar').classList.remove('active');
}

var user = document.querySelector('.header .icons #user');
user.onclick = ()=>{
    document.querySelector('.header .login').classList.toggle('active');
    document.querySelector('.header .form-search').classList.remove('active');
    document.querySelector('.header .cart').classList.remove('active');
    document.querySelector('.header .navbar').classList.remove('active');
}

$(document).ready(function(){
    setInterval(function(){
        $(".box").removeClass("active");
    }, 1000);
    setInterval(function(){
        if(i > 3) i = 1;
        $(".box").addClass("active");
        changeContent(i);
        $(".box").css({
            "background": `url(image/home-slider-${i++}.jpg)`
        });
    }, 6500);
    $(".box .left").click(()=>{
        i--;
        if(i < 1) i = 3;
        $(".box").addClass("active");
        changeContent(i);
        $(".box").css({
            "background": `url(image/home-slider-${i}.jpg)`
        });
    })
    $(".box .right").click(()=>{
        i++;
        if(i > 3) i = 1;
        $(".box").addClass("active");
        changeContent(i);
        $(".box").css({
            "background": `url(image/home-slider-${i}.jpg)`
        });
    })
    $(window).scroll(function(){
        if($(this).scrollTop() > 0)
        {
            $(".products .items.product").addClass("active");
        }
        else{
            $(".products .items.product").removeClass("active");
        }
        if($(this).scrollTop() > $(".products").height() + 200)
        {
            $(".about .content").addClass("active");
        }
        else{
            $(".about .content").removeClass("active");
        }
        if($(this).scrollTop() > 1400)
        {
            $(".products .items.arrivals .item").addClass("active");
        }
        else{
            $(".products .items.arrivals .item").removeClass("active");
        }
        if($(this).scrollTop() > 2500)
        {
            $("form > *").addClass("active");
        }
        else{   
            $("form > *").removeClass("active");
        }
        if($(this).scrollTop() > 2900)
        {
            $(".blogs .blog-items").addClass("active");
        }
        else{
            $(".blogs .blog-items").removeClass("active");
        }
    })
})