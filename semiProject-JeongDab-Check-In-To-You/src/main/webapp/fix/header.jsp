<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Navigation-->
<nav class="navbar navbar-expand-lg navbar-light" id="mainNav"
	style="background-color: skyblue;">
	<div class="container px-4 px-lg-5">
		<a class="navbar-brand" href="index.html">Start Bootstrap</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarResponsive" aria-controls="navbarResponsive"
			aria-expanded="false" aria-label="Toggle navigation">
			Menu <i class="fas fa-bars"></i>
		</button>
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav ms-auto py-4 py-lg-0">
				<li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4"
					href="${pageContext.request.contextPath}/ServiceBoardList.do">봉사 구인 게시판</a></li>
				<li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4"
					href="about.html">후기 게시판</a></li>
				<li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4"
					href="post.html">공지사항</a></li>
				<li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4"
					href="contact.html">Contact</a></li>
				<c:choose>
					<c:when test="${not empty sessionScope.member}">
						<!-- 사용자가 로그인한 상태 -->
						<button class="btn btn-outline-warning btn-sm" onclick="logout()">로그아웃</button>
						<form method="post"
							action="${pageContext.request.contextPath}/Logout.do"
							id="logoutForm"></form>
					</c:when>
					<c:otherwise>
						<!-- 사용자가 로그아웃 상태 또는 로그인하지 않은 상태 -->
						<button class="btn btn-outline-warning btn-sm" onclick="login()">로그인</button>
						<button class="btn btn-outline-warning btn-sm"
							onclick="redirectToSignUp()">회원가입</button>
					</c:otherwise>
				</c:choose>

				<script>
					function login() {
						// 로그인 페이지로 이동
						window.location.href = "${pageContext.request.contextPath}/member/login.jsp";
					}

					function redirectToSignUp() {
						// 회원가입 페이지로 이동
						window.location.href = "${pageContext.request.contextPath}/member/register.jsp";
					}
					function logout() {
						// 로그아웃
						document.getElementById("logoutForm").submit();
					}
				</script>
			</ul>
		</div>
	</div>
</nav>