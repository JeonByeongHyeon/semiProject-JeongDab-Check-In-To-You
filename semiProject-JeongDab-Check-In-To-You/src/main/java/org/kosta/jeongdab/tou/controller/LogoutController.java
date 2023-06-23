package org.kosta.jeongdab.tou.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutController implements Controller {
	private static final String POST_METHOD = "POST";
	private static final String REDIRECT_URL = "redirect:layout2.jsp";

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// POST 메서드 확인
		if (!POST_METHOD.equals(request.getMethod())) {
			System.out.println("POST 메서드가 아님");
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
		// 기존 세션 무효화 (로그아웃)
		HttpSession session = request.getSession(false);
		if (session != null) {
			System.out.println("로그아웃 회원번호 : " + session.getAttribute("member"));
			System.out.println("세션 무효화");
			session.invalidate();
		}
		// 인덱스 페이지로 리다이렉트
		return REDIRECT_URL;
	}

}
