package org.kosta.jeongdab.test.deleteServiceBoard;

import java.sql.SQLException;

import org.kosta.jeongdab.tou.model.ServiceBoardDAO;

public class TestDeleteServiceBoard  {

	public static void main(String[] args) {
		try {
			ServiceBoardDAO.getInstance().deletePostByNo(72);
			System.out.println("삭제완료");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
