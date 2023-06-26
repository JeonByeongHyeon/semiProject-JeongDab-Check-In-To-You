package org.kosta.jeongdab.tou.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kosta.jeongdab.tou.model.MemberDAO;
import org.kosta.jeongdab.tou.model.MemberVO;

public class LoginController implements Controller {

   private static final String POST_METHOD = "POST";

   @Override
   public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
      System.out.println("handleRequest 메서드 시작");
      if (!POST_METHOD.equals(request.getMethod())) {
         throw new ServletException("POST 메서드를 사용해야 합니다.");
      }
      String memberEmail = request.getParameter("memberEmail");
      String password = request.getParameter("password");
      MemberVO memberVO = MemberDAO.getInstance().login(memberEmail, password);
      if (memberVO == null) {
         System.out.println("로그인 실패");
         return "redirect:member/login-fail.jsp";
      } else {
         HttpSession session = request.getSession();
         session.setAttribute("member", memberVO);
         System.out.println("로그인 성공");
         return "redirect:index.jsp";
      }
   }

}