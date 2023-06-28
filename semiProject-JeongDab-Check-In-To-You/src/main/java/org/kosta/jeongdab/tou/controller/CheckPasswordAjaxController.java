package org.kosta.jeongdab.tou.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kosta.jeongdab.tou.model.MemberDAO;
import org.kosta.jeongdab.tou.model.MemberVO;

public class CheckPasswordAjaxController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("member");
		long memberNo = memberVO.getMemberNo();
		String inputPassword = request.getParameter("password");
		String hashInputPassword = MemberDAO.getInstance().hashPassword(inputPassword);
		String password = MemberDAO.getInstance().checkPassword(memberNo);
		String result = null;
		if (password.equals(hashInputPassword)) {
			result = "success";
		} else {
			result = "fail";
		}
		request.setAttribute("responsebody", result);
		return "AjaxView";
	}

}
