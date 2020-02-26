<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
<title>Quản lí đào tạo - Trường Đại Học Xây Dựng</title>
<link rel="stylesheet" href="<%=Common.url%>css/index.css">
</head>
<body style="margin: 0px; padding: 0px">
	<%@ include file="header.jsp" %>
	
	<marquee
		style="padding: 10px; font-size: 20px; font-weight: 500; color: #FFFF00; background-color: #08406F;">Thông
		báo gia hạn lần 3 đóng học phí học kỳ I năm học 2019-2020. Tin tức mới
		cập nhật(17/12/2019)</marquee>

	<div class="duoi"
		style="background-color: #E8F4FF; width: 100%; height: 500px; margin-top: -4px;">
		<div class="middle" style="margin: 0% 10%;">
			<div class="trai"
				style="width: 40%; float: left; background-color: #fff; margin-top: 30px; box-shadow: 3px 5px #c5c5c582;">
				<form action="/MySpringCoreSite/home" method="post">
					<h2 style="text-align: center; font-weight: 500; margin: 20px 0px;">ĐĂNG
						NHẬP</h2>
					<span class="data"> <i class="fas fa-user"></i> <input
						class="UAndP" type="text" name="username"
						placeholder="Tên đăng nhập *" required></span> <span class="data">
						<i class="fas fa-lock"></i> <input class="UAndP" type="password"
						name="password" placeholder="Mật khẩu *" required>
					</span> <input type="submit" value="Đăng nhập">
				</form>
			</div>
			<div class="trai"
				style="width: 48%; height: 389px; float: left; background-color: #fff; margin: 30px 0px 0px 70px; box-shadow: 3px 5px #c5c5c582;">
				<h2
					style="text-align: center; font-weight: 500; margin: 20px 0px; color: red;">Thông
					báo mới nhất</h2>
				<ul>
					<li><b>Thông báo học Giáo dục quốc phòng - an ninh cho K64
							(đợt 3 + đợt 4 năm học 2019-2020) </b>
						<p>- Thời gian học: Đợt 3 từ ngày 06/01/2019 đến 16/02/2019.</p>
						<p style="margin-left: 113px;">Đợt 4 từ ngày 25/5/2019 đến
							21/6/2020.</p></li>
					<li><b>Thông báo đăng ký học chương trình đại học thứ hai
							(học song bằng) học kỳ II năm học 2019-2020 </b>
						<p>
							- Sinh viên tải mẫu đơn :<a
								href="https://drive.google.com/file/d/1l28dN1-7G2oAaMh4EDON0yHIPwr0rShY/view">
								tại đây</a>
						</p></li>
					<li><b> </b>
						<p>
							- Danh sách chuyển mã Quốc phòng: <a href="#">xem tại đây</a>
						</p>
						<p>
							- Thời khóa biểu Quốc phòng tạm dự kiến HK2: <a href="#">xem
								tại đây</a>. (Sinh viên sẽ đăng ký chung với các môn văn hóa khi đến
							lịch đăng ký học phần cho HK2)
						</p></li>
				</ul>
			</div>
		</div>
	</div>

	<%@ include file="footer.jsp" %>
	
</body>
</html>