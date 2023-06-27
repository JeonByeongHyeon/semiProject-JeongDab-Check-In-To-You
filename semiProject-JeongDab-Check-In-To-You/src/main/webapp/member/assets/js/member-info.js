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
            enableSignupButton(); // 주소가 변경되었으므로 버튼 상태를 업데이트합니다.
        }
    }).open();
}

// 초기 로딩 시의 기존 정보 저장
let originalName = $("#nameInput").val();
let originalBirth = $("input[name='birth']").val();
let originalAddress = $("#address").val();
let originalDetailAddress = $("#addressDetail").val();

function validateName() {
    let name = $("#nameInput").val();
    let regex = /^[가-힣]+$/;

    if (!regex.test(name)) {
        $("#nameError").html("이름은 한글로만 입력해야 하며, 띄어쓰기를 포함할 수 없습니다.");
    } else {
        $("#nameError").html("");
    }

    enableSignupButton();
}

function enableSignupButton() {
    let name = $("#nameInput").val();
    let nameValid = /^[가-힣]+$/.test(name);
    let birth = $("input[name='birth']").val();
    let address = $("#address").val();
    let addressDetail = $("#addressDetail").val();

    if (nameValid && birth && address && (name !== originalName || birth !== originalBirth || address !== originalAddress || addressDetail !== originalDetailAddress)) {
        $("#signupButton").prop("disabled", false);
    } else {
        $("#signupButton").prop("disabled", true);
    }
}

// 이름 입력 시 호출
$("#nameInput").on("input", function() {
    enableSignupButton();
});

// 생년월일 입력 시 호출
$("input[name='birth']").on("input", function() {
    enableSignupButton();
});

// 주소 입력 시 호출
$("#address").on("input", function() {
    enableSignupButton();
});

// 주소 상세 입력 시 호출
$("#addressDetail").on("input", function() {
    enableSignupButton();
});
