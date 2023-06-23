package org.kosta.jeongdab.tou.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kosta.jeongdab.tou.model.MemberDAO;
import org.kosta.jeongdab.tou.model.MemberVO;

public class RegisterMemberController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password	= request.getParameter("password");
		String birth = request.getParameter("birth");
		String address = request.getParameter("address");
		String addressDetail = request.getParameter("addressDetail");
		MemberVO memberVO = new MemberVO(0, name,email,password,birth,0, address,addressDetail);
		MemberDAO.getInstance().registerMember(memberVO);
		return "redirect:member/register-result.jsp";
	}

}
