package org.kosta.jeongdab.tou.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kosta.jeongdab.tou.model.MemberDAO;

public class EmailCheckAjaxController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String memberEmail = request.getParameter("email");
		int count = MemberDAO.getInstance().checkEmail(memberEmail);
		String responseBody;
		if (count > 0) {
			responseBody = "duplicate";
		} else {
			responseBody = "unique";
		}
		request.setAttribute("responsebody", responseBody);
		return "AjaxView";
	}

}