<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<meta name="description" content="Ela Admin - HTML5 Admin Template">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="apple-touch-icon" href="https://i.imgur.com/QRAUqs9.png">
<link rel="shortcut icon" href="https://i.imgur.com/QRAUqs9.png">

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/normalize.css@8.0.0/normalize.min.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/font-awesome@4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/gh/lykmapipo/themify-icons@0.1.2/css/themify-icons.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/pixeden-stroke-7-icon@1.2.3/pe-icon-7-stroke/dist/pe-icon-7-stroke.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/flag-icon-css/3.2.0/css/flag-icon.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/member/assets/css/cs-skin-elastic.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/member/assets/css/style.css">
<link
	href='https://fonts.googleapis.com/css?family=Open+Sans:400,600,700,800'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/fix/assets/css/side.css">
<link
	href="${pageContext.request.contextPath}/fix/assets/css/styles.css"
	rel="stylesheet" />
<title>너에개 체크인</title>
</head>
<%-- header 영역 --%>
<div class="row header">
	<c:import url="../fix/header.jsp" />
</div>
<%-- side --%>
<div class="row header">
	<c:import url="../fix/side.jsp" />
</div>
<body class="bg-white">
	<div class="sufee-login d-flex align-content-center flex-wrap">
		<div class="container">
		<br>
		<br>
		<br>
		<br>
			<div class="login-content">
				<div class="login-logo">
					<a href="index.html"> <img class="align-content"
						src="${pageContext.request.contextPath}/img/logo1.png" alt="">
					</a>
				</div>
				<div class="login-form">
					<form
						action="${pageContext.request.contextPath}/WithdrawalMember.do"
						method="post">
						<span id="verificationResult" style="color: red;"></span>
						<div class="form-group">
							<label>비밀번호 입력 후 회원 탈퇴</label> <input type="password" name="password"
								id="password" class="form-control" placeholder="비밀번호"
								required="required">
						</div>
						<span id="message" style="color: blue;"></span>
						<button type="submit" id="signupButton"
							class="btn btn-primary btn-flat m-b-30 m-t-30" disabled>회원
							탈퇴</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/jquery@2.2.4/dist/jquery.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.14.4/dist/umd/popper.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/jquery-match-height@0.7.2/dist/jquery.matchHeight.min.js"></script>
	<script
		src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script
		src="${pageContext.request.contextPath}/member/assets/js/main.js"></script>
	<script
		src="${pageContext.request.contextPath}/member/assets/js/withdrawal-member.js"></script>

</body>
</html>