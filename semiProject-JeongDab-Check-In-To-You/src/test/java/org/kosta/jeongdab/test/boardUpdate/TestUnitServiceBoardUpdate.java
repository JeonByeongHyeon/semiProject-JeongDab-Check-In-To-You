package org.kosta.jeongdab.test.boardUpdate;

import org.kosta.jeongdab.tou.model.ServiceBoardDAO;
import org.kosta.jeongdab.tou.model.ServiceBoardVO;

public class TestUnitServiceBoardUpdate {

	public static void main(String[] args) {
		try {
			ServiceBoardVO svo=new ServiceBoardVO();
			svo.setServiceBoardTitle("한번테스트");
			svo.setServiceBoardContent("내용바뀌냐고");
			svo.setServiceDate("2023-7-14");
			svo.setNation("뉴저지로갈꺼래");
			svo.setServiceBoardNo(63);
			ServiceBoardDAO.getInstance().updateServiceBoard(svo);
			System.out.println("수정되땅!");
		}catch(Exception e) {
			e.printStackTrace();
		}	
	}

}
