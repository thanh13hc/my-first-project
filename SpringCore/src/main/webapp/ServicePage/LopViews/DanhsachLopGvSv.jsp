<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
			<div class="col-sm-8">

				<div class="row" style="background-color: #435D7D;">
					<div class="col-sm-8 mt-3 mb-3">
						<h4 style="color: whitesmoke;">
							Danh sách <span style="font-weight: bolder;">lớp </span>
						</h4>
					</div>
					<div class="col-sm-4 mt-3 mb-3">
						<button class="btn btn-success">Thêm mới</button>
					</div>
				</div>
					<table class="table">
						<thead>
							<tr>
								<th scope="col">Mã Lớp</th>
								<th scope="col">Tên lớp</th>
								<th scope="col">Tên môn học</th>
								<th scope="col">Ngày bắt đầu</th>
								<th scope="col">Ngày kết thúc</th>
								<th scope="col">Số giờ học</th>
								<th scope="col">Đăng kí học - giảng dạy</th>
							</tr>
						</thead>
						<c:forEach var="lop" items="${list}">
							<tbody>
								<tr>
									<td>${lop.maLop}</td>
									<td>${lop.tenLop}</td>
									<td>${lop.monhoc.tenMonhoc}</td>
									<td>${lop.ngayBatdau}</td>
									<td>${lop.ngayKetthuc}</td>
									<td>${lop.soGioHoc}</td>
									<td><a
										href="/QuanLiLop/lop?page=them-sv-gv&&id=${lop.maLop}"> Đăng kí</a>
								</td>
								</tr>
							</tbody>
						</c:forEach>
					</table>
				<div class="row mt-3">
					<%
						Integer p = Integer.valueOf((request.getParameter("p") == null ? "1" : request.getParameter("p")));
						Integer maxPage = (Integer) request.getAttribute("maxPage");
					%>
					<c:choose>
						<c:when test="<%=p > 1%>">
							<a href="/QuanLiLop/lop?page=them-sv-gv-form&&p=<%=p - 1%>">Previous</a>
						</c:when>
						<c:otherwise>
							<a href="/QuanLiLop/lop?page=them-sv-gv-form&&p=1">Previous</a>
						</c:otherwise>
					</c:choose>

					<span style="margin: 0px 1.5rem; color: #333; letter-spacing: 10px">
						<span><%=request.getParameter("p") == null ? 1 : request.getParameter("p")%></span>
						/ ${maxPage}
					</span>

					<c:choose>
						<c:when test="<%=p < maxPage%>">
							<a href="/QuanLiLop/lop?page=them-sv-gv-form&&p=<%=p + 1%>">Next</a>
						</c:when>
						<c:otherwise>
							<a href="/QuanLiLop/lop?page=them-sv-gv-form&&p=${maxPage}">Next</a>
						</c:otherwise>
					</c:choose>
				</div>
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
