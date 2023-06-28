package org.kosta.jeongdab.tou.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kosta.jeongdab.tou.model.NoticeBoardDAO;
import org.kosta.jeongdab.tou.model.NoticeBoardVO;

public class UpdateNoticeController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (request.getMethod().equals("POST") == false)
			throw new ServletException("POST 방식만 서비스 됩니다");
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("member") == null) {
			System.out.println("인증안돼서 서비스 안해줌!");
			return "redirect:NoticeBoardList.do";
		}
		long no = Long.parseLong(request.getParameter("no"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String noticeBoardDate = request.getParameter("noticeDate");
		NoticeBoardVO noticeBoardVO = new NoticeBoardVO();
		noticeBoardVO.setNoticeBoardNo(no);
		noticeBoardVO.setNoticeBoardTitle(title);
		noticeBoardVO.setNoticeBoardContent(content);
		noticeBoardVO.setNoticeBoardDate(noticeBoardDate);
		NoticeBoardDAO.getInstance().updateNotice(noticeBoardVO);
		System.out.println(noticeBoardVO);
		return "redirect:FindNoticeBoardByNo.do?no=" + no;
	}

}
