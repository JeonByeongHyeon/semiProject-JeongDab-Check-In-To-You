package org.kosta.jeongdab.tou.controller;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kosta.jeongdab.tou.model.MemberDAO;
import org.kosta.jeongdab.tou.model.MemberVO;

public class UpdateMemberController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String memberName = request.getParameter("name");
		String birthStr = request.getParameter("birth");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date utilDate = dateFormat.parse(birthStr);
		java.sql.Date memberBirth = new java.sql.Date(utilDate.getTime());
		String memberAddress = request.getParameter("address");
		String memberAddressDetail = request.getParameter("addressDetail");
		MemberVO memberVO = (MemberVO) session.getAttribute("member");
		long memberNo = memberVO.getMemberNo();
		memberVO.setMemberName(memberName);
		memberVO.setMemberBirth(memberBirth);
		memberVO.setMemberAddress(memberAddress);
		memberVO.setMemberDetailAddress(memberAddressDetail);
		memberVO.setMemberNo(memberNo);
		MemberDAO.getInstance().updateMember(memberVO);
		return "/UpdateMemberForm.do";
	}

}
