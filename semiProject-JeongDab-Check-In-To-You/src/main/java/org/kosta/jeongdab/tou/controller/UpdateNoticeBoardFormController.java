package org.kosta.jeongdab.tou.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kosta.jeongdab.tou.model.NoticeBoardDAO;

public class UpdateNoticeBoardFormController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(request.getMethod().equals("POST")==false)
			throw new ServletException("POST 방식만 서비스 됩니다");	
		HttpSession session=request.getSession(false);
		if(session==null||session.getAttribute("member")==null) {
			System.out.println("인증안돼서 서비스 안해줌!");
			return "redirect:NoticeBoardList.do";
		}	
		long no = Long.parseLong(request.getParameter("no"));
		request.setAttribute("noticeBoard", NoticeBoardDAO.getInstance().findNoticeBoardByNo(no));
		return "board/notice-board-update-form.jsp";
	}

}
