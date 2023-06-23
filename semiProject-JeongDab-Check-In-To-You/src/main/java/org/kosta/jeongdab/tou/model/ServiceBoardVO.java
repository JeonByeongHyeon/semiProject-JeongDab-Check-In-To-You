package org.kosta.jeongdab.tou.model;

import java.util.Date;

public class ServiceBoardVO {
	private long serviceBoardNo;
	private String serviceBoardTitle;
	private String serviceBoardContent;
	private String serviceBoardCreateDate;
	private String serviceDate;
	private long serviceBoardHits;
	private String nation;
	private MemberVO memberVO;

	public ServiceBoardVO() {
		super();
	}

	public ServiceBoardVO(long serviceBoardNo, String serviceBoardTitle, String serviceBoardContent,
			String serviceBoardCreateDate, String serviceDate, long serviceBoardHits, String nation,
			MemberVO memberVO) {
		super();
		this.serviceBoardNo = serviceBoardNo;
		this.serviceBoardTitle = serviceBoardTitle;
		this.serviceBoardContent = serviceBoardContent;
		this.serviceBoardCreateDate = serviceBoardCreateDate;
		this.serviceDate = serviceDate;
		this.serviceBoardHits = serviceBoardHits;
		this.nation = nation;
		this.memberVO = memberVO;
	}

	public long getServiceBoardNo() {
		return serviceBoardNo;
	}

	public void setServiceBoardNo(long serviceBoardNo) {
		this.serviceBoardNo = serviceBoardNo;
	}

	public String getServiceBoardTitle() {
		return serviceBoardTitle;
	}

	public void setServiceBoardTitle(String serviceBoardTitle) {
		this.serviceBoardTitle = serviceBoardTitle;
	}

	public String getServiceBoardContent() {
		return serviceBoardContent;
	}

	public void setServiceBoardContent(String serviceBoardContent) {
		this.serviceBoardContent = serviceBoardContent;
	}

	public String getServiceBoardCreateDate() {
		return serviceBoardCreateDate;
	}

	public void setServiceBoardCreateDate(String serviceBoardCreateDate) {
		this.serviceBoardCreateDate = serviceBoardCreateDate;
	}

	public String getServiceDate() {
		return serviceDate;
	}

	public void setServiceDate(String serviceDate) {
		this.serviceDate = serviceDate;
	}

	public long getServiceBoardHits() {
		return serviceBoardHits;
	}

	public void setServiceBoardHits(long serviceBoardHits) {
		this.serviceBoardHits = serviceBoardHits;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public MemberVO getMemberVO() {
		return memberVO;
	}

	public void setMemberVO(MemberVO memberVO) {
		this.memberVO = memberVO;
	}

	@Override
	public String toString() {
		return "ServiceBoardVO [serviceBoardNo=" + serviceBoardNo + ", serviceBoardTitle=" + serviceBoardTitle
				+ ", serviceBoardContent=" + serviceBoardContent + ", serviceBoardCreateDate=" + serviceBoardCreateDate
				+ ", serviceDate=" + serviceDate + ", serviceBoardHits=" + serviceBoardHits + ", nation=" + nation
				+ ", memberVO=" + memberVO + "]";
	}
	

}