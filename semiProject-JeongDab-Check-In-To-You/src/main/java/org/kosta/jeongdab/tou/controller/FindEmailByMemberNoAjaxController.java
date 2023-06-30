package org.kosta.jeongdab.tou.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kosta.jeongdab.tou.model.MemberDAO;
import org.kosta.jeongdab.tou.model.MemberVO;

public class FindEmailByMemberNoAjaxController implements Controller {
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("member");
		long memberNo = memberVO.getMemberNo();
		String inputEmail = request.getParameter("email");
		String emailMatched = null;
		String memberEmail = MemberDAO.getInstance().findEmailByMemberNo(memberNo);
		if (inputEmail.equals(memberEmail)) {
			emailMatched = "matched";
		} else {
			emailMatched = "not_matched";
		}
		request.setAttribute("responsebody", emailMatched);
		return "AjaxView";
	}

}
