package org.kosta.jeongdab.test.register;

import java.sql.Date;
import java.text.SimpleDateFormat;

import org.kosta.jeongdab.tou.model.MemberDAO;
import org.kosta.jeongdab.tou.model.MemberVO;

public class TestRegister {

	public static void main(String[] args) {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date birthDate = (Date) dateFormat.parse("2023-06-27");
			MemberVO memberVO = new MemberVO(0, "양성준", "a@a.com", "1234", birthDate, 0, "오리", "dfdf");
			System.out.println(memberVO);
			MemberDAO.getInstance().registerMember(memberVO);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

}
