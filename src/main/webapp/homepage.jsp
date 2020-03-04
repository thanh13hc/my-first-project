<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quản lí lớp</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<!--  <link rel="stylesheet" href="css/my-style.css"> -->
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
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
	<%@include file="header.jsp"%>

	<div class="container-fluid">
		<div id="slide" class="carousel slide" data-ride="arousel">
			<ol class="carousel-indicators">
				<li data-target="#slide" data-slide-to="0" class="active"></li>
				<li data-target="#slide" data-slide-to="1"></li>
				<li data-target="#slide" data-slide-to="2"></li>
			</ol>
			<div class="carousel-inner" role="listbox">
				<div class="carousel-item active">
					<img src="https://images.pexels.com/photos/7101/wood-coffee-iphone-notebook.jpg?auto=compress&cs=tinysrgb&dpr=1&w=1366" alt="First slide" class="img-fluid">
				</div>
				<div class="carousel-item">
					<img src="https://images.pexels.com/photos/1925536/pexels-photo-1925536.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=1366" alt="Second slide" class="img-fluid">
				</div>
				<div class="carousel-item">
					<img src="https://images.pexels.com/photos/6384/woman-hand-desk-office.jpg?auto=compress&cs=tinysrgb&dpr=1&w=1366" alt="Third slide" class="img-fluid" />
				</div>
			</div>
			<a class="carousel-control-prev" href="#slide" role="button"
				data-slide="prev"> <span class="carousel-control-prev-icon"
				aria-hidden="true"></span> <span class="sr-only">Previous</span>
			</a> <a class="carousel-control-next" href="#slide" role="button"
				data-slide="next"> <span class="carousel-control-next-icon"
				aria-hidden="true"></span> <span class="sr-only">Next</span>
			</a>
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

	<%@include file="footer.jsp"%>
</body>
</html>


