package com.toy.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 玩具出租表
 * 
 * @author 枫茗丿love
 *
 */
@Entity
@Table(name = "t_toy_rent")
public class ToyRent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long trId; // ID

	@Column(nullable = false)
	private long trToyId; // 玩具ID

	@Column(nullable = false)
	private long trMemberId; // 会员ID

	@Column(length = 20, nullable = false)
	private String trLaseTime; // 租出时间

	@Column(nullable = false)
	private int trDay; // 租赁天数

	@Column(length = 20)
	private String trRturnTime; // 归还时间

	@Column(nullable = true)
	private long trEmpolyeeId; // 办理人员

	@Column(nullable = true, columnDefinition = "enum('1','0') default '0'")
	private String trEmployeeType; // 办理人员类型

	@Column(nullable = true, columnDefinition = "enum('1','0') default '0'")
	private String isReturn; // 是否归还

	@Transient
	private Toy toy; // 玩具

	@Transient
	private Member member; // 会员

	@Transient
	private Object person; // 办理人员

	@Transient
	private int days; // 最终天数

	@Transient
	private BigDecimal money; // 最终租金

	@Transient
	private int isOverdue; // 是否逾期

	public long getTrToyId() {
		return trToyId;
	}

	public void setTrToyId(long trToyId) {
		this.trToyId = trToyId;
	}

	public long getTrMemberId() {
		return trMemberId;
	}

	public void setTrMemberId(long trMemberId) {
		this.trMemberId = trMemberId;
	}

	public long getTrId() {
		return trId;
	}

	public void setTrId(long trId) {
		this.trId = trId;
	}

	public String getTrLaseTime() {
		return trLaseTime;
	}

	public void setTrLaseTime(String trLaseTime) {
		this.trLaseTime = trLaseTime;
	}

	public String getTrRturnTime() {
		return trRturnTime;
	}

	public void setTrRturnTime(String trRturnTime) {
		this.trRturnTime = trRturnTime;
	}

	public int getTrDay() {
		return trDay;
	}

	public void setTrDay(int trDay) {
		this.trDay = trDay;
	}

	public long getTrEmpolyeeId() {
		return trEmpolyeeId;
	}

	public void setTrEmpolyeeId(long trEmpolyeeId) {
		this.trEmpolyeeId = trEmpolyeeId;
	}

	public String getIsReturn() {
		return isReturn;
	}

	public void setIsReturn(String isReturn) {
		this.isReturn = isReturn;
	}

	public Toy getToy() {
		return toy;
	}

	public void setToy(Toy toy) {
		this.toy = toy;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public String getTrEmployeeType() {
		return trEmployeeType;
	}

	public void setTrEmployeeType(String trEmployeeType) {
		this.trEmployeeType = trEmployeeType;
	}

	public Object getPerson() {
		return person;
	}

	public void setPerson(Object person) {
		this.person = person;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public int getIsOverdue() {
		return isOverdue;
	}

	public void setIsOverdue(int isOverdue) {
		this.isOverdue = isOverdue;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "ToyRent{trToyId=" + trToyId + ", trMemberId=" + trMemberId + ", trLaseTime=" + trLaseTime + ",trDay="
				+ trDay + ", trRturnTime=" + trRturnTime + ", trEmpolyeeId=" + trEmpolyeeId + ",trEmployeeType="
				+ trEmployeeType + ",isReturn=" + isReturn + ",toy=" + toy + ",member=" + member + ",person=" + person
				+ ",money=" + money + "}";
	}

}
