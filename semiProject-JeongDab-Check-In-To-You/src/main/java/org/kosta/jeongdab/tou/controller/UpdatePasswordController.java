package org.kosta.jeongdab.tou.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kosta.jeongdab.tou.model.MemberDAO;
import org.kosta.jeongdab.tou.model.MemberVO;

public class UpdatePasswordController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("member");
		long memberNo = memberVO.getMemberNo();
		String password = request.getParameter("password");
		memberVO.setMemberNo(memberNo);
		memberVO.setPassword(password);
		MemberDAO.getInstance().updatePassword(memberVO);
		return "Logout.do";
	}

}
