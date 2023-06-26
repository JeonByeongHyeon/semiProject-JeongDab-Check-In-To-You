function searchAddress() {
    new daum.Postcode({
        oncomplete: function (data) {
            let addr = '';
            if (data.userSelectedType === 'R') {
                addr = data.roadAddress;
            } else {
                addr = data.jibunAddress;
            }
            // jQuery를 사용하여 주소 필드에 값을 설정합니다.
            $('#address').val(addr);
            // 상세주소 필드로 커서를 이동합니다.
            $('#addressDetail').focus();
        }
    }).open();
}

function validateName() {
    // 이름 입력 필드에서 값을 가져옵니다.
    var name = $("#nameInput").val();

    // 한글로만 구성되어 있고 띄어쓰기가 없는지 확인하는 정규식
    var regex = /^[가-힣]+$/;

    // 정규식에 맞는지 검사합니다.
    if (!regex.test(name)) {
        $("#nameError").html("이름은 한글로만 입력해야 하며, 띄어쓰기를 포함할 수 없습니다.");
    } else {
        $("#nameError").html("");
    }
}
	function validateEmail() {
		var emailInput = $('#emailInput');
		var sendEmailButton = $('#sendEmailButton');
		var emailError = $('#emailError');

		var email = emailInput.val().trim();
		if (email === '') {
			emailError.text('');
			sendEmailButton.prop('disabled', true);
			return;
		}

		var emailRegex = /^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/;
		if (!emailRegex.test(email)) {
			emailError.text('유효하지 않은 이메일 주소입니다.');
			emailError.css('color', 'red');
			sendEmailButton.prop('disabled', true);
			return;
		}

		$.ajax({
			url: '../EmailCheckAjax.do',
			method: 'GET',
			data: { memberEmail: email },
			success: function(response) {
				if (response === 'duplicate') {
					emailError.text('이미 사용 중인 이메일입니다.');
					sendEmailButton.prop('disabled', true);
					emailError.css('color', 'red'); // 이미 사용 중인 이메일인 경우 텍스트 색상을 빨간색으로 설정
				} else {
					emailError.text('사용 가능한 이메일입니다.');
					sendEmailButton.prop('disabled', false);
					emailError.css('color', 'blue'); // 사용 가능한 이메일인 경우 텍스트 색상을 파란색으로 설정
				}
			},
			error: function(xhr, status, error) {
				console.error('Request failed. Status: ' + xhr.status);
			}
		});
	}

$(document).ready(function() {
    // 비밀번호가 8자리 이상이며, 최소 한 개의 특수 문자를 포함하고 있는지 검사하는 정규식
    var passwordRegex = /^(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,}$/;
    $('#password, #confirm_password').on('keyup', function() {
        var password = $("#password").val();
        var confirmPassword = $("#confirm_password").val();
        if (password !== confirmPassword) {
            $('#message').html('비밀번호가 일치하지 않습니다').css('color', 'red');
        } else {
            $('#message').html('비밀번호가 일치합니다').css('color', 'blue');
        }
        if (!passwordRegex.test(password)) {
            $('#message').html('비밀번호는 8자리 이상이며, 최소 한 개의 특수 문자를 포함해야 합니다').css('color', 'red');
        }
    });
});

function certificationEmail() {
    var email = $('#emailInput').val().trim();

    $.ajax({
        url: '../SendEmailAjax.do',
        method: 'GET',
        data: { email: email },
        success: function(response) {
            // 인증번호 전송 성공 시
            alert('인증번호가 이메일로 전송되었습니다.');
            // 추가적인 처리를 수행할 수 있습니다.
        },
        error: function(xhr, status, error) {
            console.error('Request failed. Status: ' + xhr.status);
            // 에러 처리를 수행할 수 있습니다.
        }
    });
}

function numberCheck() {
    var enteredCode = $('#verificationCodeInput').val().trim();
    var verificationCode = $('#verificationCode').text();

    if (enteredCode === verificationCode) {
        alert('인증번호가 일치합니다.');
        // 추가적인 처리를 수행할 수 있습니다.
    } else {
        alert('인증번호가 일치하지 않습니다.');
        // 추가적인 처리를 수행할 수 있습니다.
    }
}

function numberCheck() {
    var enteredCode = $('#verificationCodeInput').val().trim();
	console.log(enteredCode);

    $.ajax({
       	url: '../CheckVerificationCodeAjax.do',
        method: 'POST',
        data:"enteredCode=" +enteredCode,
        success: function(response) {
            if (response === 'success') {
                // 인증번호 일치
                alert('인증번호가 일치합니다.');
                // 추가적인 처리를 수행할 수 있습니다.
            } else {
                // 인증번호 불일치
                alert('인증번호가 일치하지 않습니다. 다시 입력해주세요.');
                $('#verificationCodeInput').val(''); // 입력 필드 비우기
                $('#verificationCodeInput').focus(); // 다시 입력 필드에 포커스 설정
            }
        },
        error: function(xhr, status, error) {
            console.error('요청이 실패했습니다. 상태 코드: ' + xhr.status);
        }
    });
}
