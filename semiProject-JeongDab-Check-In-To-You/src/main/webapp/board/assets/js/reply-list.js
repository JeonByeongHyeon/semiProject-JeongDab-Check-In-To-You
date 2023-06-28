let boardNo = $("#boardNo").text().trim().replace("글번호: ", "");

$(document).ready(function() {
	loadCommentList(); // 페이지 로딩 시 댓글 목록을 가져와서 표시

	function loadCommentList() {
		// 현재 사용자 정보 가져오기
		$.ajax({
			url: 'FindReplyRegisterMemberAjax.do',
			method: 'GET',
			dataType: 'json',
			success: function(user) {
				var currentUserNo = user; // 현재 사용자의 번호 가져오기

				// 댓글 목록 조회
				$.ajax({
					url: 'ReplyListAjax.do',
					method: 'GET',
					data: { boardNo: boardNo },
					dataType: 'json',
					success: function(response) {
						var commentList = response; // 서버에서 받아온 댓글 목록 데이터

						// 댓글 목록 동적으로 추가
						for (var i = 0; i < commentList.length; i++) {
							var comment = commentList[i];

							var commentItem = $('<li>');
							var writer = $('<span>').text(comment.memberVO.memberName);
							var date = $('<span>').text(comment.replyDate);
							var content = $('<span>').text(comment.replyContent);

							commentItem.append(content);
							commentItem.append(writer);
							commentItem.append(date);

							// 작성자와 현재 사용자 번호 비교하여 수정 및 삭제 버튼 보여주기
							if (comment.memberVO.memberNo == currentUserNo) {
								var editButton = $('<button>').text('수정');
								var deleteButton = $('<button>').text('삭제');
								editButton.click(function() {
									// 수정 버튼 클릭 시 동작할 코드 작성
									// 해당 댓글을 수정하는 로직을 구현하면 됩니다.
								});
								deleteButton.click(function() {
									// 삭제 버튼 클릭 시 동작할 코드 작성
									// 해당 댓글을 삭제하는 로직을 구현하면 됩니다.
								});
								commentItem.append(editButton);
								commentItem.append(deleteButton);
							}

							$('#commentList').append(commentItem);
						}
					},
					error: function(xhr, status, error) {
						console.error('댓글 목록 조회 실패: ' + error);
					}
				});
			},
			error: function(xhr, status, error) {
				console.error('현재 사용자 정보 조회 실패: ' + error);
			}
		});
	}
});
