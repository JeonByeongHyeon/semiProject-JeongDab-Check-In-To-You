package org.kosta.jeongdab.tou.controller;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kosta.jeongdab.tou.model.MemberDAO;

public class SendEmailAjaxController implements Controller {
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws MessagingException {
		String email = request.getParameter("email");

		// 이메일 발송에 필요한 정보 설정
		String host = "smtp.gmail.com";
		String port = "587";
		String username = "izzystradlin77777@gmail.com";
		String password = "gnfouozbklnxnqoc";

		// 이메일 속성 설정
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);

		// Session 객체 생성
		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		// 이메일 메시지 작성
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(username));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
		message.setSubject("Email Verification");

		// 랜덤 인증번호 생성
		String verificationCode = MemberDAO.getInstance().generateVerificationCode();

		// HTML 형식의 본문 작성
		String htmlContent = "<html><body>" + "<h1 style=\"color: blue;\">너에개 체크인 <br> 이메일 인증번호</h1>"
				+ "<p>아래의 인증번호를 사용하여 이메일 주소를 인증해주세요:</p>" + "<h2>" + verificationCode + "</h2>" + "</body></html>";

		// 이메일 본문 설정
		message.setContent(htmlContent, "text/html; charset=utf-8");

		// 이메일 전송
		Transport.send(message);

		// 응답 설정
		request.setAttribute("responsebody", verificationCode);

		// 세션에 인증번호 저장
		HttpSession heeHttpSession = request.getSession();
		heeHttpSession.setAttribute("verificationCode", verificationCode);

		return "AjaxView";
	}

}