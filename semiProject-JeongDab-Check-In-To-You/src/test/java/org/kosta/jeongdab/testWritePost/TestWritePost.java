package org.kosta.jeongdab.testWritePost;

import org.kosta.jeongdab.tou.model.MemberVO;
import org.kosta.jeongdab.tou.model.NoticeBoardDAO;
import org.kosta.jeongdab.tou.model.NoticeBoardVO;

public class TestWritePost {
	public static void main(String[] args) {
		try {
			for (int i = 0; i < 100; i++) {
//				ServiceBoardVO sbvo = new ServiceBoardVO();
//				sbvo.setServiceBoardTitle("123" + i);
//				sbvo.setServiceBoardContent("456" + i);
//				sbvo.setNation("한국" + i);
//				MemberVO mvo = new MemberVO();
//				mvo.setMemberNo(1);
//				sbvo.setMemberVO(mvo);
//				ServiceBoardDAO.getInstance().posting(sbvo);
//				System.out.println("등록완료" + i);
				NoticeBoardVO noticeBoardVO = new NoticeBoardVO();
				noticeBoardVO.setNoticeBoardTitle("공지사항" + i);
				noticeBoardVO.setNoticeBoardContent("공지합니다" + i);
				MemberVO mvo = new MemberVO();
				mvo.setMemberNo(1);
				noticeBoardVO.setMemberVO(mvo);
				NoticeBoardDAO.getInstance().writeNotice(noticeBoardVO);
				System.out.println("등록완료" + i);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
