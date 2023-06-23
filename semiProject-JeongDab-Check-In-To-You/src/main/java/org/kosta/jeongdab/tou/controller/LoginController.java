package org.kosta.jeongdab.tou.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kosta.jeongdab.tou.model.MemberDAO;

public class LoginController implements Controller {

	private static final String POST_METHOD = "POST";
	private static final long INVALID_MEMBER_NO = 0;

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("handleRequest 메서드 시작");
		if (!POST_METHOD.equals(request.getMethod())) {
			throw new ServletException("POST 메서드를 사용해야 합니다.");
		}
		String memberEmail = request.getParameter("memberEmail");
		String password = request.getParameter("password");
		long memberNo = MemberDAO.getInstance().login(memberEmail, password);
		if (memberNo == INVALID_MEMBER_NO) {
			System.out.println("로그인 실패"); // 로깅 프레임워크 사용
			return "redirect:member/login-fail.jsp";
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("member", memberNo);
			System.out.println("로그인 성공"); // 로깅 프레임워크 사용
			return "redirect:index.jsp";
		}
	}

}