<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url var="urlAPI" value="/user/edit"></c:url>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title></title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<link rel="stylesheet" href="css/profile.css">
</head>
<body>
	<div class="container rounded bg-white mt-5 mb-5">
		<form id="form" class="row">
			<div class="col-md-3 border-right">
				<input type="hidden" name="customerID"
					value="${customer.customerID }">
				<div
					class="d-flex flex-column align-items-center text-center p-3 py-5">
					<img id="avatar" class="rounded-circle mt-5" width="150px"
						src="${customer.avatar}"><span class="font-weight-bold">${customer.lastName}</span><span
						class="text-black-50">${customer.email }</span><span> </span>
				</div>
			</div>
			<div class="col-md-5 border-right">
				<div class="p-3 py-5">
					<div class="d-flex justify-content-between align-items-center mb-3">
						<h4 class="text-right">Profile Settings</h4>
					</div>
					<div class="row mt-2">
						<div class="col-md-6">
							<label class="labels">Name</label><input type="text"
								class="form-control" name="firstName" placeholder="first name"
								value="${customer.firstName }">
						</div>
						<div class="col-md-6">
							<label class="labels">Surname</label><input type="text"
								class="form-control" name="lastName"
								value="${customer.lastName }" placeholder="enter your last name">
						</div>
					</div>
					<div class="row mt-3">
						<div class="col-md-12">
							<label class="labels">Mobile Number</label><input type="text"
								class="form-control" name="phone"
								placeholder="enter phone number" value="${customer.phone }">
						</div>
						<div class="col-md-12">
							<label class="labels">Email ID</label><input type="text"
								class="form-control" name="email" placeholder="enter email id"
								value="${customer.email }">
						</div>
						<div class="col-md-12">
							<label class="labels">Province</label> <select
								class="selectpicker show-tick form-control" id="province">
								<c:forEach items="${province}" var="pro">
									<option value="${pro}"
										<c:if test="${customer.address.contains(pro)}">selected="selected"</c:if>>${pro}</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-md-12">
							<label class="labels">District</label> <select
								class="selectpicker show-tick form-control" id="district">
								<c:forEach items="${district}" var="dis">
									<option value="${dis}"
										<c:if test="${customer.address.contains(dis)}">selected="selected"</c:if>>${dis}</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-md-12">
							<label class="labels">Ward</label> <select
								class="selectpicker show-tick form-control" id="ward">
								<c:forEach items="${ward}" var="wa">
									<option value="${wa}"
										<c:if test="${customer.address.contains(wa)}">selected="selected"</c:if>>${wa}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="mt-5 text-center">
						<button class="btn btn-primary profile-button" id="saveProfile"
							type="button">Save Profile</button>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="p-3 py-5">
					<div class="mb-3">
						<label for="formFileSm" class="form-label">Chose an avatar</label>
						<input class="form-control form-control-sm" id="formFileSm"
							type="file">
					</div>
				</div>
			</div>
		</form>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script type="text/javascript" src="js/editProfile.js"></script>
</body>
</html>