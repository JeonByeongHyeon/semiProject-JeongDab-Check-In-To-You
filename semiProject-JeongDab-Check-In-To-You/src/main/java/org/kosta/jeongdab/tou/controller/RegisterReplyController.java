package org.kosta.jeongdab.tou.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kosta.jeongdab.tou.model.MemberVO;
import org.kosta.jeongdab.tou.model.ReplyDAO;
import org.kosta.jeongdab.tou.model.ReplyVO;
import org.kosta.jeongdab.tou.model.ServiceBoardVO;

public class RegisterReplyController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("member");
		long memberNo = memberVO.getMemberNo();
		long boardNo = Long.parseLong(request.getParameter("boardNo")) ;
		String replyContent = request.getParameter("replyContent");
		ReplyVO replyVO = new ReplyVO();
		MemberVO mvo = new MemberVO();
		mvo.setMemberNo(memberNo);
		ServiceBoardVO sbvo = new ServiceBoardVO();
		sbvo.setServiceBoardNo(boardNo);
		replyVO.setReplyContent(replyContent);
		replyVO.setMemberVO(mvo);
		replyVO.setServiceBoadrdVO(sbvo);
		System.out.println(replyVO);
		ReplyDAO.getInstance().insertReply(replyVO);
		request.setAttribute("responsebody", replyVO);
		return "AjaxView";
	}

}
