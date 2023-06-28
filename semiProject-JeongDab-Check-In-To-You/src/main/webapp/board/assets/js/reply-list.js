let boardNo = $("#boardNo").text().trim().replace("글번호: ", "");

$(document).ready(function() {

	loadCommentList(); // 페이지 로딩 시 댓글 목록을 가져와서 표시
	$("#replyInsertBtn").click(function() {
		let replyContent = $("#replyContent").val();
		if (replyContent == "") {
			alert("댓글을 입력하세요");
			return;
		}
		$.ajax({
			type: "post",
			url: "RegisterReply.do",
			data: { replyContent: replyContent, boardNo: boardNo },
			success: function(result) {
				location.reload();
			}

		});

	});
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
							var writer = $('<span>').text(" 작성자 : "+comment.memberVO.memberName);
							var date = $('<span>').text(" 작성일 : "+comment.replyDate);
							var content = $('<span>').text(" 댓글 내용 : "+comment.replyContent);

							commentItem.append(content);
							commentItem.append(writer);
							commentItem.append(date);

							// 작성자와 현재 사용자 번호 비교하여 수정 및 삭제 버튼 보여주기
							if (comment.memberVO.memberNo == currentUserNo) {
								var editButton = $('<button>').text('수정');
								var deleteButton = $('<button>').text('삭제');
								// 댓글 번호를 버튼의 data 속성에 저장
								editButton.data('commentId', comment.replyNo);
								deleteButton.data('commentId', comment.replyNo);
								editButton.click(function() {
									var commentId = $(this).data('commentId'); // 저장된 댓글 번호 가져오기
									var commentContent = $(this).siblings('span').eq(0).text();
									console.log(commentContent);
									// 수정 버튼 클릭 시 동작할 코드 작성
									// 해당 댓글을 수정하는 로직을 구현하면 됩니다.
									console.log("수정 버튼 클릭 - 댓글 ID: " + commentId);
									createEditForm(commentId,commentContent, commentItem);
								});
								deleteButton.click(function() {
									var commentId = $(this).data('commentId'); // 저장된 댓글 번호 가져오기
									// 삭제 버튼 클릭 시 동작할 코드 작성
									// 해당 댓글을 삭제하는 로직을 구현하면 됩니다.
									console.log("삭제 버튼 클릭 - 댓글 ID: " + commentId);
									if (confirm("삭제하시겠습니까?")) {
										$.ajax({
											url: 'DeleteReplyAjax.do',
											method: 'GET',
											data: { commentId: commentId },
											success: function(message) {
												if (message == 'success') {
													// 삭제 요청이 성공한 경우, 해당 댓글을 화면에서도 제거
													commentItem.remove();



													location.reload();

												}
											},
											error: function(xhr, status, error) {
												console.error('댓글 삭제 요청 실패: ' + error);
											}
										});
									}
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
	
function createEditForm(commentId, commentContent, commentItem) {
  // 기존의 수정 폼이 있을 경우 제거
  commentItem.find('.edit-form').remove();

  // 수정 폼 요소 생성
  var editForm = $('<form>').addClass('edit-form');
  var contentInput = $('<input>').attr('type', 'text').val(commentContent);
  var submitButton = $('<button>').text('확인');

  // 폼 제출 처리
  editForm.submit(function(event) {
    event.preventDefault();
    var updatedContent = contentInput.val(); // 수정된 댓글 내용 가져오기

    // AJAX를 사용하여 댓글 업데이트 또는 서버로 폼 전송
    // 예시로 AJAX 코드 작성:
    $.ajax({
      url: 'UpdateReply.do',
      method: 'POST',
      data: { commentId: commentId, content: updatedContent },
      success: function(response) {
        console.log('댓글 수정 완료');
        // DOM에서 댓글 내용 업데이트
        commentItem.find('span').eq(0).text(updatedContent);
        // 수정 폼 제거
        editForm.remove();
        // 화면에 변경된 내용을 즉시 반영하려면 location.reload() 대신에 아래 코드를 사용할 수 있습니다.
        // commentItem.find('span').eq(0).text(updatedContent);
      },
      error: function(xhr, status, error) {
        console.error('댓글 수정 실패: ' + error);
      }
    });
  });

  // 폼 요소를 댓글 아이템에 추가
  commentItem.append(editForm);
  editForm.append(contentInput);
  editForm.append(submitButton);
}
});

