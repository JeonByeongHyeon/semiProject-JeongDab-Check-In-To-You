package org.kosta.jeongdab.tou.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
				svo.setServiceBoardNo(rs.getLong("service_board_no"));
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
	//게시물 리스트 보기
		public ArrayList<ServiceBoardVO> serviceBoardList(Pagination pagination) throws SQLException{
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			ArrayList<ServiceBoardVO> list=new ArrayList<>();
			try {
				con=dataSource.getConnection();
				StringBuilder sql=new StringBuilder();
				sql.append("SELECT rnum,service_board_no,service_board_title,TO_CHAR(service_date,'YYYY.MM.DD') as service_date,service_board_hits ");
				sql.append("FROM ( ");
				sql.append("SELECT row_number() over(ORDER BY service_board_no DESC) as rnum,service_board_no,service_board_title,service_date,service_board_hits  FROM  service_board ");
				sql.append(") sb WHERE rnum BETWEEN ? AND ?");
				pstmt=con.prepareStatement(sql.toString());
				pstmt.setLong(1, pagination.getStartRowNumber());
				pstmt.setLong(2, pagination.getEndRowNumber());
				rs=pstmt.executeQuery();
				while(rs.next()) {
				ServiceBoardVO sbvo=new ServiceBoardVO();
				sbvo.setServiceBoardNo(rs.getLong("service_board_no"));
				sbvo.setServiceBoardTitle(rs.getString("service_board_title"));
				MemberVO mvo=new MemberVO();
				sbvo.setMemberVO(mvo);
				sbvo.setServiceBoardCreateDate(rs.getString("service_date"));
				sbvo.setServiceBoardHits(rs.getLong("service_board_hits"));
				list.add(sbvo);
				}
			}finally {
				closeAll(rs, pstmt, con);
			}
			return list;
		}

		public long findTotalPostCount() throws SQLException {
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			long totalPostCount=0;
					try {
						con=dataSource.getConnection();
						String sql="select count(*)from service_board";
						pstmt=con.prepareStatement(sql);
						rs=pstmt.executeQuery();
						if(rs.next())
							totalPostCount=rs.getLong(1);
					}finally {
						closeAll(rs,pstmt,con);
					}
			return totalPostCount;
		}

		public void posting(ServiceBoardVO serviceBoardVO) throws SQLException {
			Connection con=null;
			PreparedStatement pstmt=null;
			try {
				con=dataSource.getConnection();
				StringBuilder sql=new StringBuilder();
				sql.append("INSERT INTO service_board(service_board_no,service_board_title,service_board_content, ");
				sql.append("service_board_create_date,service_date,nation,member_no) VALUES ");
				sql.append("(service_board_seq.nextval,?,?, sysdate,TO_DATE('2023-7-1','YYYY-MM-DD'),?,?) ");
				pstmt=con.prepareStatement(sql.toString());
				pstmt.setString(1, serviceBoardVO.getServiceBoardTitle());
				pstmt.setString(2, serviceBoardVO.getServiceBoardContent());
				pstmt.setString(3, serviceBoardVO.getNation());
				pstmt.setLong(4, serviceBoardVO.getMemberVO().getMemberNo());
				pstmt.executeUpdate();
			}finally {
				closeAll( pstmt, con);
			}
		}

		public void updateServiceBoard(ServiceBoardVO serviceBoardVO) throws SQLException {
			Connection con=null;
			PreparedStatement pstmt=null;
			System.out.println("==========DAO 들어옴==============");
			try {
				con=dataSource.getConnection();
				StringBuilder sql= new StringBuilder();
				sql.append("UPDATE service_board SET service_board_title=?,service_board_content=?, ");
				sql.append("service_date=?, nation=? ");
				sql.append("WHERE service_board_no=?");
				pstmt=con.prepareStatement(sql.toString());
				pstmt.setString(1,serviceBoardVO.getServiceBoardTitle());
				pstmt.setString(2, serviceBoardVO.getServiceBoardContent());
				pstmt.setString(3, serviceBoardVO.getServiceDate());
				pstmt.setString(4, serviceBoardVO.getNation());
				pstmt.setLong(5, serviceBoardVO.getServiceBoardNo());
				System.out.println(serviceBoardVO);
				pstmt.executeUpdate();
			}finally {
				closeAll(pstmt, con);
			}
		}

		public void deletePostByNo(long no) throws SQLException {
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				con = dataSource.getConnection();
				String sql = "delete from service_board where service_board_no= ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setLong(1, no);
				pstmt.executeUpdate();
			} finally {
				closeAll(pstmt, con);
			}
			

		}
}
