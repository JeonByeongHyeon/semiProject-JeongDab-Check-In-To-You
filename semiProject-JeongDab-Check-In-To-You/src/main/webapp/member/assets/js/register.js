let nameFlag = false;
let emailFlag = false;
let passwordFlag = false;
let numberFlag = false;
    
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
    let name = $("#nameInput").val();
    let regex = /^[가-힣]+$/;

    if (!regex.test(name)) {
        $("#nameError").html("이름은 한글로만 입력해야 하며, 띄어쓰기를 포함할 수 없습니다.");
        nameFlag = false;
    } else {
        $("#nameError").html("");
        nameFlag = true;
    }

    enableSignupButton();
}

function validateEmail() {
    let emailInput = $('#emailInput');
    let sendEmailButton = $('#sendEmailButton');
    let emailError = $('#emailError');

    let email = emailInput.val().trim();
    if (email === '') {
        emailError.text('');
        sendEmailButton.prop('disabled', true);
        emailFlag = false;
        return;
    }

    let emailRegex = /^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/;
    if (!emailRegex.test(email)) {
        emailError.text('유효하지 않은 이메일 주소입니다.');
        emailError.css('color', 'red');
        sendEmailButton.prop('disabled', true);
        emailFlag = false;
        return;
    }

    $.ajax({
        url: '../EmailCheckAjax.do',
        method: 'GET',
        data: { memberEmail: email },
        success: function(response) {
            if (response == 'duplicate') {
                emailError.text('이미 사용 중인 이메일입니다.');
                sendEmailButton.prop('disabled', true);
                emailError.css('color', 'red');
                emailFlag = false;
            } else {
                emailError.text('사용 가능한 이메일입니다.');
                sendEmailButton.prop('disabled', false);
                emailError.css('color', 'blue');
                emailFlag = true;
            }

            enableSignupButton();
        },
        error: function(xhr, status, error) {
            console.error('Request failed. Status: ' + xhr.status);
        }
    });
}

$(document).ready(function() {
    let passwordRegex = /^(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,}$/;

    $('#password').on('keyup', function() {
        let password = $(this).val();
        let message = '';

        if (!passwordRegex.test(password)) {
            message = '비밀번호는 8자 이상이며, 최소한 하나의 특수 문자를 포함해야 합니다.';
            passwordFlag = false;
        } else {
            passwordFlag = true;
        }

        $('#message').text(message).css('color', 'red');
        enableSignupButton();
    });

    $('#confirm_password').on('keyup', function() {
        let password = $('#password').val();
        let confirmPassword = $(this).val();

        if (password !== confirmPassword) {
            $('#message').text('비밀번호가 일치하지 않습니다.').css('color', 'red');
            passwordFlag = false;
        } else if (password == confirmPassword) {
            $('#message').text('비밀번호가 일치합니다').css('color', 'blue');
            passwordFlag = true;
        }

        enableSignupButton();
    });
});




function certificationEmail() {
    let email = $('#emailInput').val().trim();

    $.ajax({
        url: '../SendEmailAjax.do',
        method: 'GET',
        data: { email: email },
        success: function(response) {
            alert('인증번호가 이메일로 전송되었습니다.');
        },
        error: function(xhr, status, error) {
            console.error('Request failed. Status: ' + xhr.status);
        }
    });
}

function numberCheck() {
    let enteredCode = $('#verificationCodeInput').val().trim();

    $.ajax({
        url: '../CheckVerificationCodeAjax.do',
        method: 'POST',
        data: {
            enteredCode: enteredCode
        },
        success: function(response) {
            if (response === 'success') {
                alert('인증번호가 일치합니다.');
                numberFlag = true;
                $('#password').focus();
            } else {
                alert('인증번호가 일치하지 않습니다. 다시 입력해주세요.');
                $('#verificationCodeInput').val('');
                $('#verificationCodeInput').focus();
                numberFlag = false;
            }

            enableSignupButton();
        },
        error: function(xhr, status, error) {
            console.error('요청이 실패했습니다. 상태 코드: ' + xhr.status);
        }
    });
}

function enableSignupButton() {
    if (nameFlag && emailFlag && passwordFlag && numberFlag) {
        $("#signupButton").prop("disabled", false);
    } else {
        $("#signupButton").prop("disabled", true);
    }
}