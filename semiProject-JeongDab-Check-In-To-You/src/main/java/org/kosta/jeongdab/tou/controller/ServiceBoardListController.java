package org.kosta.jeongdab.tou.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kosta.jeongdab.tou.model.Pagination;
import org.kosta.jeongdab.tou.model.ServiceBoardDAO;

public class ServiceBoardListController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pageNo = request.getParameter("pageNo");
		Pagination pagination = null;
		long totalPostCount = ServiceBoardDAO.getInstance().findTotalPostCount();
		if (pageNo == null) {
			pagination = new Pagination(totalPostCount);
		} else {
			pagination = new Pagination(totalPostCount, Long.parseLong(pageNo));
		}
		request.setAttribute("pagination", pagination);
		request.setAttribute("list", ServiceBoardDAO.getInstance().serviceBoardList(pagination));
//		request.setAttribute("url", "board/serviceboard-list.jsp");
		return "board/serviceboard-list.jsp";
	}
}
