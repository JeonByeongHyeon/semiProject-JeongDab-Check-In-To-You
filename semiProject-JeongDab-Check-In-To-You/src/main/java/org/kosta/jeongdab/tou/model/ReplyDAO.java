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

	public void insertReply(ReplyVO replyVO) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = dataSource.getConnection();
			String sql ="insert into reply (reply_no, reply_content, reply_date,member_no,service_board_no) values(reply_seq.nextval,?,sysdate,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, replyVO.getReplyContent());
			pstmt.setLong(2, replyVO.getMemberVO().getMemberNo());
			pstmt.setLong(3, replyVO.getServiceBoadrdVO().getServiceBoardNo());
			pstmt.executeUpdate();
		} finally {
			closeAll(pstmt, con);
		}
		
		
	}
	
	public ArrayList<ReplyVO> selectAjaxReplyList() throws SQLException {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		ArrayList<ReplyVO> ajaxReplyList=new ArrayList<ReplyVO>();
		
		try {
			con=dataSource.getConnection();
			// 최신글 상단정렬
			String sql="SELECT * FROM reply ORDER BY reply_no DESC";
			pstmt=con.prepareStatement(sql);
			
			rs= pstmt.executeQuery();
			
			while(rs.next()) {
				long replyNo = rs.getLong("reply_no");
				String replyContent = rs.getString("reply_content");
				String replyDate = rs.getString("reply_date");
				long memberNo = rs.getLong("member_no");
				long serviceBoardNo = rs.getLong("service_board_no");
				MemberVO memberVO = new MemberVO();
				memberVO.setMemberNo(memberNo);
				ServiceBoardVO serviceBoardVO = new ServiceBoardVO();
				serviceBoardVO.setServiceBoardNo(serviceBoardNo);
				
				ReplyVO ajaxReply=new ReplyVO();
				ajaxReply.setReplyNo(replyNo);
				ajaxReply.setReplyContent(replyContent);
				ajaxReply.setReplyDate(replyDate);
				ajaxReply.setMemberVO(memberVO);
				ajaxReply.setServiceBoadrdVO(serviceBoardVO);
				ajaxReplyList.add(ajaxReply);
			}
			
		
		} finally {
			closeAll(rs, pstmt, con);
		} 
		return ajaxReplyList;
	}

}