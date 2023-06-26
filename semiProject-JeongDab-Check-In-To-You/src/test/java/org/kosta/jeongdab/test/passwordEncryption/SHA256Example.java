package org.kosta.jeongdab.test.passwordEncryption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256Example {

	public static void main(String[] args) {
		String password = "1234";

		// 비밀번호 해싱
		String hashedPassword = hashPassword(password);

		System.out.println("Original password: " + password);
		System.out.println("Hashed password: " + hashedPassword);

		// 사용자가 비밀번호를 입력했다고 가정
		String userInput = "1234";
		String hashedUserInput = hashPassword(userInput);

		// 저장된 해시와 사용자 입력 해시 비교
		if (hashedPassword.equals(hashedUserInput)) {
			System.out.println("Password match!");
		} else {
			System.out.println("Password does not match!");
		}
	}

	public static String hashPassword(String password) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] bytes = md.digest(password.getBytes());
			StringBuilder builder = new StringBuilder();
			for (byte b : bytes) {
				builder.append(String.format("%02x", b));
			}
			return builder.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("SHA-256 algorithm not found.", e);
		}
	}
}
