package org.kosta.jeongdab.test1;

import java.sql.SQLException;

import org.kosta.jeongdab.tou.model.MemberDAO;

public class TestUnitLogin {
	public static void main(String[] args) {
		String memberEmail = "cyon8254@gmail.com";
		String password = "1234";
		try {
			MemberDAO.getInstance().login(memberEmail, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
