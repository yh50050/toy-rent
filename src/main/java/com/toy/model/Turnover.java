package com.toy.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 营业额表
 * 
 * @author 枫茗丿love
 *
 */
@Entity
@Table(name = "t_turnover")
public class Turnover {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long turnoverId; // ID

	private String turnoverDate; // 时间

	private int turnoverHousesrented; // 日出租量

	private BigDecimal turnoverMoney; // 日营业额

	public long getTurnoverId() {
		return turnoverId;
	}

	public void setTurnoverId(long turnoverId) {
		this.turnoverId = turnoverId;
	}

	public String getTurnoverDate() {
		return turnoverDate;
	}

	public void setTurnoverDate(String turnoverDate) {
		this.turnoverDate = turnoverDate;
	}

	public int getTurnoverHousesrented() {
		return turnoverHousesrented;
	}

	public void setTurnoverHousesrented(int turnoverHousesrented) {
		this.turnoverHousesrented = turnoverHousesrented;
	}

	public BigDecimal getTurnoverMoney() {
		return turnoverMoney;
	}

	public void setTurnoverMoney(BigDecimal turnoverMoney) {
		this.turnoverMoney = turnoverMoney;
	}

}
