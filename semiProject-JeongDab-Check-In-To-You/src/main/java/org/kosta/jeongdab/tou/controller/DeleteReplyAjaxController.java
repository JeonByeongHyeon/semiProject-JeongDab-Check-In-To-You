package org.kosta.jeongdab.tou.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.kosta.jeongdab.tou.model.MemberVO;
import org.kosta.jeongdab.tou.model.ReplyDAO;
import org.kosta.jeongdab.tou.model.ReplyVO;

public class DeleteReplyAjaxController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (request.getMethod().equals("POST") == false)
			throw new ServletException("POST 방식만 서비스 됩니다");
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("member") == null) {
			System.out.println("인증안돼서 댓글수정 안해줌!");
			return "redirect:FindServiceBoardByNo.do";
		}
		String message = null;
		String replyNo = request.getParameter("no");
		long no = Long.parseLong(replyNo);
		MemberVO memberVO = new MemberVO();
		memberVO = (MemberVO) session.getAttribute("member");
		long memberNo = memberVO.getMemberNo();
		ReplyVO replyVO =new ReplyVO(); 
		replyVO.setReplyNo(no);
		replyVO.setMemberVO(memberVO);
		boolean result=ReplyDAO.getInstance().deleteReplyByNo(replyVO);
		if (result) {
			message = "succes";
		}else {
			message = "fail";
		}
		request.setAttribute("responsebody", message);
		return "AjaxView";
		
	}
}
