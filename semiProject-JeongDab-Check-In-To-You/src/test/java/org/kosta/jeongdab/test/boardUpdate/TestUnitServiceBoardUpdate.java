package org.kosta.jeongdab.test.boardUpdate;

import org.kosta.jeongdab.tou.model.ServiceBoardDAO;
import org.kosta.jeongdab.tou.model.ServiceBoardVO;

public class TestUnitServiceBoardUpdate {

	public static void main(String[] args) {
		try {
			ServiceBoardVO svo=new ServiceBoardVO();
			svo.setServiceBoardTitle("업데이트 테스트");
			svo.setServiceBoardContent("업데이트 테스트");
			svo.setServiceDate("2023-7-07");
			svo.setNation("미국");
			svo.setServiceBoardNo(65);
			ServiceBoardDAO.getInstance().updateServiceBoard(svo);
			System.out.println("수정되땅!");
		}catch(Exception e) {
			e.printStackTrace();
		}	
	}

}
