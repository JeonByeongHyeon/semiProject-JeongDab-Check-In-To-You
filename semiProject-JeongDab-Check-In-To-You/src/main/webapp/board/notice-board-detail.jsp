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
<title>Clean Blog - Start Bootstrap Theme</title>
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

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

</head>
<body>
	<div class="container-fluid pt-3">

		<div class="culmn">
			<%-- header 영역 --%>
			<div class="row header">
				<div class="col-sm-8 offset-sm-2" align="right">
					<c:import url="../fix/header.jsp" />
				</div>
			</div>
			<%-- main 영역 --%>
			<br> <br>

			<div class="row main">
				<div class="align-self-center">
					<table class="table table-bordered">

						<tr>
							<td id="boardNo">글번호: ${noticeBoard.noticeBoardNo}</td>
							<td>제목: ${noticeBoard.noticeBoardTitle}</td>

						</tr>
						<tr>
							<td>작성일: ${noticeBoard.noticeBoardDate}</td>
							<td>조회수: ${noticeBoard.noticeBoardHits}</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									<font size="6">내용</font>
								</div>
								<div style="display: flex; justify-content: left;">
									<pre>
                              <font size="6">${noticeBoard.noticeBoardContent}</font>
                           </pre>
								</div>
							</td>
						</tr>
					</table>



					<div style="display: flex; justify-content: right;">
						<button type="button" class="btn btn-primary"
							onclick="noticeBoardList()">목록</button>

						<form id="noticeBoardList" action="NoticeBoardList.do"
							method="post"></form>

						<c:if test="${sessionScope.member.memberStatus == 0 }">

							<button type="button" class="btn btn-primary"
								onclick="deleteNotice()">삭제</button>
							<form id="deleteNoticeForm" action="DeleteNoticeByNo.do" method="post">
								<input type="hidden" name="no"
									value="${noticeBoard.noticeBoardNo}">
							</form>


							<button type="button" class="btn btn-primary"
								onclick="updateNoticeBoard()">수정</button>
							<form id="updateNoticeBoard" action="UpdateNoticeBoardForm.do"
								method="post">
								<input type="hidden" name="no"
									value="${noticeBoard.noticeBoardNo}">
							</form>

						</c:if>
						<script type="text/javascript">
                     function deleteNotice() {
                        if (confirm("삭제하시겠습니까?")) {
                           document.getElementById("deleteNoticeForm")
                                 .submit();
                        }
                     }
                     function updateNoticeBoard() {
                        if (confirm("수정하시겠습니까?")) {
                           document.getElementById(
                                 "updateNoticeBoard").submit();
                        }
                     }
                     function noticeBoardList() {
                        if (confirm("게시글 목록으로 이동하시겠습니까?")) {
                           document.getElementById("noticeBoardList")
                                 .submit();
                        }
                     }
                  </script>


					</div>
				</div>
				<%--footer 영역 --%>
				<div class="row">
					<!-- 메인영역을 동적으로 import 해옴-->
					<c:import url="../fix/footer.jsp" />
				</div>
			</div>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/jquery@2.2.4/dist/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/board/assets/js/reply-list.js"></script>
</body>
</html>
