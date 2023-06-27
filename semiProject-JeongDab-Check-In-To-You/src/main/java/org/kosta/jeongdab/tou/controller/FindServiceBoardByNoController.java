package org.kosta.jeongdab.tou.controller;

import java.util.ArrayList;

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
		// 조회수 증가
		@SuppressWarnings("unchecked")
		ArrayList<Long> serviceBoardNoList = (ArrayList<Long>) session.getAttribute("serviceBoardNoList");
		if (serviceBoardNoList == null) {
			serviceBoardNoList = new ArrayList<Long>();
			session.setAttribute("serviceBoardNoList", serviceBoardNoList);
		}
		boolean existNo = serviceBoardNoList.contains(no);

		if (existNo == false) { // 조회하지 않았으면
			// 조회수 증가
			ServiceBoardDAO.getInstance().updateHits(no);
			serviceBoardNoList.add(no);// 조회한 글리스트에 게시글번호를 저장한다
		}
		// 게시물 조회
		request.setAttribute("serviceBoard", ServiceBoardDAO.getInstance().findServiceBoardByNo(no));
//		request.setAttribute("url", "board/serviceboard-list.jsp");
		return "board/service-board-detail.jsp";
	}
}
