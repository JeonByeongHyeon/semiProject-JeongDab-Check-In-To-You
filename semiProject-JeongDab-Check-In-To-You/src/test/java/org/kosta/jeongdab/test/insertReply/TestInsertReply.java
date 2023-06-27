package org.kosta.jeongdab.test.insertReply;

import org.kosta.jeongdab.tou.model.MemberVO;
import org.kosta.jeongdab.tou.model.ReplyDAO;
import org.kosta.jeongdab.tou.model.ReplyVO;
import org.kosta.jeongdab.tou.model.ServiceBoardVO;

public class TestInsertReply {
	
	public static void main(String[] args) {
		
		try {
			ReplyVO rvo = new ReplyVO();
			MemberVO mvo = new MemberVO();
			ServiceBoardVO sbvo = new ServiceBoardVO();
			mvo.setMemberNo(62);
			sbvo.setServiceBoardNo(65);
			rvo.setReplyContent("댓글테스트");
			rvo.setMemberVO(mvo);
			rvo.setServiceBoadrdVO(sbvo);
			ReplyDAO.getInstance().insertReply(rvo);
			System.out.println("댓글 등록 완료");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
