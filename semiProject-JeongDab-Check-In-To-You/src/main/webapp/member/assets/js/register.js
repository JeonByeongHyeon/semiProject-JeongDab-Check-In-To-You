function searchAddress() {
    new daum.Postcode({
        oncomplete: function (data) {
            // 주소를 가져오는 로직을 변경해야 합니다.
            let addr = ''; // 주소 변수

            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 주소 필드에 값을 설정하는 방식을 변경해야 합니다.
            // document.getElementById('zipcode').value = data.zonecode;
            document.getElementById('address').value = addr;

            // 상세주소 필드로 커서를 이동하는 부분은 유지합니다.
            document.getElementById('addressDetail').focus();

            // 기타 필요한 로직을 추가하거나 수정합니다.
            // addressCheckFlag = true;
            // $('#searchAddressBtn').parent().next().hide();
        }
    }).open();
}
$(document).ready(function() {
	$('#nameInput').on('input', function() {
		const name = $(this).val();
		const koreanNamePattern = /^[가-힣]+$/;
		const hasConsonantOrVowelOnly = /[^가-힣]*[ㄱ-ㅎㅏ-ㅣ]+[^가-힣]*/;
		if (!koreanNamePattern.test(name) || hasConsonantOrVowelOnly.test(name)) {
			$('#nameError').text("이름은 공백 없이 제대로된 한글만 사용해주세요.");
			$(this).focus();
		} else {
			$('#nameError').text("");
		}
	});
});
        
$(document).ready(function() {
    $('#emailInput').on('input', function() {
        const email = $(this).val();
        const emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;

        if (!emailPattern.test(email)) {
            $('#emailError').text("올바른 이메일 형식을 사용해주세요.");
            $('#certificationButton').prop('disabled', true);
        } else {
            $('#emailError').text("");
            $('#certificationButton').prop('disabled', false);
        }
	});
});
function certificationEmail() {
    alert('인증번호가 전송되었습니다.');
}
        
$(document).ready(function() {
    $('#password, #passwordConfirm').on('input', function() {
        const password = $('#password').val();
        const passwordConfirm = $('#passwordConfirm').val();
        const passwordPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
        
        // 비밀번호 패턴 검사
        if (!passwordPattern.test(password) && password) {
            $('#passwordError').text("비밀번호는 8자 이상이며, 대문자, 소문자, 숫자, 특수 문자를 포함해야 합니다.");
            $('#password').focus();
        } else {
            $('#passwordError').text("");
        }

        // 비밀번호 일치 검사
        if (password && passwordConfirm && password === passwordConfirm) {
            $('#passwordMatchMessage').text("비밀번호가 일치합니다.").css("color", "blue");
        } else if (password && passwordConfirm) {
            $('#passwordMatchMessage').text("비밀번호가 일치하지 않습니다.").css("color", "red");
        } else {
            $('#passwordMatchMessage').text("");
        }
    });
});

