<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Thêm sinh viên</title>
<style>
input[type="text"], input[type="number"] {
	padding: 7px;
	border: 1px solid #ff93ff9e;
	margin-bottom: 14px;
}

input[type="submit"] {
	border: 0;
	color: white;
	margin-top: 14px;
	padding: 7px;
	background-color: #337AB7;
}

b {
	margin-left: 15px;
	font-size: 18px;
}
</style>
</head>
<body>
	<%@ include file="../../header.jsp"%>
	<%
		request.setCharacterEncoding("UTF-8");
	%>
	<h1 style="margin-left: 20px; letter-spacing: 1px;">Student</h1>
	<form action='/MySpringCoreSite/sinhvien?page=add' method='post'
		style="margin-left: 50px">
		<b>Mã sinh viên:</b><br> <input type="number" name='msv'
			size="40" min="1" max="2147483647" required><br> <b>Họ:</b>
		<br> <input type='text' name='ho' size="40" maxlength="20"
			required><br> <b>Tên:</b> <br> <input type='text'
			name='ten' size="40" maxlength="15" required><br> <b>Mã
			Khoa:</b> <br> <input type='text' name='maKhoa' size="40" min="1"
			max="2147483647" required><br> <b>Mã Lớp:</b> <br>
		<input type='number' name='maLop' size="40" min="1" max="2147483647"
			required><br> <input type='submit' value='Lưu thông tin'>
	</form>

	<a href="/MySpringCoreSite/home"
		style="position: fixed; bottom: 0px; background-color: #337AB7; color: wheat; padding: 8px; border: 1px solid #cccccc; border-radius: 3px; margin-top: 10px">
		Trở lại trang chủ</a>

	<%@ include file="../../footer.jsp"%>
</body>
</html>