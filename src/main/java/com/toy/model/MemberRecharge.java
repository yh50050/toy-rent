package com.toy.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 会员充值记录表
 * 
 * @author 枫茗丿love
 *
 */
@Entity
@Table(name = "t_member_recharge")
public class MemberRecharge {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long mrId; // id

	@Column(nullable = false)
	private long mrMemberId; // 会员id

	private BigDecimal mrMoney; // 充值金额

	@Column(length = 20)
	private String mrDateTime; // 充值时间

	public long getMrId() {
		return mrId;
	}

	public void setMrId(long mrId) {
		this.mrId = mrId;
	}

	public long getMrMemberId() {
		return mrMemberId;
	}

	public void setMrMemberId(long mrMemberId) {
		this.mrMemberId = mrMemberId;
	}

	public BigDecimal getMrMoney() {
		return mrMoney;
	}

	public void setMrMoney(BigDecimal mrMoney) {
		this.mrMoney = mrMoney;
	}

	public String getMrDateTime() {
		return mrDateTime;
	}

	public void setMrDateTime(String mrDateTime) {
		this.mrDateTime = mrDateTime;
	}

}
