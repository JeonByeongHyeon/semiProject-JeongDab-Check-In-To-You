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
