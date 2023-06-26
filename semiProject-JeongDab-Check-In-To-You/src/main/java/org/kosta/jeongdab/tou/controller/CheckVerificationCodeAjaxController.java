package org.kosta.jeongdab.tou.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CheckVerificationCodeAjaxController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String enteredCode = request.getParameter("enteredCode");
		// 세션에서 저장된 인증번호 가져오기
		HttpSession session = request.getSession();
		String verificationCode = (String) session.getAttribute("verificationCode");
		String result = null;

		if (enteredCode != null && enteredCode.equals(verificationCode)) {
			// 인증번호가 일치하는 경우
			System.out.println(verificationCode);
			System.out.println(enteredCode + "입력 성공");
			result = "success";
		} else {
			// 인증번호가 일치하지 않는 경우
			System.out.println(verificationCode);
			System.out.println(enteredCode + "입력 실패");
		}

		// 응답 설정
		request.setAttribute("responsebody", result);
		return "AjaxView";
	}

}
