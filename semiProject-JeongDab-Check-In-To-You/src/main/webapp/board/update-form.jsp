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
<title>봉사구인게시글수정하기</title>
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
	<div class="container pt-3">
		<form method="post"
			action="${pageContext.request.contextPath}/UpdateServiceBoard.do">
			<input type="hidden" name="no" value="${serviceBoard.serviceBoardNo}">
			<table class="table">
				<tr>
					<td><input type="text" name="title" placeholder="글제목"
						required="required" value="${serviceBoard.serviceBoardTitle}"></td>
				</tr>
				<tr>
					<td><input type="text" name="serviceDate" placeholder="출국 날짜"
						required="required" value="${serviceBoard.serviceDate}"></td>
				</tr>
				<tr>
					<td><input type="text" name="nation" placeholder="나라 선택"
						required="required" value="${serviceBoard.nation}"></td>
				</tr>
				<tr>
					<td><input type="file" class="form-control-file border"
						name="file"></td>
				</tr>
				<tr>
					<td><textarea rows="10" class="form-control" name="content"
							required="required"> ${serviceBoard.serviceBoardContent}</textarea></td>
				</tr>

			</table>
			<div class="text-center">
				<button type="submit" class="btn btn-primary">확인</button>
				<button type="reset" class="btn btn-primary">취소</button>
			</div>
		</form>

<%--footer 영역 --%>
			<div class="row footer">
				<c:import url="/fix/footer.jsp" />
			</div>
		</div>
	</div>
	</div>
</body>
</html>