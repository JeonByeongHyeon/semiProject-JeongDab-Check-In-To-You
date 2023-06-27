<?xml version="1.0" encoding="UTF-8"?>
<%@page import="org.kosta.jeongdab.tou.model.ReplyDAO"%>
<%@page import="org.kosta.jeongdab.tou.model.ReplyVO"%>
<%@ page language="java" contentType="text/xml; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 댓글 정보를 전달받아 AJAX_COMMENT테이블에 저장하고 저장결과를 XML텍스트데이터로 응답하는 JSP문서 --%>
<%
	// 비정상적인 요청에대한 처리
	if(request.getMethod().equals("GET")) {
		response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
		return;
	}

	// 한글 입력값에 대한 처리
	request.setCharacterEncoding("UTF-8");
	
	String writer=request.getParameter("writer");
	String content=request.getParameter("content");
	
	ReplyVO ajaxComment=new ReplyVO();
	
	ajaxComment.setReplyContent(content);
	/* 
	// 저장을 실패할 경우를 대비하여 아래같이 rows받아서xml문서에 활용
string rows=ReplyDAO.getInstance().insertReply(ajaxComment); */
%>
<%-- <result>
	<% if (rows>0) { %>
		<code>success</code>
	<% } else { %>
		<code>fail</code>
	<% } %>
</result> --%>