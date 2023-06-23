<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<!--start header footer 관련 링크 -->
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Clean Blog - Start Bootstrap Theme</title>
<link rel="icon" type="image/x-icon" href="fix/assets/favicon.ico" />
<!-- Font Awesome icons (free version)-->
<script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js"
	crossorigin="anonymous"></script>
<!-- Google fonts-->
<link
	href="https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic"
	rel="stylesheet" type="text/css" />
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800"
	rel="stylesheet" type="text/css" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="fix/assets/css/styles.css" rel="stylesheet" />
<!--end header footer 관련 링크 -->



<!-- start board 관련 링크 -->
<link
	href="${pageContext.request.contextPath}/board/assets/lib/animate/animate.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/board/assets/lib/owlcarousel/assets/owl.carousel.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/board/assets/lib/lightbox/css/lightbox.min.css"
	rel="stylesheet">

<!-- Customized Bootstrap Stylesheet -->
<link
	href="${pageContext.request.contextPath}/board/assets/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Template Stylesheet -->
<link
	href="${pageContext.request.contextPath}/board/assets/css/style.css"
	rel="stylesheet">
<!-- end board 관련 링크 -->


</head>
<body>
	<div class="container-fluid pt-3">

		<div class="culmn">
			<%-- header 영역 --%>
			<div class="row header">
				<div class="col-sm-8 offset-sm-2" align="right">
					<c:import url="/fix/header.jsp" />
				</div>
			</div>
			<%-- main 영역 --%>
			<div class="container-xxl py-5">
				<div class="container">
					<div class="text-center mx-auto mb-5 wow fadeInUp"
						data-wow-delay="0.1s" style="max-width: 600px;">
						<h1 class="display-6 mb-4">봉사 구인 게시판</h1>
					</div>
					<div class="row g-4">
						<div class="col-lg-4 col-md-6 wow fadeInUp" data-wow-delay="0.1s">
							<a class="service-item d-block rounded text-center h-100 p-4"
								href="#"> <img class="img-fluid rounded mb-4"
								src="${pageContext.request.contextPath}/board/assets/img/service-1.jpg"
								alt="">
								<h4 class="mb-0">봉사 구인 합니다</h4>
								<div>
									<h6 class="mb-0">박해준&nbsp;&nbsp;2023-06-20</h6>
								</div>
							</a>
						</div>
						<div class="col-lg-4 col-md-6 wow fadeInUp" data-wow-delay="0.1s">
							<a class="service-item d-block rounded text-center h-100 p-4"
								href="#"> <img class="img-fluid rounded mb-4"
								src="${pageContext.request.contextPath}/board/assets/img/service-1.jpg"
								alt="">
								<h4 class="mb-0">봉사 구인 합니다</h4>
								<div>
									<h6 class="mb-0">박해준&nbsp;&nbsp;2023-06-20</h6>
								</div>
							</a>
						</div>
						<div class="col-lg-4 col-md-6 wow fadeInUp" data-wow-delay="0.1s">
							<a class="service-item d-block rounded text-center h-100 p-4"
								href="#"> <img class="img-fluid rounded mb-4"
								src="${pageContext.request.contextPath}/board/assets/img/service-1.jpg"
								alt="">
								<h4 class="mb-0">봉사 구인 합니다</h4>
								<div>
									<h6 class="mb-0">박해준&nbsp;&nbsp;2023-06-20</h6>
								</div>
							</a>
						</div>
						<div class="col-lg-4 col-md-6 wow fadeInUp" data-wow-delay="0.1s">
							<a class="service-item d-block rounded text-center h-100 p-4"
								href="#"> <img class="img-fluid rounded mb-4"
								src="${pageContext.request.contextPath}/board/assets/img/service-1.jpg"
								alt="">
								<h4 class="mb-0">봉사 구인 합니다</h4>
								<div>
									<h6 class="mb-0">박해준&nbsp;&nbsp;2023-06-20</h6>
								</div>
							</a>
						</div>
						<div class="col-lg-4 col-md-6 wow fadeInUp" data-wow-delay="0.1s">
							<a class="service-item d-block rounded text-center h-100 p-4"
								href="#"> <img class="img-fluid rounded mb-4"
								src="${pageContext.request.contextPath}/board/assets/img/service-1.jpg"
								alt="">
								<h4 class="mb-0">봉사 구인 합니다</h4>
								<div>
									<h6 class="mb-0">박해준&nbsp;&nbsp;2023-06-20</h6>
								</div>
							</a>
						</div>
						<div class="col-lg-4 col-md-6 wow fadeInUp" data-wow-delay="0.1s">
							<a class="service-item d-block rounded text-center h-100 p-4"
								href="#"> <img class="img-fluid rounded mb-4"
								src="${pageContext.request.contextPath}/board/assets/img/service-1.jpg"
								alt="">
								<h4 class="mb-0">봉사 구인 합니다</h4>
								<div>
									<h6 class="mb-0">박해준&nbsp;&nbsp;2023-06-20</h6>
								</div>
							</a>
							</div>
						</div>
					</div>
				</div>
				<%--footer 영역 --%>
				<div class="row">
					<!-- 메인영역을 동적으로 import 해옴-->
					<c:import url="/fix/footer.jsp" />
				</div>
			</div>
		</div>
		</div>
</body>
</html>
