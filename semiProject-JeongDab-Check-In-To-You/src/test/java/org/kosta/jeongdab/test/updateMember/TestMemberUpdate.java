package org.kosta.jeongdab.test.updateMember;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.kosta.jeongdab.tou.model.MemberDAO;
import org.kosta.jeongdab.tou.model.MemberVO;

public class TestMemberUpdate {
	public static void main(String[] args) {
		try {
			MemberVO memberVO = new MemberVO();
			memberVO.setMemberName("타란티노");
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date birthDate = (Date) dateFormat.parse("2023-06-27");
			memberVO.setMemberBirth(birthDate);
			memberVO.setMemberAddress("성남시");
			memberVO.setMemberDetailAddress("어딘가");
			memberVO.setMemberNo(64);
			MemberDAO.getInstance().updateMember(memberVO);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
