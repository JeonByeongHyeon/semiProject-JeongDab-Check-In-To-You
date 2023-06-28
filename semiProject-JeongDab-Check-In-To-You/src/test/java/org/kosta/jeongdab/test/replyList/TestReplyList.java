package org.kosta.jeongdab.test.replyList;

import java.util.ArrayList;

import org.kosta.jeongdab.tou.model.ReplyDAO;
import org.kosta.jeongdab.tou.model.ReplyVO;

public class TestReplyList {
	
	public static void main(String[] args) {
		try {
			ArrayList<ReplyVO> list = ReplyDAO.getInstance().replyList(65);
			for(ReplyVO rvo : list)
				System.out.println(rvo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
