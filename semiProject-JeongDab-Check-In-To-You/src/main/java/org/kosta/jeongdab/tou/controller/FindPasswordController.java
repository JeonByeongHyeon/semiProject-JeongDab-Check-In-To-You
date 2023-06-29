package org.kosta.jeongdab.tou.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kosta.jeongdab.tou.model.MemberDAO;
import org.kosta.jeongdab.tou.model.MemberVO;

public class FindPasswordController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MemberVO memberVO = new MemberVO();
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		memberVO.setPassword(password);
		memberVO.setMemberEmail(email);
		MemberDAO.getInstance().findPassword(memberVO);
		return "index.jsp";
	}

}
