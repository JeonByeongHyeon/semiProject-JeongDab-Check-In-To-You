package org.kosta.jeongdab.tou.controller;

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
		String message = null;
		String replyNo = request.getParameter("no");
		HttpSession session=request.getSession(false);
		long no = Long.parseLong(replyNo);
		MemberVO memberVO = new MemberVO();
		memberVO = (MemberVO) session.getAttribute("member");
		long memberNo = memberVO.getMemberNo();
		ReplyVO replyVO =new ReplyVO(); 
		replyVO.setReplyNo(no);
		replyVO.setMemberVO(memberVO);
		boolean result=ReplyDAO.getInstance().deletePostByNo(replyVO);
		if (result) {
			message = "succes";
		}else {
			message = "fail";
		}
		request.setAttribute("responsebody", message);
		return "AjaxView";
		
	}
}
