<%@page import="com.Mysite.common.Common"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<style>
ul {
	list-style-type: none;
	margin: 0% 20%;
	background-color: #f1f1f1;
}

ul li {
	padding: 8px 16px;
	display: inline-block;
}

li a {
	color: #000;
	text-decoration: none;
}
li a:hover {
	background-color: #555;
	color: white;
}
</style>
</head>
<body>
	<ul>
		<li><a href="/MySpringCoreSite/home"> Home </a></li>
		<li><a href="/MySpringCoreSite/khoa"> Khoa </a></li>
		<li><a href="/MySpringCoreSite/lop"> Lớp </a></li>
		<li><a href="/MySpringCoreSite/sinhvien"> Sinh viên </a></li>
		<li>Xin chào
			<p style="color: red">${USER_SESSION}</p>
		</li>
		<li><a href="/MySpringCoreSite/logout">Đăng xuất</a></li>
	</ul>
</body>
</html>