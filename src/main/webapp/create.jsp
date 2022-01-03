<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<c:url var="urlAPI" value="/api/product"></c:url>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Add Product - Dashboard HTML Template</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:400,700"/>
    <link rel="stylesheet" href="css/fontawesome.min.css"/>
    <link rel="stylesheet" href="css/bootstrap.min.css"/>
    <link rel="stylesheet" href="css/addProduct.css">

</head>

<body>
<div class="container tm-mt-big tm-mb-big">
    <div class="row">
        <div class="col-xl-9 col-lg-10 col-md-12 col-sm-12 mx-auto">
            <div class="tm-bg-primary-dark tm-block tm-block-h-auto">
                <div class="row">
                    <div class="col-12">
                        <h2 class="tm-block-title d-inline-block">Add Product</h2>
                    </div>
                </div>
                <form action="Controller?a=Create" method="post" id="form" class="row tm-edit-product-row" enctype="multipart/form-data">
                    <div class="col-xl-6 col-lg-6 col-md-12">
                        <div class="tm-edit-product-form">
                            <c:if test="${not empty product}">
                                <input type="hidden" name="productID" value="${product.productID}"/>
                            </c:if>
                            <input type="hidden" value="1" name="status" id="status" />
                            <div class="form-group mb-3">
                                <label for="name">Product Name</label>
                                <input id="name" value="<c:if test="${not empty product}">${product.productName}</c:if>" name="productName" type="text" class="form-control validate" required/>
                            </div>
                            <div class="form-group mb-3">
                                <label for="description">Description</label>
                                <textarea class="form-control validate" id="description" rows="3" required>No Description</textarea>
                            </div>
                            <div class="form-group mb-3">
                                <label for="category">Category</label>
                                <select class="custom-select tm-select-accounts" name="categoryID" id="category">
                                    <option value="">Select category</option>
                                    <c:forEach var="item" items="${category}">
                                        <option value="${item.categoryID}"
                                                <c:if test="${not empty product && item.categoryID == product.categoryID}">selected="selected"</c:if>
                                        >${item.categoryName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="row">
                                <div class="form-group mb-3 col-xs-12 col-sm-6">
                                    <label for="price">Price</label>
                                    <input id="price" name="price" type="text"
                                           value="<c:if test="${not empty product}">${product.price}</c:if>"
                                           class="form-control validate" data-large-mode="true"/>
                                </div>
                                <div class="form-group mb-3 col-xs-12 col-sm-6">
                                    <label for="quantity">Quantity</label>
                                    <input id="quantity" name="quantity" type="text"
                                           value="<c:if test="${not empty product}">${product.quantity}</c:if>"
                                           class="form-control validate" required/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-6 col-lg-6 col-md-12 mx-auto mb-4">
                        <div class="tm-product-img-dummy mx-auto">
                            <em class="fas fa-images tm-upload-icon"
                                onclick="document.getElementById('fileInput').click();"></em>
                        </div>
                        <div class="custom-file mt-3 mb-3">
                            <input id="fileInput" name="image" type="file" style="display:none;"/>
                            <input type="button" class="btn btn-primary btn-block mx-auto"
                                   value="UPLOAD PRODUCT IMAGE"
                                   onclick="document.getElementById('fileInput').click();"/>
                        </div>
                    </div>
                    <div class="col-12">
                        <button type="submit" id="addOrUpdate" class="btn btn-primary btn-block text-uppercase">
                            <c:if test="${empty product}">Add Product Now</c:if>
                            <c:if test="${not empty product}">Update Product Now</c:if>
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>


    <script src="JQUERY/jquery-3.3.1.min.js"></script>

    <script src="JQUERY/jquery-ui.min.js"></script>

    <script src="JQUERY/bootstrap.min.js"></script>

    <script>
        $(document).ready(()=>{
            const type = $('#addOrUpdate').text();
            if(type.indexOf('Update', 0) >= 0)
            {
                $('#addOrUpdate').on('click', (e)=>{
                    const type = $('#addOrUpdate').text();
                    e.preventDefault();
                    const product = $('#form').serializeArray();
                    const object = {}
                    $.each(product, (index, value)=>{
                        object[value.name] = value.value;
                    })
                    $.ajax({
                        type: 'PUT',
                        contentType: 'application/json',
                        url: '${urlAPI}',
                        data: JSON.stringify(object),
                        success: (data)=>{
                            alert(data);
                            window.location.href = "/ShoppingHalloween/Controller";
                        },
                        error: (error)=>{
                            console.log(error)
                        }
                    })
                })
            }
        });
    </script>
</body>
</html>