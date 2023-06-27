package org.kosta.jeongdab.test.updateReply;

import org.kosta.jeongdab.tou.model.ReplyDAO;
import org.kosta.jeongdab.tou.model.ReplyVO;
import org.kosta.jeongdab.tou.model.ServiceBoardVO;

public class TestUnitUpdateReply {

	public static void main(String[] args) {
		try {
			ReplyVO rvo = new ReplyVO();
			rvo.setReplyContent("우리조는 정답이야");
			rvo.setReplyNo(3);
			ReplyDAO.getInstance().updateReplyBoard(rvo);
			System.out.println("답글수정됨!");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
