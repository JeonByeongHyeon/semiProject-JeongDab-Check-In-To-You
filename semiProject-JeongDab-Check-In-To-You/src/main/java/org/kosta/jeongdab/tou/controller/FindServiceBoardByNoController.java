package org.kosta.jeongdab.tou.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kosta.jeongdab.tou.model.ServiceBoardDAO;

public class FindServiceBoardByNoController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("member") == null) {
			System.out.println("인증안돼서 서비스 안해줌!");
			request.setAttribute("url", "index.jsp");
		}
		long no = Long.parseLong(request.getParameter("no"));
		System.out.println("=======================" + no);

		request.setAttribute("serviceBoard", ServiceBoardDAO.getInstance().findServiceBoardByNo(no));
//		request.setAttribute("url", "board/serviceboard-list.jsp");
		return "board/service-board-detail.jsp";
	}
}
