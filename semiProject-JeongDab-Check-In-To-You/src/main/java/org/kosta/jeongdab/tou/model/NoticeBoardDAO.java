package org.kosta.jeongdab.tou.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class NoticeBoardDAO {
	private static NoticeBoardDAO instance=new NoticeBoardDAO();
	private DataSource dataSource;
	
	private NoticeBoardDAO() {
		dataSource = DataSourceManager.getInstance().getDataSource();
	}
	public static NoticeBoardDAO getInstance() {
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
	public NoticeBoardVO findNoticeBoardByNo(long no) throws SQLException{
		NoticeBoardVO nvo=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=dataSource.getConnection();
			StringBuilder sql=new StringBuilder();
			sql.append("SELECT notice_no,notice_board_title,notice_board_content,to_char(notice_board_date ,'YYYY.MM.DD') ");
			sql.append("AS notice_board_date,notice_board_hits FROM notice ");
			sql.append("WHERE notice_no=?");
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setLong(1, no);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				nvo = new NoticeBoardVO();
				nvo.setNoticeBoardNo(rs.getLong("notice_no"));
				nvo.setNoticeBoardTitle(rs.getString("notice_board_title"));
				nvo.setNoticeBoardContent(rs.getString("notice_board_content"));
				nvo.setNoticeBoardDate(rs.getString("notice_board_date"));
				nvo.setNoticeBoardHits(rs.getLong("notice_board_hits"));
				
			}
	} finally {
		closeAll(rs, pstmt, con);
	}
		return nvo;
	}
	public ArrayList<NoticeBoardVO> noticeBoardList(Pagination pagination) throws SQLException{
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<NoticeBoardVO> list = new ArrayList<NoticeBoardVO>();
		try {
			con=dataSource.getConnection();
			StringBuilder sql=new StringBuilder();
			sql.append("SELECT rnum,notice_no,notice_board_title, ");
			sql.append("to_char(notice_board_date ,'YYYY.MM.DD') AS notice_board_date, ");
			sql.append("notice_board_hits FROM (SELECT row_number() over(ORDER BY notice_no DESC) as rnum, ");
			sql.append("notice_no,notice_board_title,notice_board_date,notice_board_hits FROM notice) n ");
			sql.append("WHERE rnum BETWEEN ? AND ?");
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setLong(1, pagination.getStartRowNumber());
			pstmt.setLong(2, pagination.getEndRowNumber());
			rs=pstmt.executeQuery();
			while(rs.next()) {
				NoticeBoardVO nvo=new NoticeBoardVO();
				nvo.setNoticeBoardNo(rs.getLong("notice_no"));
				nvo.setNoticeBoardTitle(rs.getString("notice_board_title"));
				nvo.setNoticeBoardDate(rs.getString("notice_board_date"));
				nvo.setNoticeBoardHits(rs.getLong("notice_board_hits"));
				list.add(nvo);
			}
		}finally {
			closeAll(rs, pstmt, con);
		}
		return list;	
	}
	public long findTotalNoticeCount() throws SQLException{
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		long totalNoticeCount=0;
		try {
			con=dataSource.getConnection();
			String sql="select count(*)from notice";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next())
				totalNoticeCount=rs.getLong(1);
		}finally {
			closeAll(rs,pstmt,con);
		}
		return totalNoticeCount;
	}
	public void writeNotice(NoticeBoardVO noticeVO) throws SQLException{
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=dataSource.getConnection();
			StringBuilder sql=new StringBuilder();
			sql.append("INSERT INTO notice (notice_no, notice_board_title,notice_board_content,notice_board_date ,member_no) ");
			sql.append("VALUES(notice_board_seq.nextval,?,?,sysdate,?)");
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, noticeVO.getNoticeBoardTitle());
			pstmt.setString(2, noticeVO.getNoticeBoardContent());
			pstmt.setLong(3, noticeVO.getMemberVO().getMemberNo());
			pstmt.executeUpdate();
		}finally {
			closeAll( pstmt, con);
		}	
	}
	public void updateNotice(NoticeBoardVO noticeVO) throws SQLException {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=dataSource.getConnection();
			StringBuilder sql= new StringBuilder();
			sql.append("UPDATE notice SET notice_board_title=?, ");
			sql.append("notice_board_content=? WHERE notice_no=?");
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, noticeVO.getNoticeBoardTitle());	
			pstmt.setString(2, noticeVO.getNoticeBoardContent());	
			pstmt.setLong(3, noticeVO.getNoticeBoardNo());
			pstmt.executeUpdate();
		}finally {
			closeAll(pstmt, con);
		}	
	}
	public void deleteNoticeByNo(long no) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = dataSource.getConnection();
			String sql = "delete from notice where notice_no= ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, no);
			pstmt.executeUpdate();
		} finally {
			closeAll(pstmt, con);
		}
	}
	public void updateHits(long no) throws SQLException {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=dataSource.getConnection();
			String sql="update notice set notice_board_hits=notice_board_hits+1 where notice_no=?";
			pstmt=con.prepareCall(sql);
			pstmt.setLong(1, no);
			pstmt.executeUpdate();
		}finally {
			closeAll(pstmt,con);
		}
		}
}
