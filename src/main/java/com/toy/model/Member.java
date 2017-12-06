package com.toy.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 会员表
 * 
 * @author yanhu
 *
 */
@Entity
@Table(name = "t_member")
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long memberId; // id

	@Column(length = 32, nullable = false)
	private String memberName; // 姓名

	@Column(length = 2, columnDefinition = "enum('男','女') default '男'")
	private String memberSex; // 性别

	@Column(length = 32, unique = true, nullable = false)
	private String memberCode; // 编号

	@Column(length = 11, unique = true, nullable = false)
	private String memberPhone; // 电话

	@Column(columnDefinition = "int default 0")
	private int memberIntegral; // 积分

	@Column(length = 15)
	private String memberCreateDate; // 入会时间

	public long getMemberId() {
		return memberId;
	}

	public void setMemberId(long memberId) {
		this.memberId = memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberSex() {
		return memberSex;
	}

	public void setMemberSex(String memberSex) {
		this.memberSex = memberSex;
	}

	public String getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}

	public String getMemberPhone() {
		return memberPhone;
	}

	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}

	public int getMemberIntegral() {
		return memberIntegral;
	}

	public void setMemberIntegral(int memberIntegral) {
		this.memberIntegral = memberIntegral;
	}

	public String getMemberCreateDate() {
		return memberCreateDate;
	}

	public void setMemberCreateDate(String memberCreateDate) {
		this.memberCreateDate = memberCreateDate;
	}

}
