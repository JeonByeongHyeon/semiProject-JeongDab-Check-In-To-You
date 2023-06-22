package org.kosta.jeongdab.tou.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

public class ServiceBoardDAO {
	private static ServiceBoardDAO instance=new ServiceBoardDAO();
	private DataSource dataSource;
	private ServiceBoardDAO() {
		dataSource=DataSourceManager.getInstance().getDataSource();
	}
	public static ServiceBoardDAO getInstance() {
		return instance;
	}	
	public void closeAll(PreparedStatement pstmt,Connection con) throws SQLException {
		if(pstmt!=null)
			pstmt.close();
		if(con!=null)
			con.close(); // connection을 dbcp에 반환한다 
	}
	public void closeAll(ResultSet rs,PreparedStatement pstmt,Connection con) throws SQLException {
		if(rs!=null)
			rs.close();
		closeAll(pstmt, con);
	}
}
