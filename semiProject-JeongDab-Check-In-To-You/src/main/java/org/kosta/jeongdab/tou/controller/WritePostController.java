package org.kosta.jeongdab.tou.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kosta.jeongdab.tou.model.MemberVO;
import org.kosta.jeongdab.tou.model.ServiceBoardDAO;
import org.kosta.jeongdab.tou.model.ServiceBoardVO;

public class WritePostController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (request.getMethod().equals("POST") == false)
			throw new ServletException("POST 방식만 서비스 가능합니다");
		// 회원만 사용 가능
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("member") == null) {
			System.out.println("회원만 서비스 이용 가능합니다");
			return "redirect:ServiceBoardList.do";
		}
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String date = request.getParameter("date");
		String nation = request.getParameter("nation");
		MemberVO memberNo = (MemberVO) session.getAttribute("member");
		MemberVO memberVO = new MemberVO();
		memberVO.setMemberNo(memberNo.getMemberNo());
		ServiceBoardVO sbvo = new ServiceBoardVO();
		sbvo.setServiceBoardTitle(title);
		sbvo.setServiceBoardContent(content);
		sbvo.setNation(nation);
		sbvo.setServiceDate(date);
		sbvo.setMemberVO(memberVO);
		ServiceBoardDAO.getInstance().posting(sbvo);
		return "redirect:ServiceBoardList.do";
	}

}
