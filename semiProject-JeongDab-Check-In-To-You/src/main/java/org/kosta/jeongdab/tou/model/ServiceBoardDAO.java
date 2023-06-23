package org.kosta.jeongdab.tou.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

public class ServiceBoardDAO {
	private static ServiceBoardDAO instance = new ServiceBoardDAO();
	private DataSource dataSource;

	private ServiceBoardDAO() {
		dataSource = DataSourceManager.getInstance().getDataSource();
	}

	public static ServiceBoardDAO getInstance() {
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
	public ServiceBoardVO findServiceBoardByNo(long no) throws SQLException{
		ServiceBoardVO svo=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=dataSource.getConnection();
			StringBuilder sql=new StringBuilder();
			sql.append("SELECT s.service_board_no,s.service_board_title,s.service_board_content, ");
			sql.append("to_char(service_board_create_date,'YYYY.MM.DD') as service_board_create_date, ");
			sql.append("s.service_board_hits,to_char(s.service_date,'YYYY.MM.DD') as service_date,s.nation,m.member_name,m.member_email ");
			sql.append("FROM service_board s ");
			sql.append("INNER JOIN member m ON s.member_no=m.member_no ");
			sql.append("WHERE s.service_board_no=?");
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setLong(1, no);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				svo=new ServiceBoardVO();
				svo.setServiceBoardTitle(rs.getString("service_board_title"));
				svo.setServiceBoardContent(rs.getString("service_board_content"));
				svo.setServiceBoardCreateDate(rs.getString("service_board_create_date"));
				svo.setServiceDate(rs.getString("service_date"));
				svo.setServiceBoardHits(rs.getLong("service_board_hits"));
				svo.setNation(rs.getString("nation"));
				MemberVO mvo=new MemberVO();
				mvo.setMemberName(rs.getString("member_name"));
				mvo.setMemberEmail(rs.getString("member_email"));
				svo.setMemberVO(mvo);
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		return svo;
	}
}
