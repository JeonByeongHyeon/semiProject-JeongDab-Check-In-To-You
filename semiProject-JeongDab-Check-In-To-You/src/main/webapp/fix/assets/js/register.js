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