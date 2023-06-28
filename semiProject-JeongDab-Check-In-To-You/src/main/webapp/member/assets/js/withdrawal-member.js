let passwordFlag = false;

$('#password').on('input', function() {
    let password = $('#password').val();
    // AJAX를 사용하여 비밀번호 확인 요청
    $.ajax({
        url: '../CheckPasswordAjax.do',
        method: 'GET',
        data: { password: password },
        success: function(response) {
            if (response === 'success') {
                // 비밀번호가 일치하는 경우, 메시지를 표시
                $('#message').text('비밀번호가 확인되었습니다').css('color', 'blue');
                passwordFlag = true;
            } else {
                // 비밀번호가 일치하지 않는 경우, 에러 메시지 표시
                $('#message').text('비밀번호가 일치하지 않습니다.').css('color', 'red');
                passwordFlag = false;
            }
            enableSignupButton(); // enableSignupButton() 호출하여 버튼 상태 업데이트
        },
        error: function(xhr, status, error) {
            console.error('비밀번호 확인 요청 실패: ' + error);
        }
    });
});
$('#signupButton').on('click', function(e) {
        e.preventDefault();
        if (confirm("회원 탈퇴하시겠습니까?")) {
            $(this).closest('form').submit();
        }
    });
function enableSignupButton() {
    if (passwordFlag) {
        $("#signupButton").prop("disabled", false);
    } else {
        $("#signupButton").prop("disabled", true);
    }
}