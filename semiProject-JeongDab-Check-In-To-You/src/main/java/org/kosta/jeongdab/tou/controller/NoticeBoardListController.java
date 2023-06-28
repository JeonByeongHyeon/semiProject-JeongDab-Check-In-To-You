package org.kosta.jeongdab.tou.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kosta.jeongdab.tou.model.NoticeBoardDAO;
import org.kosta.jeongdab.tou.model.Pagination;
import org.kosta.jeongdab.tou.model.ServiceBoardDAO;

public class NoticeBoardListController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pageNo = request.getParameter("pageNo");
		Pagination pagination = null;
		long totalNoticePostCount = NoticeBoardDAO.getInstance().findTotalNoticeCount();
		if (pageNo == null) {
			pagination = new Pagination(totalNoticePostCount);
		} else {
			pagination = new Pagination(totalNoticePostCount, Long.parseLong(pageNo));
		}
		request.setAttribute("pagination", pagination);
		request.setAttribute("list", NoticeBoardDAO.getInstance().noticeBoardList(pagination));
//		request.setAttribute("url", "board/serviceboard-list.jsp");
		return "board/notice-board-list.jsp";
	}
}
