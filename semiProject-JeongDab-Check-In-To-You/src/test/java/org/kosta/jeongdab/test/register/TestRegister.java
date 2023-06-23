package org.kosta.jeongdab.test.register;

import org.kosta.jeongdab.tou.model.MemberDAO;
import org.kosta.jeongdab.tou.model.MemberVO;

public class TestRegister {
	
	public static void main(String[] args) {
		try {
		MemberVO memberVO =new MemberVO(0, "양성준", "a@a.com", "1234", "120101", 0, "오리", "dfdf");
		System.out.println(memberVO);
		MemberDAO.getInstance().registerMember(memberVO);
		}catch (Exception e) {
			
			e.printStackTrace();
		}
	}

}
