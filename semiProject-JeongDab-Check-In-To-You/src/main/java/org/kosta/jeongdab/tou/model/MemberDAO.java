package org.kosta.jeongdab.tou.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.mindrot.jbcrypt.BCrypt;

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

	private static final String LOGIN_QUERY = "SELECT member_no FROM member WHERE member_email=? AND password=?";

	// 로그인
	public long login(String memberEmail, String password) throws SQLException {
		System.out.println("login 메서드 시작"); // 로깅 프레임워크 사용
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		long memberNo = 0;
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(LOGIN_QUERY);
			pstmt.setString(1, memberEmail);
			pstmt.setString(2, password); // 실제로는 비밀번호를 해싱하여 비교해야 함
			rs = pstmt.executeQuery();
			if (rs.next()) {
				memberNo = rs.getLong(1);
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		System.out.println("회원 번호: " + memberNo); // 로깅 프레임워크 사용
		return memberNo;
	}

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

	public void registerMember(MemberVO memberVO) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con= dataSource.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("insert into member(member_no,member_name, member_email, password, member_birth, member_address, member_detail_address) ");
			sql.append("values(member_seq.nextval,?,?,?,?,?,?) ");
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, memberVO.getMemberName());
			pstmt.setString(2, memberVO.getMemberEmail());
			pstmt.setString(3, memberVO.getPassword());
			pstmt.setString(4, memberVO.getMemberBirth());
			pstmt.setString(5, memberVO.getMemberAddress());
			pstmt.setString(6, memberVO.getMemberDetailAddress());
			pstmt.executeUpdate();
			
		} finally {
			closeAll(pstmt, con);
		}
		
		
	}
}
