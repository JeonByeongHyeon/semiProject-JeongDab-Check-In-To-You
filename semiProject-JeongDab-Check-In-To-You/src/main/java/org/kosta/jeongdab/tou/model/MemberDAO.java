package org.kosta.jeongdab.tou.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import javax.sql.DataSource;

public class MemberDAO {
	private static MemberDAO instance = new MemberDAO();
	private DataSource dataSource;

	private MemberDAO() {
		this.dataSource = DataSourceManager.getInstance().getDataSource();
	}

	public static MemberDAO getInstance() {
		return instance;
	}

	public void closeAll(PreparedStatement pstmt, Connection con) throws SQLException {
		if (pstmt != null)
			pstmt.close();
		if (con != null)
			con.close();
	}

	public void closeAll(ResultSet rs, PreparedStatement pstmt, Connection con) throws SQLException {
		if (rs != null)
			rs.close();
		closeAll(pstmt, con);
	}

	private static final String LOGIN_QUERY = "SELECT member_no ,member_status FROM member WHERE member_email=? AND password=?";
	

	// 로그인
	public MemberVO login(String memberEmail, String password) throws SQLException {
		System.out.println("login 메서드 시작"); // 로깅 프레임워크 사용
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVO memberVO = null;
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(LOGIN_QUERY);
			pstmt.setString(1, memberEmail);
			String hashedPassword = hashPassword(password);
			System.out.println("login" + hashedPassword);
			pstmt.setString(2, hashedPassword); // 실제로는 비밀번호를 해싱하여 비교해야 함
			rs = pstmt.executeQuery();
			if (rs.next()) {
				memberVO = new MemberVO();
				memberVO.setMemberNo(rs.getLong(1));
				memberVO.setMemberStatus(rs.getInt(2));
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		return memberVO;
	}

	public void registerMember(MemberVO memberVO) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = dataSource.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append(
					"insert into member(member_no,member_name, member_email, password, member_birth, member_address, member_detail_address) ");
			sql.append("values(member_seq.nextval,?,?,?,?,?,?) ");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, memberVO.getMemberName());
			pstmt.setString(2, memberVO.getMemberEmail());
			String hashedPassword = hashPassword(memberVO.getPassword());
			System.out.println("register" + hashedPassword);
			pstmt.setString(3, hashedPassword);
			pstmt.setString(4, memberVO.getMemberBirth());
			pstmt.setString(5, memberVO.getMemberAddress());
			pstmt.setString(6, memberVO.getMemberDetailAddress());
			pstmt.executeUpdate();

		} finally {
			closeAll(pstmt, con);
		}
	}

	// 비밀번호 암호화를 수행하는 메서드
	public static String hashPassword(String password) {
		try {
			// MessageDigest 인스턴스를 생성하여 SHA-256 알고리즘을 사용하도록 설정
			// SHA-256은 해시 알고리즘 중 하나로, 입력값을 고유한 256비트 길이의 해시 값으로 변환합니다.
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			// 입력된 비밀번호를 바이트 배열로 변환한 후, 해당 바이트 배열을 SHA-256 해시 알고리즘으로 처리
			// 결과로 해시된 바이트 배열을 반환
			byte[] bytes = md.digest(password.getBytes());
			// StringBuilder 인스턴스를 생성하여, 바이트 배열을 16진수 문자열로 변환할 준비
			StringBuilder builder = new StringBuilder();
			// 해시된 바이트 배열을 순회하면서 각 바이트를 16진수 형태로 변환하여 builder에 추가
			// 예: 바이트 값이 15인 경우 '0f'로 변환
			for (byte b : bytes) {
				builder.append(String.format("%02x", b));
			}
			// 최종적으로 변환된 16진수 문자열을 반환
			return builder.toString();
		} catch (NoSuchAlgorithmException e) { // SHA-256 알고리즘이 사용할 수 없는 경우 예외 처리
			// RuntimeException을 발생시키며, 상세한 오류 메시지와 함께 원본 예외를 포함
			throw new RuntimeException("SHA-256 algorithm not found.", e);
		}
	}

	// 이메일 중복 검사
	public int checkEmail(String memberEmail) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			con = dataSource.getConnection();
			String sql = "SELECT COUNT(*) FROM member WHERE member_email = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberEmail);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		return result;
	}

//	랜덤한 인증번호 생성 메소드
	public String generateVerificationCode() {
		// 6자리의 랜덤 숫자 생성
		Random random = new Random();
		int code = 100000 + random.nextInt(900000);
		return String.valueOf(code);
	}
}
