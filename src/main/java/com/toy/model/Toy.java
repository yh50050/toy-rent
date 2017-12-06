package com.toy.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 玩具表
 * 
 * @author 枫茗丿love
 *
 */
@Entity
@Table(name = "t_toy")
public class Toy {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long toyId; // id

	@Column(length = 32, nullable = false)
	private String toyName; // 名称

	@Column(length = 32)
	private String toyType; // 类型

	@Column(nullable = false)
	private int toyStock; // 库存量

	@Column(nullable = false)
	private int toyRentAmount; // 出租中

	@Column(nullable = false)
	private int toyScrap; // 报废数

	@Column(nullable = false)
	private int toyNeedPoint; // 所需积分

	@Column(nullable = false)
	private BigDecimal toyDailyRent; // 日租金

	@Column(nullable = false)
	private BigDecimal toyBeyondRent; // 超出租金

	@Column(nullable = false, columnDefinition = "enum('1','0') default '0'")
	private String toyIsRent; // 可租赁

	public long getToyId() {
		return toyId;
	}

	public void setToyId(long toyId) {
		this.toyId = toyId;
	}

	public String getToyName() {
		return toyName;
	}

	public void setToyName(String toyName) {
		this.toyName = toyName;
	}

	public String getToyType() {
		return toyType;
	}

	public void setToyType(String toyType) {
		this.toyType = toyType;
	}

	public int getToyStock() {
		return toyStock;
	}

	public void setToyStock(int toyStock) {
		this.toyStock = toyStock;
	}

	public int getToyRentAmount() {
		return toyRentAmount;
	}

	public void setToyRentAmount(int toyRentAmount) {
		this.toyRentAmount = toyRentAmount;
	}

	public int getToyScrap() {
		return toyScrap;
	}

	public void setToyScrap(int toyScrap) {
		this.toyScrap = toyScrap;
	}

	public int getToyNeedPoint() {
		return toyNeedPoint;
	}

	public void setToyNeedPoint(int toyNeedPoint) {
		this.toyNeedPoint = toyNeedPoint;
	}

	public BigDecimal getToyDailyRent() {
		return toyDailyRent;
	}

	public void setToyDailyRent(BigDecimal toyDailyRent) {
		this.toyDailyRent = toyDailyRent;
	}

	public BigDecimal getToyBeyondRent() {
		return toyBeyondRent;
	}

	public void setToyBeyondRent(BigDecimal toyBeyondRent) {
		this.toyBeyondRent = toyBeyondRent;
	}

	public String getToyIsRent() {
		return toyIsRent;
	}

	public void setToyIsRent(String toyIsRent) {
		this.toyIsRent = toyIsRent;
	}

}
