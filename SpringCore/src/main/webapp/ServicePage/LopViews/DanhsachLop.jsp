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
<body>

	<div class="row" style="background-color: #435D7D;">
		<div class="col-sm-8 mt-3 mb-3">
			<h4 style="color: whitesmoke;">
				Quản lí <span style="font-weight: bolder;">lớp </span>
			</h4>
		</div>
		<div class="col-sm-4 mt-3 mb-3">
			<button class="btn btn-success">Thêm mới</button>
		</div>
	</div>
	<form action="/QuanLiLop/lop?page=deletes" method="post">
		<table class="table">
			<thead>
				<tr>
					<th scope="col">#</th>
					<th scope="col">Mã Lớp</th>
					<th scope="col">Tên lớp</th>
					<th scope="col">Tên môn học</th>
					<th scope="col">Ngày bắt đầu</th>
					<th scope="col">Ngày kết thúc</th>
					<th scope="col">Số giờ học</th>
					<th scope="col">Actions</th>
				</tr>
			</thead>
			<c:forEach var="lop" items="${list}">
				<tbody>
					<tr>
						<th scope="row"><input type="checkbox" name="${lop.maLop}">
						<td>${lop.tenLop}</td>
						<td>${lop.monhoc.tenMonhoc}</td>
						<td>${lop.ngayBatdau}</td>
						<td>${lop.ngayKetthuc}</td>
						<td>${lop.soGioHoc}</td>
						<td><a href="/QuanLiLop/mon?page=form-edit&&id=${lop.maLop}">
								<i class="far fa-edit"></i>
						</a> <a href="/QuanLiLop/mon?page=delete&&id=${lop.maLop}"><i
								class="fas fa-trash"></i></a></td>
					</tr>
				</tbody>
			</c:forEach>
		</table>
		<input type="submit" class="btn btn-danger" value="Xóa">
	</form>
	<div class="row mt-3">
		<%
			Integer p = Integer.valueOf((request.getParameter("p") == null ? "1" : request.getParameter("p")));
			Integer maxPage = (Integer) request.getAttribute("maxPage");
		%>
		<c:choose>
			<c:when test="<%=p > 1%>">
				<a href="/QuanLiLop/lop?p=<%=p - 1%>">Previous</a>
			</c:when>
			<c:otherwise>
				<a href="/QuanLiLop/lop?p=1">Previous</a>
			</c:otherwise>
		</c:choose>

		<span style="margin: 0px 1.5rem; color: #333; letter-spacing: 10px">
			<span><%=request.getParameter("p") == null ? 1 : request.getParameter("p")%></span>
			/ ${maxPage}
		</span>

		<c:choose>
			<c:when test="<%=p < maxPage%>">
				<a href="/QuanLiLop/lop?p=<%=p + 1%>">Next</a>
			</c:when>
			<c:otherwise>
				<a href="/QuanLiLop/lop?p=${maxPage}">Next</a>
			</c:otherwise>
		</c:choose>
	</div>

</body>
</html>
