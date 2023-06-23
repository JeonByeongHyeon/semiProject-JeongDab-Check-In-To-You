package org.kosta.jeongdab.test.boardDetail;

import org.kosta.jeongdab.tou.model.ServiceBoardDAO;

public class TestUnitFindBoardByNo {
	public static void main(String[] args) {
		try {
			System.out.println(ServiceBoardDAO.getInstance().findServiceBoardByNo(21));
		} catch(Exception e){
			e.printStackTrace();
		}

	}

}
