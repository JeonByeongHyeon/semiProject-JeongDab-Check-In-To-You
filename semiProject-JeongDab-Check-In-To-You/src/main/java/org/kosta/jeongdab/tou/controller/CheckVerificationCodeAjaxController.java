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

		if (verificationCode != null && verificationCode.equals(enteredCode)) {
			// 인증번호가 일치하는 경우
			response.getWriter().write("success");
			System.out.println(verificationCode);
			System.out.println(enteredCode + "입력");
		} else {
			// 인증번호가 일치하지 않는 경우
			System.out.println(verificationCode);
			System.out.println(enteredCode + "입력");
			response.getWriter().write("failure");
		}

		// 응답 설정
		request.setAttribute("responsebody", response.getWriter().toString());
		return "AjaxView";
	}

}
