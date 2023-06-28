package org.kosta.jeongdab.tou.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kosta.jeongdab.tou.model.ReplyDAO;

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
