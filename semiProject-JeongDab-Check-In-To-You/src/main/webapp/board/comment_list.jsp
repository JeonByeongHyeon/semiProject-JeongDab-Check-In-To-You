<?xml version="1.0" encoding="UTF-8"?>
<%@page import="org.kosta.jeongdab.tou.utility.Utility"%>
<%@page import="org.kosta.jeongdab.tou.model.ReplyDAO"%>
<%@page import="org.kosta.jeongdab.tou.model.ReplyVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/xml; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- AJAX_COMMENT테이블에 저장된 모든 댓글을 검색하여 XML텍스트데이터로 응답하는 JSP문서 >> 값저장은 JSON텍스트로 --%>
<%
	// 전체검색
	ArrayList<ReplyVO> ajaxCommentList=ReplyDAO.getInstance().selectAjaxReplyList();
%>

<result>
	<% if (!ajaxCommentList.isEmpty()) { %>
		<code>success</code>
		<data><![CDATA[
			[
			<% for(int i=0; i<ajaxCommentList.size();i++) { %>
				<% if(i>0) { %>,<% } %>
				{"num":<%=ajaxCommentList.get(i).getNum()%>
				,"writer":"<%=Utility.toJSON(ajaxCommentList.get(i).getWriter())%>"
				,"content":"<%=Utility.toJSON(ajaxCommentList.get(i).getContent())%>"
				,"writeDate":"<%=ajaxCommentList.get(i).getWriteDate()%>"}
				
			<% } %>
			]
		]]></data>
	<% } else { %>
		<code>empty</code>
		<message><![CDATA[첫번째 댓글을 등녹해 주떼욤]]></message>
	<% } %>
</result>