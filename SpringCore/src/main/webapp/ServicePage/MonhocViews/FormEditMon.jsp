<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sửa môn học</title>
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
<body style="background-color: #7dff0738;"><%@include
		file="../../header.jsp"%>

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
							Sửa <span style="font-weight: bolder;">môn học </span>
						</h4>
					</div>
				</div>
				<div class="row">
					<form action="/QuanLiLop/mon?page=edit" method="post"
						style="width: 100%;">
						<div class="form-group row">
							<label for="inputEmail"
								class="offset-sm-1 col-sm-3 col-form-label">Mã môn học</label>
							<div class="col-sm-6">
								<input type="text" name="maMon" class="form-control-plaintext"
									id="inputEmail" value="${mon.maMonhoc}"readonly">
							</div>
						</div>
						<div class="form-group row">
							<label for="inputText"
								class="offset-sm-1 col-sm-3 col-form-label">Tên môn học</label>
							<div class="col-sm-6">
								<input type="text" name="tenMon" class="form-control"
									id="inputPassword" value="${mon.tenMonhoc}">
							</div>
						</div>
						<div class="form-group row mt-3">
							<div class="col-sm-10 offset-sm-3">
								<button type="submit" class="btn btn-primary">Lưu</button>
							</div>
						</div>
					</form>
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


