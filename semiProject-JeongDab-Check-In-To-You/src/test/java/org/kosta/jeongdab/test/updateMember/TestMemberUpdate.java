package org.kosta.jeongdab.test.updateMember;

import java.sql.SQLException;

import org.kosta.jeongdab.tou.model.MemberDAO;
import org.kosta.jeongdab.tou.model.MemberVO;

public class TestMemberUpdate {
	public static void main(String[] args) {
		try {
			MemberVO memberVO = new MemberVO();
			memberVO.setMemberName("타란티노");
			memberVO.setMemberBirth("2023-06-27");
			memberVO.setMemberAddress("성남시");
			memberVO.setMemberDetailAddress("어딘가");
			memberVO.setMemberNo(64);
			MemberDAO.getInstance().updateMember(memberVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
