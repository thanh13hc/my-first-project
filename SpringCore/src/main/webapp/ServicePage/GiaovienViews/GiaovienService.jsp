<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quản Lí giáo viên</title>
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
<body style="background-color: #7dff0738;">
	<%@include file="../../header.jsp"%>

	<!-- body -->
	<div class="container-fluid mt-5">
		<div class="row">

			<!-- navbar trái -->
			<div class="col-sm-3" style="padding-right: 0px !important;">
				<%@include file="../navbarService.jsp"%>
			</div>

			<!-- trung tam -->
			<div class="col-sm-8">
				<%@include file='DanhsachGiaovien.jsp'%>
			</div>
			<div class="col-sm-1"></div>
			<!-- phai -->
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


