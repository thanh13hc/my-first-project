<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Danh sách sinh viên</title>
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
				<div class="row" style="background-color: #435D7D;">
					<div class="col-sm-8 mt-3 mb-3">
						<h4 style="color: whitesmoke;">
							Danh sách <span style="font-weight: bolder;">sinh viên lớp
								${maLop}</span>
						</h4>
					</div>
					<div class="col-sm-4 mt-3 mb-3">
						<button class="btn btn-success">Thêm mới</button>
					</div>
				</div>
				<form action="/QuanLiLop/sinhvien?page=deletes" method="post">
					<table class="table">
						<thead>
							<tr>
								<th scope="col">#</th>
								<th scope="col">Mã sinh viên</th>
								<th scope="col">Họ</th>
								<th scope="col">Tên</th>
								<th scope="col">Actions</th>
							</tr>
						</thead>
						<c:forEach var="sinhvien" items="${list}">
							<tbody>
								<tr>
									<th scope="row"><input type="checkbox"
										name="${sinhvien.maSinhvien}">
									<td>${sinhvien.maSinhvien}</td>
									<td>${sinhvien.ho}</td>
									<td>${sinhvien.ten}</td>
									<td><a
										href="/QuanLiLop/sinhvien?page=form-edit&&id=${sinhvien.maSinhvien}">
											<i class="far fa-edit"></i>
									</a> <a
										href="/QuanLiLop/sinhvien?page=delete&&id=${sinhvien.maSinhvien}"><i
											class="fas fa-trash"></i></a></td>
								</tr>
							</tbody>
						</c:forEach>
					</table>
					<c:if test="${message ne null }">
						<div class="alert alert-warning" role="alert">${message}</div>
					</c:if>
					<input type="submit" class="btn btn-danger" value="Xóa">
				</form>

				<c:if test="${message eq null }">
						<!-- Page -->
						<div class="row mt-3">
							<%
								Integer p = Integer.valueOf((request.getParameter("p") == null ? "1" : request.getParameter("p")));
										Integer maxPage = (Integer) request.getAttribute("maxPage");
							%>
							<c:choose>
								<c:when test="<%=p > 1%>">
									<a href="/QuanLiLop/lop?page=tim-kiem-sinhvien&&lopID=${maLop}&&p=<%=p - 1%>">Previous</a>
								</c:when>
								<c:otherwise>
									<a href="/QuanLiLop/lop?page=tim-kiem-sinhvien&&lopID=${maLop}&&p=1">Previous</a>
								</c:otherwise>
							</c:choose>

							<span
								style="margin: 0px 1.5rem; color: #333; letter-spacing: 10px">
								<span><%=p == null ? 1 : p%></span> / ${maxPage}
							</span>

							<c:choose>
								<c:when test="<%=p < maxPage%>">
									<a href="/QuanLiLop/lop?page=tim-kiem-sinhvien&&lopID=${maLop}&&p=<%=p + 1%>">Next</a>
								</c:when>
								<c:otherwise>
									<a href="/QuanLiLop/lop?page=tim-kiem-sinhvien&&lopID=${maLop}&&p=${maxPage}">Next</a>
								</c:otherwise>
							</c:choose>
						</div>
						<!-- end Page -->
					</c:if>
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


