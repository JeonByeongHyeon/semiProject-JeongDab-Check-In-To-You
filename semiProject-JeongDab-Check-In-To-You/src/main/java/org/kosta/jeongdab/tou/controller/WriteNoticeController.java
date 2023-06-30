package org.kosta.jeongdab.tou.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kosta.jeongdab.tou.model.MemberVO;
import org.kosta.jeongdab.tou.model.NoticeBoardDAO;
import org.kosta.jeongdab.tou.model.NoticeBoardVO;

public class WriteNoticeController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (request.getMethod().equals("POST") == false)
			throw new ServletException("POST 방식만 서비스 가능합니다");
		// 회원만 사용 가능
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("member") == null) {
			System.out.println("회원만 서비스 이용 가능합니다");
			return "redirect:NoticeBoardList.do";
		}
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String date = request.getParameter("date");
		MemberVO memberNo = (MemberVO) session.getAttribute("member");
		MemberVO memberVO = new MemberVO();
		memberVO.setMemberNo(memberNo.getMemberNo());
		NoticeBoardVO nbvo = new NoticeBoardVO();
		nbvo.setNoticeBoardTitle(title);
		nbvo.setNoticeBoardContent(content);
		nbvo.setNoticeBoardDate(date);
		nbvo.setMemberVO(memberVO);
		NoticeBoardDAO.getInstance().writeNotice(nbvo);
		return "redirect:NoticeBoardList.do";
	}

}
