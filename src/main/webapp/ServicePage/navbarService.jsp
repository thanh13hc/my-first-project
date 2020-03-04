<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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
</head>
<body>

	<nav class="navbar navbar-info bg-light">
		<ul class="navbar-nav">
			<b>
				<li class="nav-item"><a class="nav-link"> Môn học</a></li>
			</b>
			<li class="nav-item"><a href="/QuanLiLop/mon?p=1"
				class="nav-link">Danh sách môn học</a> <b>
					<li class="nav-item"><a class="nav-link">Lớp học</a></li>
			</b>
			<li class="nav-item"><a href="/QuanLiLop/lop?p=1"
				class="nav-link">Danh sách lớp học</a></li>
			<li class="nav-item"><a href="/QuanLiLop/lop?page=add-lop"
				class="nav-link">Tạo lớp</a>
			<li class="nav-item"><a href="/QuanLiLop/lop?page=tim-kiem"
				class="nav-link">Tìm kiếm</a>
				<li class="nav-item"><a href="/QuanLiLop/lop?page=them-sv-gv-form"
				class="nav-link">Thêm sinh viên - giáo viên</a>
			<b>
				<li class="nav-item"><a class="nav-link"> Giáo viên</a></li>
			</b>
			<li class="nav-item"><a href="/QuanLiLop/giaovien?p=1"
				class="nav-link">Danh sách giáo viên</a></li>
			<li class="nav-item"><a href="/QuanLiLop/giaovien?page=dang-ki-giang-day"
				class="nav-link">Đăng kí giảng dạy</a></li>
			<b>
				<li class="nav-item"><a class="nav-link"> Sinhvien</a></li>
			</b>
			<li class="nav-item"><a href="/QuanLiLop/sinhvien?p=1"
				class="nav-link">Danh sách sinh viên</a></li>
			<li class="nav-item"><a href="/QuanLiLop/sinhvien?page=dang-ki-hoc&&p=1"
				class="nav-link">Đăng kí học</a></li>
		</ul>
	</nav>

</body>
</html>


