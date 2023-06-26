package org.kosta.jeongdab.tou.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kosta.jeongdab.tou.model.MemberVO;
import org.kosta.jeongdab.tou.model.ServiceBoardDAO;
import org.kosta.jeongdab.tou.model.ServiceBoardVO;

public class WritePostController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(request.getMethod().equals("POST")==false)
			throw new ServletException("POST 방식만 서비스 가능합니다");
		//회원만 사용 가능
		HttpSession session=request.getSession(false);
		if(session==null||session.getAttribute("memberId")==null) {
			System.out.println("회원만 서비스 이용 가능합니다");
			return "redirect:FindPostList.do";
		}
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		MemberVO memberVO=(MemberVO) session.getAttribute("memberVO");
		ServiceBoardVO serviceBoardVO=new ServiceBoardVO();
		serviceBoardVO.setServiceBoardTitle(title);
		serviceBoardVO.setServiceBoardContent(content);
		serviceBoardVO.setMemberVO(memberVO);
		ServiceBoardDAO.getInstance().posting(serviceBoardVO);
		
		return null;
	}

}
