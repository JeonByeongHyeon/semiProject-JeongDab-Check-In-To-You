package org.kosta.jeongdab.tou.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class ReplyDAO {

	private static ReplyDAO instance = new ReplyDAO();
	private DataSource dataSource;

	private ReplyDAO() {
		this.dataSource = DataSourceManager.getInstance().getDataSource();
	}

	public static ReplyDAO getInstance() {
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

	// 댓글 수정
	public boolean updateReplyBoard(ReplyVO replyVO) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = dataSource.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE reply SET reply_content=?");
			sql.append("WHERE reply_no=?");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, replyVO.getReplyContent());
			pstmt.setLong(2, replyVO.getReplyNo());
			// System.out.println(replyVO);
			pstmt.executeUpdate();
		} finally {
			closeAll(pstmt, con);
		}
		return true;
	}

//	댓글 등록
	public void insertReply(ReplyVO replyVO) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = dataSource.getConnection();
			String sql = "insert into reply (reply_no, reply_content,reply_date,member_no,service_board_no)values(reply_seq.nextval,?,sysdate,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, replyVO.getReplyContent());
			pstmt.setLong(2, replyVO.getMemberVO().getMemberNo());
			pstmt.setLong(3, replyVO.getServiceBoadrdVO().getServiceBoardNo());
			pstmt.executeUpdate();
		} finally {
			closeAll(pstmt, con);
		}

	}

//	댓글 삭제
	public boolean deleteReplyByNo(long replyNo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = dataSource.getConnection();
			String sql = "delete from reply where reply_no= ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, replyNo);
			pstmt.executeUpdate();
		} finally {
			closeAll(pstmt, con);
		}
		return true;
	}

//	댓글 리스트
	public ArrayList<ReplyVO> replyList(long boardNo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ReplyVO> arrayList = new ArrayList<>();
		try {
			con = dataSource.getConnection();
			String sql = "select r.reply_no, r.reply_content, r.reply_date, m.member_name, r.member_no from reply r, member m, service_board b where r.member_no = m.member_no and r.service_board_no = b.service_board_no and r.service_board_no = ? ORDER BY reply_no DESC";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, boardNo);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ReplyVO replyVO = new ReplyVO();
				MemberVO memberVO = new MemberVO();
				replyVO.setReplyNo(rs.getLong("reply_no"));
				replyVO.setReplyContent(rs.getString("reply_content"));
				replyVO.setReplyDate(rs.getString("reply_date"));
				memberVO.setMemberName(rs.getString("member_name"));
				memberVO.setMemberNo(rs.getLong("member_no"));
				replyVO.setMemberVO(memberVO);
				arrayList.add(replyVO);
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		return arrayList;
	}
}