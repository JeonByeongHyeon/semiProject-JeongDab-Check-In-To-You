<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Navigation-->
<nav class="navbar navbar-expand-lg navbar-light" id="mainNav"
	style="background-color: skyblue;">
	<div class="container px-4 px-lg-5">
		<a class="navbar-brand" href="index.jsp">너에개 체크인</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarResponsive" aria-controls="navbarResponsive"
			aria-expanded="false" aria-label="Toggle navigation">
			Menu <i class="fas fa-bars"></i>
		</button>
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav ms-auto py-4 py-lg-0">
				<li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4"
					href="${pageContext.request.contextPath}/ServiceBoardList.do">봉사
						구인 게시판</a></li>
				<li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4"
					href="${pageContext.request.contextPath}/NoticeBoardList.do">공지사항</a></li>
				<li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4"
					href="contact.jsp">Contact</a></li>
				<c:choose>
					<c:when test="${not empty sessionScope.member}">
						<!-- 사용자가 로그인한 상태 -->
						<button class="btn btn-info btn-sm" onclick="logout()"
							style="border-radius: 20%;">로그아웃</button>
						<form method="post"
							action="${pageContext.request.contextPath}/Logout.do"
							id="logoutForm"></form>
						<button class="btn btn-info btn-sm" onclick="mypage()"
							style="border-radius: 20%;">마이페이지</button>
						<form method="post"
							action="${pageContext.request.contextPath}/UpdateMemberForm.do"
							id="mypageForm"></form>
					</c:when>
					<c:otherwise>
						<!-- 사용자가 로그아웃 상태 또는 로그인하지 않은 상태 -->
						<button class="btn btn-info btn-sm" onclick="login()"
							style="border-radius: 20%;">로그인</button>
						<button class="btn btn-info btn-sm"
							onclick="redirectToSignUp()" style="border-radius: 20%;">회원가입</button>
					</c:otherwise>
				</c:choose>

				<script type="text/javascript">
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
					function mypage() {
						// 로그아웃
						document.getElementById("mypageForm").submit();
					}
				</script>
			</ul>
		</div>
	</div>
</nav>