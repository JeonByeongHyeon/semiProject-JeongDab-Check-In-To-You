package org.kosta.jeongdab.test.updateMember;

import java.sql.SQLException;

import org.kosta.jeongdab.tou.model.MemberDAO;
import org.kosta.jeongdab.tou.model.MemberVO;

public class TestMemberInfo {
	public static void main(String[] args) {
		try {
			long memberNo = 64;
			MemberVO memberVO = MemberDAO.getInstance().findMemberInfo(memberNo);
			System.out.println(memberVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
