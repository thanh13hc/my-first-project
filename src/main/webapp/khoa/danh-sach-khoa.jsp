<%@ page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Danh sách Khoa</title>
<style>
table {
	width: 90%;
	border-collapse: collapse;
	color: #00009E;
	margin: 0 5% 1% 5%;
}

th {
	border: 1px solid black;
	color: white !important;
	background-color: #2D8ECE;
	padding: 10px 0px;
}

td {
	border: 1px solid black;
	padding: 5px 0px;
	text-align: center;
}
input[type="submit"] {
	border: 0;
	color: white;
	margin-top: 14px;
	padding: 7px;
	background-color: #337AB7;
}
</style>
</head>
<body>
	<%@ include file="../../header.jsp"%>
	<%@ include file="../../link.jsp"%>

	<%
	request.setCharacterEncoding("UTF-8");
	int stt = 1;
	int numberKhoa = 0;
	%>

	<h1 style="text-align: center;">Danh sách khoa</h1>
	<form action="/MySpringCoreSite/khoa?page=delete" method="post">
		<table>
			<tr>
				<th>STT</th>
				<th>Mã Khoa</th>
				<th>Tên Khoa</th>
				<th>Sửa</th>
				<th>Xóa</th>
			</tr>
			<c:forEach items="${khoa}" var="khoa">
			<tr>
				<td><%=stt%></td>
				<td>${khoa.maKhoa}</td>
				<td>${khoa.tenKhoa}</td>
				<td><a href="/MySpringCoreSite/khoa?page=edit?id=${khoa.maKhoa}">Edit</a></td>
				<td><input type="checkbox" name="khoa${khoa.maKhoa}" checked="checked" />Delete</td>
			</tr>
			<%
				stt++;
				numberKhoa++;
			%>
			</c:forEach>
		</table>
		<input type="submit" value="Lưu thông tin">

	</form>
	<a href="/MySpringCoreSite/home"
		style="position: fixed; bottom: 0px; background-color: #337AB7; color: wheat; padding: 8px; border: 1px solid #cccccc; border-radius: 3px; margin-top: 10px">
		Trở lại trang chủ</a>
	<%@ include file="../../footer.jsp"%>
</body>
</html>