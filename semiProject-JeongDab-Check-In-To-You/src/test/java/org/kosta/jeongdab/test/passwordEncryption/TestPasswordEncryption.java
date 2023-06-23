package org.kosta.jeongdab.test.passwordEncryption;

import org.mindrot.jbcrypt.BCrypt;

// 비밀번호 암호화 테스트

public class TestPasswordEncryption {

	// 비밀번호를 해시화하여 저장하는 메서드
	public static String hashPassword(String password) {
		String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
		return hashedPassword;
	}

	// 저장된 해시화된 비밀번호를 확인하는 메서드
	public static boolean checkPassword(String password, String hashedPassword) {
		boolean passwordMatch = BCrypt.checkpw(password, hashedPassword);
		return passwordMatch;
	}

	public static void main(String[] args) {
		String originalPassword = "password123";

		// 비밀번호를 해시화하여 저장
		String hashedPassword = hashPassword(originalPassword);
		System.out.println("Hashed Password: " + hashedPassword);

		// 저장된 해시화된 비밀번호 확인
		boolean isMatch = checkPassword(originalPassword, hashedPassword);
		System.out.println("Password Match: " + isMatch);
	}
}
