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

//	로그인
	public long login(String memberEmail, String password) throws SQLException {
		System.out.println("==============로그인==============");
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		long memberNo = 0;
		try {
			con = dataSource.getConnection();
			String sql = "SELECT member_no FROM member WHERE member_email=? AND password=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberEmail);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				memberNo = rs.getLong(1);
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		System.out.println(memberNo);
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
}
