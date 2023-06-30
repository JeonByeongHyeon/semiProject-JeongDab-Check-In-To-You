package org.kosta.jeongdab.tou.model;

public class NoticeBoardVO {
	private long noticeBoardNo;
	private String noticeBoardTitle;
	private String noticeBoardContent;
	private String noticeBoardDate;
	private long noticeBoardHits;
	private MemberVO memberVO;

	public NoticeBoardVO() {
		super();
	}

	public NoticeBoardVO(long noticeBoardNo, String noticeBoardTitle, String noticeBoardContent, String noticeBoardDate,
			long noticeBoardHits, MemberVO memberVO) {
		super();
		this.noticeBoardNo = noticeBoardNo;
		this.noticeBoardTitle = noticeBoardTitle;
		this.noticeBoardContent = noticeBoardContent;
		this.noticeBoardDate = noticeBoardDate;
		this.noticeBoardHits = noticeBoardHits;
		this.memberVO = memberVO;
	}

	public long getNoticeBoardNo() {
		return noticeBoardNo;
	}

	public void setNoticeBoardNo(long noticeBoardNo) {
		this.noticeBoardNo = noticeBoardNo;
	}

	public String getNoticeBoardTitle() {
		return noticeBoardTitle;
	}

	public void setNoticeBoardTitle(String noticeBoardTitle) {
		this.noticeBoardTitle = noticeBoardTitle;
	}

	public String getNoticeBoardContent() {
		return noticeBoardContent;
	}

	public void setNoticeBoardContent(String noticeBoardContent) {
		this.noticeBoardContent = noticeBoardContent;
	}

	public String getNoticeBoardDate() {
		return noticeBoardDate;
	}

	public void setNoticeBoardDate(String noticeBoardDate) {
		this.noticeBoardDate = noticeBoardDate;
	}

	public long getNoticeBoardHits() {
		return noticeBoardHits;
	}

	public void setNoticeBoardHits(long noticeBoardHits) {
		this.noticeBoardHits = noticeBoardHits;
	}

	public MemberVO getMemberVO() {
		return memberVO;
	}

	public void setMemberVO(MemberVO memberVO) {
		this.memberVO = memberVO;
	}

	@Override
	public String toString() {
		return "NoticeBoardVO [noticeBoardNo=" + noticeBoardNo + ", noticeBoardTitle=" + noticeBoardTitle
				+ ", noticeBoardContent=" + noticeBoardContent + ", noticeBoardDate=" + noticeBoardDate
				+ ", noticeBoardHits=" + noticeBoardHits + ", memberVO=" + memberVO + "]";
	}

}
