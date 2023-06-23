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
<title>너에개 체크인</title>
</head>
<body class="bg-white">

	<div class="sufee-login d-flex align-content-center flex-wrap">
		<div class="container">
			<div class="login-content">
				<div class="login-logo">
					<a href="index.html"> <img class="align-content"
						src="${pageContext.request.contextPath}/img/logo1.png" alt="">
					</a>
				</div>
				<div class="login-form">
					<form action="../RegisterMember.do" method="post">
						<div class="form-group">
							<label>이름</label> <input type="text" name ="name" class="form-control"
								placeholder="이름" required="required">
						</div>
						<label>이메일</label>
						<div class="input-group">
							<input type="email" name="email" class="form-control" placeholder="이메일"
								required="required">
								<!--  인증번호 전송기능 
							<div class="input-group-append">
								<button type="button" class="btn btn-info"
									onclick="certificationEmail()">인증번호 전송</button>
							</div>
							-->
						</div>
						<!--  인증번호 확인 기능
						<div class="input-group">
							<input type="text" class="form-control" placeholder="인증번호"
								required="required">
							<div class="input-group-append">
								<button type="button" class="btn btn-info"
									onclick="numberCheck()">인증번호 확인</button>
							</div>
						</div>
						-->
						<span style="color: blue;">인증번호가 일치합니다(동적으로 일치 불일치 검사)</span>
						<div class="form-group">
							<label>비밀번호</label> <input type="password" name="password" class="form-control"
								placeholder="비밀번호" required="required">
						</div>
						<!--  비밀번호 확인 기능
						<div class="form-group">
							<input type="password" class="form-control" placeholder="비밀번호 확인"
								required="required"> <span style="color: blue;">사용
								가능한 비밀번호 입니다(ajax로 구현)</span>
						</div>
						-->
						<div class="form-group">
							<label>생년월일</label> <input type="date" name="birth" class="form-control"
								placeholder="" required="required">
						</div>
						<div class="form-group">
							<label>주소</label>
							  
							<div class="input-group">
								<input type="text" class="form-control" name="address"
									id="address" placeholder="주소 검색 버튼으로 주소 선택" required="required"
									readonly>
									
								<div class="input-group-append">
									<button type="button" class="btn btn-info"
										onclick="searchAddress()">주소 검색</button>
								</div>
								
							</div>
							
							
							<input type="text" name="address"class="form-control" name="addressDetail"
								id="addressDetail" placeholder="상세주소">
						</div>
						<button type="submit"
							class="btn btn-primary btn-flat m-b-30 m-t-30">회원가입</button>

						<div class="register-link m-t-15 text-center">
							<p>
								이미 계정이 있습니까 ? <a href="#"> 로그인</a>
							</p>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

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
		src="${pageContext.request.contextPath}/member/assets/js/register.js"></script>

</body>
</html>