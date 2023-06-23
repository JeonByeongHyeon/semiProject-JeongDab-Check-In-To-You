package org.kosta.jeongdab.tou.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kosta.jeongdab.tou.model.MemberDAO;
import org.kosta.jeongdab.tou.model.MemberVO;

public class MemberLoginController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("==============들어옴1=============");
		if (request.getMethod().equals("POST") == false) {
			throw new ServletException("POST METHOD 방식만 로그인 가능합니다");
		}
		System.out.println("==============들어옴2=============");
		String memberEmail = request.getParameter("memberEmail");
		String password = request.getParameter("password");
		MemberVO memberVO = MemberDAO.getInstance().login(memberEmail, password);
		if (memberVO == null) {
			System.out.println("로그인 실패");
			return "redirect:member/login-fail.jsp";
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("member", memberVO);
			System.out.println("로그인 성공");
			return "redirect:index.jsp";
		}
	}
}