package org.kosta.jeongdab.testWritePost;

import org.kosta.jeongdab.tou.model.MemberVO;
import org.kosta.jeongdab.tou.model.ServiceBoardDAO;
import org.kosta.jeongdab.tou.model.ServiceBoardVO;

public class TestWritePost {
	public static void main(String[] args) {
		try {
			ServiceBoardVO sbvo= new ServiceBoardVO();
			sbvo.setServiceBoardTitle("123");
			sbvo.setServiceBoardContent("456");
			sbvo.setNation("한국");
			MemberVO mvo=new MemberVO();
			mvo.setMemberNo(62);
			sbvo.setMemberVO(mvo);
			ServiceBoardDAO.getInstance().posting(sbvo);
			System.out.println("등록완료");
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
