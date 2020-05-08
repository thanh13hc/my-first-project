<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng kí học</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
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
			<div class="col-sm-8">
				<div class="row" style="background-color: #435D7D;">
					<div class="col-sm-6 mt-3 mb-3">
						<h4 style="color: whitesmoke;">
							Đăng kí <span style="font-weight: bolder;"> học </span>
						</h4>
					</div>
					<div class="col-sm-6 mt-3 mb-3">
						<form class="form-inline my-2 my-lg-0"
							action="https://google.com.vn/search?q=abc">
							<input class="form-control mr-sm-2" type="search"
								placeholder="Nhập tên môn học" aria-label="Search">
							<button class="btn btn-outline-light my-2 my-sm-0" type="submit">Search</button>
						</form>
					</div>
				</div>

				<table class="table">
					<thead>
						<tr>
							<th scope="col">Mã môn học</th>
							<th scope="col">Tên môn học</th>
							<th scope="col">Actions</th>
						</tr>
					</thead>
					<c:forEach var="mon" items="${list}">
						<tbody>
							<tr>
								<td>${mon.maMonhoc}</td>
								<td>${mon.tenMonhoc}</td>
								<td><a
									href="/QuanLiLopMVC/sinhvien/dang-ki-lop?monID=${mon.maMonhoc}&&p=1"
									class="btn btn-outline-success"> Chọn </a></td>
							</tr>
						</tbody>
					</c:forEach>
				</table>
				<!-- table -->

				<c:if test="${message ne null }">
					<div class="alert alert-warning" role="alert">${message}</div>
				</c:if>

				<!-- Page -->
				<div class="row mt-3">
					<%
						Integer p = Integer.valueOf((request.getParameter("p") == null ? "1" : request.getParameter("p")));
						Integer maxPage = (Integer) request.getAttribute("maxPage");
					%>
					<c:choose>
						<c:when test="<%=p > 1%>">
							<a href="/QuanLiLopMVC/sinhvien/dang-ki-hoc?p=<%=p - 1%>">Previous</a>
						</c:when>
						<c:otherwise>
							<a href="/QuanLiLopMVC/sinhvien/dang-ki-hoc?p=1">Previous</a>
						</c:otherwise>
					</c:choose>

					<span style="margin: 0px 1.5rem; color: #333; letter-spacing: 10px">
						<span><%=request.getParameter("p") == null ? 1 : request.getParameter("p")%></span>
						/ ${maxPage}
					</span>

					<c:choose>
						<c:when test="<%=p < maxPage%>">
							<a href="/QuanLiLopMVC/sinhvien/dang-ki-hoc?p=<%=p + 1%>">Next</a>
						</c:when>
						<c:otherwise>
							<a href="/QuanLiLopMVC/sinhvien/dang-ki-hoc?p=${maxPage}">Next</a>
						</c:otherwise>
					</c:choose>
				</div>
				<!-- end Page -->
			</div>
			<!-- end trung tam -->

			<!-- phai -->
			<div class="col-sm-1"></div>
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