package org.kosta.jeongdab.tou.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	public void updateReplyBoard(ReplyVO replyVO) throws SQLException {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=dataSource.getConnection();
			StringBuilder sql= new StringBuilder();
			sql.append("UPDATE reply SET reply_content=?, reply_date=sysdate ");
			sql.append("WHERE reply_no=?");
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, replyVO.getReplyContent());
			pstmt.setLong(2, replyVO.getReplyNo());
			//System.out.println(replyVO);
			pstmt.executeUpdate();
		}finally {
			closeAll(pstmt, con);
		}
	}
	public boolean deleteReplyByNo(ReplyVO replyVO) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = dataSource.getConnection();
			String sql = "delete from reply where reply_no= ? and member_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, replyVO.getReplyNo());
			pstmt.setLong(2, replyVO.getMemberVO().getMemberNo());
			pstmt.executeUpdate();
		} finally {
			closeAll(pstmt, con);
		}
		return true;
	}
	
}