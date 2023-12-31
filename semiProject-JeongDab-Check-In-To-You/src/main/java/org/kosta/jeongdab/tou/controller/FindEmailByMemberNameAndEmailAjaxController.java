package org.kosta.jeongdab.tou.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kosta.jeongdab.tou.model.MemberDAO;
import org.kosta.jeongdab.tou.model.MemberVO;

public class FindEmailByMemberNameAndEmailAjaxController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String name = request.getParameter("name");
		String inputEmail = request.getParameter("email");
		MemberVO memberVO = new MemberVO();
		memberVO.setMemberName(name);
		memberVO.setMemberEmail(inputEmail);
		String memberEmail = MemberDAO.getInstance().findEmailByMemberNameAndEmail(memberVO);
		String result = null;
		if (inputEmail.equals(memberEmail)) {
			result = "matched";
		} else {
			result = "not_matched";
		}
		request.setAttribute("responsebody", result);
		return "AjaxView";
	}
}
