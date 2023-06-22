package org.kosta.jeongdab.test3;

// 이메일 발송 테스트
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class TestEmail {
	public static void main(String[] args) {
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

		try {
			// 이메일 메시지 작성
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("cyon8254@gmail.com"));
			message.setSubject("Test Email");

			// 랜덤 인증번호 생성
			String verificationCode = generateVerificationCode();

			// HTML 형식의 본문 작성
			String htmlContent = "<html><body>" + "<h1 style=\"color: blue;\">너에개 체크인!</h1>"
					+ "<p>This is a test email with HTML formatting.</p>" + "<p>Your verification code: "
					+ verificationCode + " 오늘도 잘했다</p>"
					+ "<a href=\"https://naver.com\">Click here</a> to visit our website." + "</body></html>";

			// 이메일 본문 설정
			message.setContent(htmlContent, "text/html; charset=utf-8");

			// 이메일 전송
			Transport.send(message);

			System.out.println("Email sent successfully.");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

//	랜덤한 인증번호 생성 메소드
	private static String generateVerificationCode() {
		// 여기에서 인증번호를 생성하는 로직을 구현하세요.
		// 예를 들어, 6자리의 랜덤 숫자 생성
		Random random = new Random();
		int code = 100000 + random.nextInt(900000);
		return String.valueOf(code);
	}
}
