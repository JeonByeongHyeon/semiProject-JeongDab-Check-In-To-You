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
	public void updateReplyBoard(ReplyVO ReplyVO) throws SQLException {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
//			con=dataSource.getConnection();
//			StringBuilder sql= new StringBuilder();
//			sql.append("UPDATE service_board SET service_board_title=?,service_board_content=?, ");
//			sql.append("service_date=?, nation=? ");
//			sql.append("WHERE service_board_no=?");
//			pstmt=con.prepareStatement(sql.toString());
//			pstmt.setString(1,serviceBoardVO.getServiceBoardTitle());
//			pstmt.setString(2, serviceBoardVO.getServiceBoardContent());
//			pstmt.setString(3, serviceBoardVO.getServiceDate());
//			pstmt.setString(4, serviceBoardVO.getNation());
//			pstmt.setLong(5, serviceBoardVO.getServiceBoardNo());
//			System.out.println(serviceBoardVO);
			pstmt.executeUpdate();
		}finally {
			closeAll(pstmt, con);
		}
	}
	
}