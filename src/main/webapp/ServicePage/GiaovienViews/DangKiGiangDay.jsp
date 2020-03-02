<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng kí giảng dạy</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://fonts.googleapis.com/css?family=Roboto:300,300i,400,400i,500,500i,700,700i&display=swap&subset=vietnamese"
	rel="stylesheet">
<!--  <link rel="stylesheet" href="css/my-style.css"> -->
<link
	href="https://unpkg.com/bootstrap@4.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>
	<%@include file="../../header.jsp"%>

	<!-- body -->
	<div class="container-fluid mt-5">
		<div class="row">

			<!-- navbar trái -->
			<div class="col-sm-3" style="padding-right: 0px !important;">
				<%@include file="../navbarService.jsp"%>
			</div>

			<!-- trung tam -->
			<div class="col-sm-6">
				<div class="row mb-3" style="background-color: #435D7D;">
					<div class="col-sm-12 mt-3 mb-3">
						<h4 style="color: whitesmoke;">
							Đăng kí <span style="font-weight: bolder;">giảng dạy </span>
						</h4>
					</div>
				</div>
				<div class="row">
					<!-- form -->
					<form action="/QuanLiLop/giaovien?page=dang-ki-giang-day"
						method="POST" class="form-horizontal" role="form"
						style="width: 100%;">
						<div class="form-group offset-sm-1">
							<label for="" class="col-sm-3 control-label">Chọn lớp học
								*</label>
							<div class="col-sm-9">
								<select class="form-control" name="lophocs" id="lophocs"
									required>
									<c:forEach var="lop" items="${listLop}">
										<option value="${lop.maLop}">${lop.tenLop}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group offset-sm-1">
							<label for="" class="col-sm-3 control-label">Chọn giáo
								viên *</label>
							<div class="col-sm-9">
								<select class="form-control" name="giaoviens" id="giaoviens"
									required>
									<c:forEach var="giaovien" items="${listGiaovien}">
										<option value="${giaovien.maGiaovien}">${giaovien.ho} ${giaovien.ten}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group offset-sm-1">
							<div class="col-sm-9 col-sm-offset-3">
								<span class="help-block">*Required fields</span>
							</div>
						</div>
						<button type="submit"
							class="btn btn-primary btn-block offset-sm-1" style="width: 70%">Đăng
							kí</button>
					</form>
					<!-- /form -->
				</div>
			</div>

			<!-- phai -->
			<div class="col-sm-3"></div>
		</div>
	</div>

	<!-- Page Content -->
	<section class="py-5">
		<div class="container">
			<h1 class="display-4">Full Page Image Slider</h1>
			<p class="lead">
				The background images for the slider are set directly in the HTML
				using inline CSS. The images in this snippet are from <a
					href="https://unsplash.com">Unsplash</a>, taken by <a
					href="https://unsplash.com/@joannakosinska">Joanna Kosinska</a>!
			</p>
		</div>
	</section>

	<%@include file="../../footer.jsp"%>
</body>
</html>


