<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link rel="stylesheet" href="css/reviews.css">
    <title>Reviews Product</title>
</head>
<body>
    <h2 id="fh2">WE APPRECIATE YOUR REVIEW!</h2>

    <form id="feedback" action="">
        <div class="pinfo">Your personal info</div>

        <div class="form-group">
            <div class="col-md-4 inputGroupContainer">
                <div class="input-group">
                    <span class="input-group-addon"><em class="fa fa-user"></em></span>
                    <input placeholder="enter your name" class="form-control" value="${CUSTOMER.getFullName()}" type="text">
                    <input type="hidden" name="customerID" value="${CUSTOMER.customerID}">
                    <input type="hidden" name="productID" value="<%=request.getParameter("productID")%>">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="col-md-4 inputGroupContainer">
                <div class="input-group">
                    <span class="input-group-addon"><em class="fa fa-envelope"></em></span>
                    <input type="email" value="${CUSTOMER.email}" class="form-control" placeholder="john.doe@yahoo.com">
                </div>
            </div>
        </div>

        <div class="pinfo">Rate our product.</div>


        <div class="form-group">
            <div class="col-md-4 inputGroupContainer">
                <div class="input-group">
                    <span class="input-group-addon"><em class="fa fa-heart"></em></span>
                    <select class="form-control" name="vote" id="rate">
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                    </select>
                </div>
            </div>
        </div>

        <div class="pinfo">Write your feedback.</div>
        <div class="form-group">
            <div class="col-md-4 inputGroupContainer">
                <div class="input-group">
                    <span class="input-group-addon"><em class="fa fa-pencil"></em></span>
                    <textarea class="form-control" id="review" rows="3" name="content"></textarea>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="col-md-4 inputGroupContainer">
                <div class="input-group">
                    <div class="file-upload">
                        <button class="file-upload-btn" type="button" onclick="$('.file-upload-input').trigger( 'click' )">
                            Chose an image
                        </button>
                        <div class="image-upload-wrap">
                            <input class="file-upload-input" id="file" type='file' onchange="readURL(this);" accept="image/*" multiple/>
                            <div class="drag-text">
                                <i class="fa fa-download" aria-hidden="true"></i>
                                <h3>Drag and drop a file or select add Image</h3>
                            </div>
                        </div>
                        <div class="file-upload-content">
                            <img class="file-upload-image" src="#" alt="your image"/>
                            <div class="image-title-wrap">
                                <button type="button" onclick="removeUpload()" class="remove-image">Remove <span
                                    class="image-title">Uploaded Image</span></button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <button id="submit" type="submit" class="btn btn-primary">Submit</button>
    </form>
    <script class="jsbin" src="https://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
    <script>
        function readURL(input) {
            if (input.files && input.files[0]) {

                const reader = new FileReader();

                const formData = new FormData()

                reader.onload = function (e) {
                    $('.image-upload-wrap').hide();

                    $('.file-upload-image').attr('src', e.target.result);
                    $('.file-upload-content').show();

                    $('.image-title').html(input.files[0].name);
                };

                reader.readAsDataURL(input.files[0]);
            } else {
                removeUpload();
            }
        }

        function removeUpload() {
            $('.file-upload-input').replaceWith($('.file-upload-input').clone());
            $('.file-upload-content').hide();
            $('.image-upload-wrap').show();
        }

        $('.image-upload-wrap').bind('dragover', function () {
            $('.image-upload-wrap').addClass('image-dropping');
        });
        $('.image-upload-wrap').bind('dragleave', function () {
            $('.image-upload-wrap').removeClass('image-dropping');
        });
    </script>
    <script src="js/reviews.js"></script>
</body>
</html>