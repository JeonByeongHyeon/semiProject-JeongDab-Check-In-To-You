package org.kosta.jeongdab.tou.controller;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kosta.jeongdab.tou.model.MemberDAO;
import org.kosta.jeongdab.tou.model.MemberVO;

public class RegisterMemberController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String birthStr = request.getParameter("birth");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date utilDate = dateFormat.parse(birthStr);
		java.sql.Date memberBirth = new java.sql.Date(utilDate.getTime());
		String address = request.getParameter("address");
		String addressDetail = request.getParameter("addressDetail");
		System.out.println(addressDetail);
		MemberVO memberVO = new MemberVO();
		memberVO.setMemberName(name);
		memberVO.setMemberEmail(email);
		memberVO.setPassword(password);
		memberVO.setMemberBirth(memberBirth);
		memberVO.setMemberAddress(address);
		memberVO.setMemberDetailAddress(addressDetail);
		MemberDAO.getInstance().registerMember(memberVO);
		System.out.println(memberVO);
		return "redirect:index.jsp";
	}

}
