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
<title>공지사항 게시판</title>
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
	<%-- header 영역 --%>
	<div class="row header">
		<c:import url="/fix/header.jsp" />
	</div>
	<div class="container-xxl py-5">
		<div class="text-center mx-auto mb-5 wow fadeInUp"
			data-wow-delay="0.1s" style="max-width: 500px;">
			<h1 class="display-6 mb-4">공지사항 게시판</h1>
		</div>
		<div class="row g-4">
			<%--시작 --%>

			<c:forEach items="${list}" var="board">
				<div class="col-lg-4 col-md-6 wow fadeInUp" data-wow-delay="0.1s">
					<c:choose>
						<c:when test="${sessionScope.member==null }">
							<a class="service-item d-block rounded text-center h-100 p-4"
								href="#">
								<img class="img-fluid rounded mb-4"
								src="${pageContext.request.contextPath}/board/assets/img/noticeDog.jpg"
								alt="">
ㄴㄴㄴㄴ
								<h4 class="mb-0">${board.noticeBoardTitle}</h4>
								<div>
									<h6 class="mb-0">조회수:${board.noticeBoardHits}&nbsp;&nbsp;등록일:${board.noticeBoardDate}</h6>
								</div>
							</a>
						</c:when>
						<c:otherwise>
							<a class="service-item d-block rounded text-center h-100 p-4"
								href="${pageContext.request.contextPath}/FindNoticeBoardByNo.do?no=${board.noticeBoardNo}">
								<img class="img-fluid rounded mb-4"
								src="${pageContext.request.contextPath}/board/assets/img/noticeDog.jpg"
								alt="">

								<h4 class="mb-0">${board.noticeBoardTitle}</h4>
								<div>
									<h6 class="mb-0">조회수:${board.noticeBoardHits}&nbsp;&nbsp;등록일:${board.noticeBoardDate}</h6>
								</div>
							</a>
						</c:otherwise>

					</c:choose>
				</div>
			</c:forEach>
			<div>
				<c:if test="${sessionScope.member.memberStatus == 0 }">
					<button type="submit" class="btn btn-primary" onclick="writeForm()">글쓰기</button>
				</c:if>
			</div>

			<%--끝 --%>

			<!--
      pagination 
-->
			<ul class="pagination justify-content-center" style="margin: 20px 0">
				<c:if test="${pagination.previousPageGroup}">
					<li class="page-item"><a class="page-link"
						href="ServiceBoardList.do?pageNo=${pagination.startPageOfPageGroup-1}">Previous</a></li>
				</c:if>
				<c:forEach begin="${pagination.startPageOfPageGroup}"
					end="${pagination.endPageOfPageGroup}" var="page">
					<c:choose>
						<c:when test="${pagination.nowPage==page}">
							<li class="page-item active"><a class="page-link"
								href="ServiceBoardList.do?pageNo=${page}">${page}</a></li>
						</c:when>
						<c:otherwise>
							<li class="page-item"><a class="page-link"
								href="ServiceBoardList.do?pageNo=${page}">${page}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${pagination.nextPageGroup}">
					<li class="page-item"><a class="page-link"
						href="ServiceBoardList.do?pageNo=${pagination.endPageOfPageGroup+1}">Next</a></li>
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
	<script type="text/javascript">
		function writeForm() {
			console.log("누름");
			location.href = "${pageContext.request.contextPath}/board/notice-board-write-post.jsp";
		}
	</script>

	<%--footer 영역 --%>
	<div class="row footer">
		<c:import url="/fix/footer.jsp" />
	</div>

</body>
</html>