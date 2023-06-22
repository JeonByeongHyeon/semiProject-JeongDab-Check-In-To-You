package org.kosta.jeongdab.tou.model;

import java.io.Serializable;
import java.util.Date;

public class MemberVO implements Serializable {
	private static final long serialVersionUID = -7822446113218586799L;
	private long memberNo;
	private String memberEmail;
	private String memberName;
	private String password;
	private Date memberBirth;
	private int memberStatus;
	private String memberAddress;
	private String memberDetailAddress;

	public MemberVO() {
		super();

	}

	public MemberVO(long memberNo, String memberEmail, String memberName, String password, Date memberBirth,
			int memberStatus, String memberAddress, String memberDetailAddress) {
		super();
		this.memberNo = memberNo;
		this.memberEmail = memberEmail;
		this.memberName = memberName;
		this.password = password;
		this.memberBirth = memberBirth;
		this.memberStatus = memberStatus;
		this.memberAddress = memberAddress;
		this.memberDetailAddress = memberDetailAddress;
	}

	public long getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(long memberNo) {
		this.memberNo = memberNo;
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getMemberBirth() {
		return memberBirth;
	}

	public void setMemberBirth(Date memberBirth) {
		this.memberBirth = memberBirth;
	}

	public int getMemberStatus() {
		return memberStatus;
	}

	public void setMemberStatus(int memberStatus) {
		this.memberStatus = memberStatus;
	}

	public String getMemberAddress() {
		return memberAddress;
	}

	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}

	public String getMemberDetailAddress() {
		return memberDetailAddress;
	}

	public void setMemberDetailAddress(String memberDetailAddress) {
		this.memberDetailAddress = memberDetailAddress;
	}

	@Override
	public String toString() {
		return "MemberVO [memberNo=" + memberNo + ", memberEmail=" + memberEmail + ", memberName=" + memberName
				+ ", password=" + password + ", memberBirth=" + memberBirth + ", memberStatus=" + memberStatus
				+ ", memberAddress=" + memberAddress + ", memberDetailAddress=" + memberDetailAddress + "]";
	}

}