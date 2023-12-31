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
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVO memberVO = null;
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(LOGIN_QUERY);
			pstmt.setString(1, memberEmail);
			String hashedPassword = hashPassword(password);
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

//	회원가입
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
			pstmt.setString(3, hashedPassword);
			pstmt.setDate(4, memberVO.getMemberBirth());
			pstmt.setString(5, memberVO.getMemberAddress());
			pstmt.setString(6, memberVO.getMemberDetailAddress());
			pstmt.executeUpdate();

		} finally {
			closeAll(pstmt, con);
		}
	}

	// 비밀번호 암호화를 수행하는 메서드
	public String hashPassword(String password) {
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

// 	회원 정보 수정
	public void updateMember(MemberVO memberVO) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = dataSource.getConnection();
			String sql = "UPDATE member SET member_name = ?, member_birth = ?, member_address = ?, member_detail_address = ? WHERE member_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberVO.getMemberName());
			pstmt.setDate(2, memberVO.getMemberBirth());
			pstmt.setString(3, memberVO.getMemberAddress());
			pstmt.setString(4, memberVO.getMemberDetailAddress());
			pstmt.setLong(5, memberVO.getMemberNo());
			pstmt.executeUpdate();
		} finally {
			closeAll(pstmt, con);
		}

	}

//	회원 정보 수정에 필요한 정보들 조회
	public MemberVO findMemberInfo(long memberNo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVO memberVO = null;
		try {
			con = dataSource.getConnection();
			String sql = "SELECT member_name, member_email, member_birth, member_address, member_detail_address FROM member WHERE member_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, memberNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				memberVO = new MemberVO();
				memberVO.setMemberName(rs.getString("member_name"));
				memberVO.setMemberEmail(rs.getString("member_email"));
				memberVO.setMemberBirth(rs.getDate("member_birth"));
				memberVO.setMemberAddress(rs.getString("member_address"));
				memberVO.setMemberDetailAddress(rs.getString("member_detail_address"));
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		return memberVO;
	}

//	로그인한 회원과 같은 이메일 조회
	public String findEmailByMemberNo(long memberNo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String memberEmail = null;
		try {
			con = dataSource.getConnection();
			// 이메일과 회원 ID를 이용하여 데이터베이스에서 일치하는지 확인하는 로직 작성
			String sql = "SELECT member_email FROM member WHERE member_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, memberNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				memberEmail = rs.getString(1);
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		// 반환값은 일치 여부에 따라 true 또는 false
		return memberEmail;
	}

//	비밀번호 변경
	public void updatePassword(MemberVO memberVO) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = dataSource.getConnection();
			String sql = "UPDATE member SET password = ? WHERE member_no = ?";
			pstmt = con.prepareStatement(sql);
			String hashedPassword = hashPassword(memberVO.getPassword());
			pstmt.setString(1, hashedPassword);
			pstmt.setLong(2, memberVO.getMemberNo());
			pstmt.executeUpdate();
		} finally {
			closeAll(pstmt, con);
		}
	}

//	회원 명과 이메일로 동일한 이메일 있는지 검사
	public String findEmailByMemberNameAndEmail(MemberVO memberVO) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String findEmail = null;
		try {
			con = dataSource.getConnection();
			// 이메일과 회원 ID를 이용하여 데이터베이스에서 일치하는지 확인하는 로직 작성
			String sql = "SELECT member_email FROM member WHERE member_name = ? and member_email = ?  ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberVO.getMemberName());
			pstmt.setString(2, memberVO.getMemberEmail());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				findEmail = rs.getString(1);
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		// 반환값은 일치 여부에 따라 true 또는 false
		return findEmail;
	}

//	비밀번호 찾기
	public void findPassword(MemberVO memberVO) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = dataSource.getConnection();
			String sql = "UPDATE member SET password = ? WHERE member_email = ?";
			pstmt = con.prepareStatement(sql);
			String hashedPassword = hashPassword(memberVO.getPassword());
			pstmt.setString(1, hashedPassword);
			pstmt.setString(2, memberVO.getMemberEmail());
			pstmt.executeUpdate();
		} finally {
			closeAll(pstmt, con);
		}
	}

//	회원탈퇴
	public void withdrawalMember(long memberNo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = dataSource.getConnection();
			String sql = "DELETE FROM member WHERE member_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, memberNo);
			pstmt.executeUpdate();
		} finally {
			closeAll(pstmt, con);
		}

	}

//	로그인한 회원의 비밀번호인지 검사
	public String checkPassword(long memberNo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String password = null;
		try {
			con = dataSource.getConnection();
			String sql = "SELECT password FROM member WHERE member_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, memberNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				password = rs.getString("password");
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		return password;
	}
}
