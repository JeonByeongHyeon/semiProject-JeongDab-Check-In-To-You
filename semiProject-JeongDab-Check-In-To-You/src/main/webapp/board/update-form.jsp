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
<title></title>
</head>
<div class="row">
	<div class="col">
		<c:import url="/fix/header.jsp" />
	</div>
</div>
<body>
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
	</div>
</body>
</html>