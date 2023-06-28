<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
<aside class="side-bar" style="width: 265px; padding-top: 115px;">
	<section class="side-bar__icon-box"></section>
	<ul>
		<li><a
			href="${pageContext.request.contextPath}/UpdateMemberForm.do">회원
				정보 변경</a></li>
		<li><a
			href="${pageContext.request.contextPath}/member/change-password.jsp">비밀번호
				변경</a></li>
		<li><a
			href="${pageContext.request.contextPath}/member/withdrawal-member.jsp">회원탈퇴</a></li>
	</ul>
</aside>