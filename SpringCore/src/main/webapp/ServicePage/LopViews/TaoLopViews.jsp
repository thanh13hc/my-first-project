<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tạo Lớp</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<!--  <link rel="stylesheet" href="css/my-style.css"> -->
<link
	href="https://fonts.googleapis.com/css?family=Roboto:300,300i,400,400i,500,500i,700,700i&display=swap&subset=vietnamese"
	rel="stylesheet">
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
			<div class="col-sm-6">
				<div class="row mb-3" style="background-color: #435D7D;">
					<div class="col-sm-12 mt-3 mb-3">
						<h4 style="color: whitesmoke;">
							Tạo <span style="font-weight: bolder;">lớp học </span>
						</h4>
					</div>
				</div>
				<div class="row">
					<!-- form -->
					<form action="/QuanLiLop/lop?page=add" method="POST"
						class="form-horizontal" role="form" style="width: 100%;">
						<div class="form-group offset-sm-1">
							<label for="lopName" class="col-sm-3 control-label">Tên
								lớp học*</label>
							<div class="col-sm-9">
								<input type="text" name="lopName" id="lopName" placeholder="Tên lớp học"
									class="form-control" autofocus required>
							</div>
						</div>
						<div class="form-group offset-sm-1">
							<label for="" class="col-sm-3 control-label">Môn học*</label>
							<div class="col-sm-9">
								<select class="form-control" name="monhocs" id="monhocs"
									required>
									<c:forEach var="mon" items="${list}">
										<option value="${mon.maMonhoc}">${mon.tenMonhoc}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group offset-sm-1">
							<label for="birthDate" class="col-sm-3  control-label">Ngày
								bắt đầu*</label>
							<div class="col-sm-9">
								<input type="date" name="startDate" id="birthDate"
									class="form-control" required>
							</div>
						</div>
						<div class="form-group offset-sm-1">
							<label for="birthDate" class="col-sm-3 control-label">Ngày
								kết thúc*</label>
							<div class="col-sm-9">
								<input type="date" name="endDate" id="birthDate"
									class="form-control" required>
							</div>
						</div>
						<div class="form-group offset-sm-1">
							<label for="Height" class="col-sm-3 control-label">Số giờ
								học* </label>
							<div class="col-sm-9">
								<input type="number" id="height" name="giohoc" placeholder="Số giờ học"
									class="form-control" required min="10" max="7200">
							</div>
						</div>
						<div class="form-group offset-sm-1">
							<div class="col-sm-9 col-sm-offset-3">
								<span class="help-block">*Required fields</span>
							</div>
						</div>
						<button type="submit"
							class="btn btn-primary btn-block offset-sm-1" style="width: 70%">Register</button>
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


