let boardNo = $("#boardNo").text().trim().replace("글번호: ", "");

$(document).ready(function() {
	loadCommentList(); // 페이지 로딩 시 댓글 목록을 가져와서 표시
	console.log("시작");
	console.log(boardNo);
	function loadCommentList() {
    	$.ajax({
        	url: 'ReplyListAjax.do',
        	method: 'GET',
        	data: { boardNo: boardNo },
        	dataType: 'json',
        	success: function(response) {
            	var commentList = response; // 서버에서 받아온 댓글 목록 데이터
				console.log(commentList+"=================");
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

                   $('#commentList').append(commentItem);
               }
           },
           error: function(xhr, status, error) {
               console.error('댓글 목록 조회 실패: ' + error);
           }
       });
   	}
});