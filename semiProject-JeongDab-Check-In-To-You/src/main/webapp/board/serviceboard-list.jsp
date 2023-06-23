<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<title>봉사 구인 게시판</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;500;600;700&display=swap"
	rel="stylesheet">

<!-- Icon Font Stylesheet -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css"
	rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css"
	rel="stylesheet">

<!-- Libraries Stylesheet -->
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
</head>
			<%-- header 영역 --%>
			<div class="row header">
				
					<c:import url="/fix/header.jsp" />
				
			</div>
<body>
	<div class="container-xxl py-5">
		<div class="container">
			<div class="text-center mx-auto mb-5 wow fadeInUp"
				data-wow-delay="0.1s" style="max-width: 600px;">
				<h1 class="display-6 mb-4">봉사 구인 게시판</h1>
			</div>
			<div class="row g-4">
				<%--시작 --%>
				<%-- <c:forEach begin="1" end="6" var="num" >--%>
				<c:forEach items="${list}" var="board">
					<div class="col-lg-4 col-md-6 wow fadeInUp" data-wow-delay="0.1s">
						<a class="service-item d-block rounded text-center h-100 p-4"
							href="#"> <img class="img-fluid rounded mb-4"
							src="${pageContext.request.contextPath}/board/assets/img/service-1.jpg"
							alt="">
							<h4 class="mb-0">${board.serviceBoardTitle}</h4>
							<div>
								<h6 class="mb-0">${board.serviceBoardHits}&nbsp;&nbsp;${board.serviceBoardCreateDate}</h6>
							</div>
						</a>
					</div>
				</c:forEach>
				<%--</c:forEach>--%>
				<%--끝 --%>

				<!--
      pagination 
-->
				<ul class="pagination justify-content-center" style="margin: 20px 0">
					<c:if test="${pagination.previousPageGroup}">
						<li class="page-item"><a class="page-link"
							href="FindPostList.do?pageNo=${pagination.startPageOfPageGroup-1}">Previous</a></li>
					</c:if>
					<c:forEach begin="${pagination.startPageOfPageGroup}"
						end="${pagination.endPageOfPageGroup}" var="page">
						<c:choose>
							<c:when test="${pagination.nowPage==page}">
								<li class="page-item active"><a class="page-link"
									href="FindPostList.do?pageNo=${page}">${page}</a></li>
							</c:when>
							<c:otherwise>
								<li class="page-item"><a class="page-link"
									href="FindPostList.do?pageNo=${page}">${page}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:if test="${pagination.nextPageGroup}">
						<li class="page-item"><a class="page-link"
							href="FindPostList.do?pageNo=${pagination.endPageOfPageGroup+1}">Next</a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/board/assets/lib/wow/wow.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/board/assets/lib/easing/easing.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/board/assets/lib/waypoints/waypoints.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/board/assets/lib/counterup/counterup.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/board/assets/lib/owlcarousel/owl.carousel.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/board/assets/lib/lightbox/js/lightbox.min.js"></script>
</body>
</html>