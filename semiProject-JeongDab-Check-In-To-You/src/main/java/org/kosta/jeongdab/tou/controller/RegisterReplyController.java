package org.kosta.jeongdab.tou.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kosta.jeongdab.tou.model.ReplyDAO;
import org.kosta.jeongdab.tou.model.ReplyVO;

public class RegisterReplyController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String replyContent = request.getParameter("replyContent");
		ReplyVO replyVO = new ReplyVO();
		replyVO.setReplyContent(replyContent);
		ReplyDAO.getInstance().insertReply(replyVO);
		request.setAttribute("responsebody", replyVO);
		return "AjaxView";
	}

}
