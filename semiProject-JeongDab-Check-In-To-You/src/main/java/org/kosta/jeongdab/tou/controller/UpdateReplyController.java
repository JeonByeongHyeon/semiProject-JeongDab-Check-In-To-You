package org.kosta.jeongdab.tou.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kosta.jeongdab.tou.model.ReplyDAO;
import org.kosta.jeongdab.tou.model.ReplyVO;

public class UpdateReplyController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (request.getMethod().equals("POST") == false)
			throw new ServletException("POST 방식만 서비스 됩니다");
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("member") == null) {
			System.out.println("인증안돼서 댓글수정 안해줌!");
			return "redirect:FindServiceBoardByNo.do";
		}
		System.out.println("========컨트롤러===");
		String result=null;
		String replyContent=request.getParameter("content");
		long replyNo= Long.parseLong(request.getParameter("commentId"));
		ReplyVO replyVO= new ReplyVO();
		replyVO.setReplyContent(replyContent);
		replyVO.setReplyNo(replyNo);
		System.out.println(replyVO);
		boolean flag=ReplyDAO.getInstance().updateReplyBoard(replyVO);
		if(flag) {
			result = "success";
		}else {
			result = "fail";
		}
		request.setAttribute("responsebody", result);
		return "AjaxView";
	}

}
