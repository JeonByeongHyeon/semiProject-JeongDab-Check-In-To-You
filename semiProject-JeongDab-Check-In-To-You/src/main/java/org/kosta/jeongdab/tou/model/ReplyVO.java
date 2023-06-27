package org.kosta.jeongdab.tou.model;

public class ReplyVO {
	private long replyNo;
	private String replyContent;
	private String replyDate;
	private MemberVO memberVO;
	private ServiceBoardVO serviceBoadrdVO;
	public ReplyVO() {
		super();
		
	}
	public ReplyVO(long replyNo, String replyContent, String replyDate, MemberVO memberVO,
			ServiceBoardVO serviceBoadrdVO) {
		super();
		this.replyNo = replyNo;
		this.replyContent = replyContent;
		this.replyDate = replyDate;
		this.memberVO = memberVO;
		this.serviceBoadrdVO = serviceBoadrdVO;
	}
	public long getReplyNo() {
		return replyNo;
	}
	public void setReplyNo(long replyNo) {
		this.replyNo = replyNo;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public String getReplyDate() {
		return replyDate;
	}
	public void setReplyDate(String replyDate) {
		this.replyDate = replyDate;
	}
	public MemberVO getMemberVO() {
		return memberVO;
	}
	public void setMemberVO(MemberVO memberVO) {
		this.memberVO = memberVO;
	}
	public ServiceBoardVO getServiceBoadrdVO() {
		return serviceBoadrdVO;
	}
	public void setServiceBoadrdVO(ServiceBoardVO serviceBoadrdVO) {
		this.serviceBoadrdVO = serviceBoadrdVO;
	}
	@Override
	public String toString() {
		return "ReplyVO [replyNo=" + replyNo + ", replyContent=" + replyContent + ", replyDate=" + replyDate
				+ ", memberVO=" + memberVO + ", serviceBoadrdVO=" + serviceBoadrdVO + "]";
	}
	
 
}
