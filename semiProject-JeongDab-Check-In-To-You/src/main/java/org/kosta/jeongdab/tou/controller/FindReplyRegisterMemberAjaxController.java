package org.kosta.jeongdab.tou.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kosta.jeongdab.tou.model.MemberVO;

public class FindReplyRegisterMemberAjaxController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("member");
		long memberNo = memberVO.getMemberNo();
		System.out.println(memberNo + "멤버넘버");
		request.setAttribute("responsebody", memberNo);
		return "AjaxView";
	}

}
