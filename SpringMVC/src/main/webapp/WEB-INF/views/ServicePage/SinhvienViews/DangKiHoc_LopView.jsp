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
								placeholder="Nhập tên lớp" aria-label="Search">
							<button class="btn btn-outline-light my-2 my-sm-0" type="submit">Search</button>
						</form>
					</div>
				</div>
				<form
					action="/QuanLiLopMVC/sinhvien/dang-ki-hoc?p=<%=request.getParameter("p")%>"
					method="post" class="form-horizontal" role="form"">
					<table class="table">
						<thead>
							<tr>
								<th scope="col">#</th>
								<th scope="col">Tên lớp</th>
								<th scope="col">Tên giảng viên</th>
								<th scope="col">Ngày bắt đầu</th>
								<th scope="col">Ngày kết thúc</th>
								<th scope="col">Số giờ học</th>
							</tr>
						</thead>
						<c:forEach var="lop" items="${listLop}">
							<tbody>
								<tr>
									<th scope="row"><input type="checkbox" name="${lop.maLop}">
									<td>${lop.tenLop}</td>
									<td></td>
									<td>${lop.ngayBatdau}</td>
									<td>${lop.ngayKetthuc}</td>
									<td>${lop.soGioHoc}</td>
								</tr>
							</tbody>
						</c:forEach>
					</table>
					<div class="form-group row offset-sm-1">
						<div class="col-sm-6">
							<label for="exampleFormControlSelect1">Tên sinh viên</label> <select
								name="sinhviens" class="form-control"
								id="exampleFormControlSelect1">
								<c:forEach var="sinhvien" items="${listSinhvien}">
									<option value="${sinhvien.maSinhvien}">${sinhvien.ho}
										${sinhvien.ten}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<c:if test="${message ne null }">
						<div class="alert alert-warning" role="alert">${message}</div>
					</c:if>
					<input type="hidden" name="maMonHoc" value="${monID}"> <input
						type="submit" class="btn btn-danger" value="Chọn">
				</form>
				<!-- form -->
				<!-- Page -->
				<c:if test="${message eq null }">
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

						<span
							style="margin: 0px 1.5rem; color: #333; letter-spacing: 10px">
							<span><%=p == null ? 1 : p%></span> / ${maxPage}
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
				</c:if>
				<!-- end page -->
			</div>
			<!-- end trung tam -->
			<!-- phai -->
			<div class="col-sm-1"></div>
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