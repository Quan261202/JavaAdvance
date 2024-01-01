<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<c:url var="urlAPI" value="/api/product"></c:url>
<c:url var="urlUploadFile" value="/fileUpload"></c:url>
<c:url var="urlHome" value="/Controller"></c:url>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>${type}</title>
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
                        <h2 class="tm-block-title d-inline-block">${type}</h2>
                    </div>
                </div>
                <form action="Category" method="post" id="form" class="row tm-edit-product-row">
                    <div class="col-xl-6 col-lg-6 col-md-12">
                        <div class="tm-edit-product-form">
                            <c:if test="${not empty category}">
                                <input type="hidden" name="categoryID" value="${category.categoryID}"/>
                            </c:if>
                            <div class="form-group mb-3">
                                <label for="name">Category Name</label>
                                <input id="name" value="<c:if test="${not empty category}">${category.categoryName}</c:if>" name="categoryName" type="text" class="form-control validate" required/>
                            </div>
                            <div class="form-group mb-3">
                                <label for="description">Description</label>
                                <textarea class="form-control validate" name="descriptions" id="description" rows="3" required>${category.descriptions}</textarea>
                            </div>
                        </div>
                    </div>
                    <div class="col-12">
                        <button type="submit" id="addOrUpdate" class="btn btn-primary btn-block text-uppercase">
                            <c:if test="${empty category}">Add Category Now</c:if>
                            <c:if test="${not empty category}">Update Category Now</c:if>
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
</script>
</body>
</html>