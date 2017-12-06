package com.toy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 员工信息表
 * 
 * @author 枫茗丿love
 *
 */
@Entity
@Table(name = "t_employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long employeeId; // id

	@Column(length = 32, nullable = false)
	private String employeeName; // 姓名

	@Column(columnDefinition = "enum('男','女') default '男'")
	private String employeeSex; // 性别

	@Column(length = 11, nullable = false, unique = true)
	private String employeePhone; // 电话

	@Column(length = 50, nullable = false, unique = true)
	private String employeeEmail; // 邮箱

	@Column(length = 32, nullable = false)
	private String employeeBranch; // 部门

	@Column(length = 32, nullable = false, unique = true)
	private String employeeCode; // 编号

	@Column(length = 240)
	private String employeeAddress; // 家庭住址

	@Column(length = 32)
	private String employeeBirth; // 出生日期

	@Column(columnDefinition = "enum('1','0') default '1'")
	private String employeeOnJop; // 是否在职

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeSex() {
		return employeeSex;
	}

	public String getEmployeeBranch() {
		return employeeBranch;
	}

	public void setEmployeeBranch(String employeeBranch) {
		this.employeeBranch = employeeBranch;
	}

	public void setEmployeeSex(String employeeSex) {
		this.employeeSex = employeeSex;
	}

	public String getEmployeePhone() {
		return employeePhone;
	}

	public void setEmployeePhone(String employeePhone) {
		this.employeePhone = employeePhone;
	}

	public String getEmployeeEmail() {
		return employeeEmail;
	}

	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getEmployeeAddress() {
		return employeeAddress;
	}

	public void setEmployeeAddress(String employeeAddress) {
		this.employeeAddress = employeeAddress;
	}

	public String getEmployeeBirth() {
		return employeeBirth;
	}

	public void setEmployeeBirth(String employeeBirth) {
		this.employeeBirth = employeeBirth;
	}

	public String getEmployeeOnJop() {
		return employeeOnJop;
	}

	public void setEmployeeOnJop(String employeeOnJop) {
		this.employeeOnJop = employeeOnJop;
	}

}
