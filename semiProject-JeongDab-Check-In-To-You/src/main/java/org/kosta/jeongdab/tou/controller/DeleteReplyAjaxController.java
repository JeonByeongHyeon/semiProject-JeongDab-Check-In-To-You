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
		String message = null;
		long replyNo = Long.parseLong(request.getParameter("commentId")) ;
		boolean result=ReplyDAO.getInstance().deleteReplyByNo(replyNo);
		System.out.println(replyNo+"-------controller");
		if (result) {
			message = "success";
		}else {
			message = "fail";
		}
		request.setAttribute("responsebody", message);
		return "AjaxView";
		
	}
}
