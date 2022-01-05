<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url var="urlRegister" value="singUp"></c:url>
<c:url var="urlLogin" value="<%=request.getServletPath()%>"></c:url>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <script
            src="https://kit.fontawesome.com/64d58efce2.js"
            crossorigin="anonymous"></script>
        <link rel="stylesheet" href="css/login.css">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
        <title>Sign in & Sign up Form</title>
    </head>
    <body>
        <script>
            function statusChangeCallback(response) {
                console.log(response);
                if (response.status === 'connected') {
                    testAPI();
                    window.location.href = "LoginServlet?loginFB=" + response.authResponse.accessToken;
                }
            }

            function checkLoginState() {
                FB.getLoginStatus(function (response) {
                    statusChangeCallback(response);
                });
            }

            window.fbAsyncInit = function () {
                FB.init({
                    appId: '3012954795613233',
                    cookie: true,
                    xfbml: true,
                    version: 'v3.2'
                });
                FB.getLoginStatus(function (response) {
                    statusChangeCallback(response);
                });
            };

            function testAPI() { // Testing Graph API after login.  See statusChangeCallback() for when this call is made.
                console.log('Welcome!  Fetching your information.... ');
                FB.api(
                    '/me',
                    function (response) {
                        console.log(response);
                    });
            }
        </script>
        <script async defer crossorigin="anonymous" src="https://connect.facebook.net/en_US/sdk.js"></script>

        <c:if test="${empty name}">
            <div class="container">
                <div class="forms-container">
                    <div class="signin-signup">
                        <form action="LoginServlet" class="sign-in-form" method="post">
                            <h2 class="title">Sign in</h2>
                            <div class="input-field">
                                <i class="fas fa-user"></i>
                                <input autofocus id="name" type="text" placeholder="Username" name="name"/>
                            </div>
                            <c:if test="${not empty error}">
                                <p class="message" style="display: flex;align-items: center;justify-content: center;color: #c82333"><i style="margin-right: 5px" class="fas fa-exclamation-triangle"></i>User Name or PassWord Invalid</p>
                            </c:if>
                            <div class="input-field">
                                <i class="fas fa-lock"></i>
                                <input id="pass" type="password" placeholder="Password" name="password"/>
                            </div>
                            <input type="submit" value="Login" class="btn solid" />
                            <p class="social-text">Or Sign in with social platforms</p>
                            <div class="social-media">
                                <a style="overflow: hidden" href="#" class="social-icon">
                                    <fb:login-button scope="public_profile,email"
                                                     onlogin="checkLoginState();">
                                    </fb:login-button>
                                </a>
                                <a href="#" class="social-icon">
                                    <i class="fab fa-twitter"></i>
                                </a>
                                <a href="#" class="social-icon">
                                    <i class="fab fa-google"></i>
                                </a>
                                <a href="#" class="social-icon">
                                    <i class="fab fa-linkedin-in"></i>
                                </a>
                            </div>
                        </form>
                        <form action="#" class="sign-up-form">
                            <h2 class="title">Sign up</h2>
                            <div class="input-field">
                                <i class="fas fa-user"></i>
                                <input autofocus autocomplete="off" type="text" placeholder="Username" name="userName" />
                            </div>
                            <div class="input-field">
                                <i class="fas fa-envelope"></i>
                                <input id="email" type="email" placeholder="Email" name="email" />
                            </div>
                            <div class="input-field">
                                <i class="fas fa-lock"></i>
                                <input autocomplete="off" type="password" placeholder="Password" name="password" />
                            </div>
                            <input type="submit" id="submit" class="btn" value="Sign up" />
                            <p class="social-text">Or Sign up with social platforms</p>
                            <div class="social-media">
                                <a href="#" class="social-icon">
                                    <i class="fab fa-facebook"></i>
                                </a>
                                <a href="#" class="social-icon">
                                    <i class="fab fa-twitter"></i>
                                </a>
                                <a href="#" class="social-icon">
                                    <i class="fab fa-google"></i>
                                </a>
                                <a href="#" class="social-icon">
                                    <i class="fab fa-linkedin-in"></i>
                                </a>
                            </div>
                        </form>
                    </div>
                </div>

                <div class="panels-container">
                    <div class="panel left-panel">
                        <div class="content">
                            <h3>New here ?</h3>
                            <p>
                                Lorem ipsum, dolor sit amet consectetur adipisicing elit. Debitis,
                                ex ratione. Aliquid!
                            </p>
                            <button class="btn transparent" id="sign-up-btn">
                                Sign up
                            </button>
                        </div>
                        <img src="image/log.svg" class="image" alt="" />
                    </div>
                    <div class="panel right-panel">
                        <div class="content">
                            <h3>One of us ?</h3>
                            <p>
                                Lorem ipsum dolor sit amet consectetur adipisicing elit. Nostrum
                                laboriosam ad deleniti.
                            </p>
                            <button class="btn transparent" id="sign-in-btn">
                                Sign in
                            </button>
                        </div>
                        <img src="image/register.svg" class="image" alt="" />
                    </div>
                </div>
            </div>
        </c:if>
        <c:if test="${not empty name}">
            <c:redirect url="loadProducts"></c:redirect>
        </c:if>
        <script src="js/loginFB.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script>
            $(document).ready(()=>{
                $('#submit').on('click', (e)=>{
                    e.preventDefault();
                    const arrays = $('.sign-up-form').serializeArray();
                    console.log(arrays)
                    console.log('${urlLogin}')
                    const inputs = $('.sign-up-form .input-field input');
                    console.log(inputs)
                    let check = true
                    for(let i = 0; i < inputs.length; ++i)
                    {
                        if($(inputs[i]).val() == '')
                        {
                            check = false;
                            $(inputs[i]).focus();
                            break;
                        }
                    }
                    if(!/^\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}$/.test($('#email').val())) check = false;
                    if(check) {
                        let user = {}
                        $.each(arrays, (index, value) =>{
                            user[value.name] = value.value;
                        })
                        $.ajax({
                            type: 'POST',
                            contentType: 'application/json',
                            url: '${urlRegister}',
                            data: JSON.stringify(user),
                            success: (data)=>{
                                alert(data)
                                window.location.href = '${urlLogin}'
                            }
                        })
                    }
                })
            })
        </script>
    </body>
</html>