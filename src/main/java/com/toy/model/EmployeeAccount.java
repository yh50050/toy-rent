package com.toy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 员工账号表
 * 
 * @author 枫茗丿love
 *
 */
@Entity
@Table(name = "t_employee_account")
public class EmployeeAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long accountId;

	@Column(length = 32, nullable = false, unique = true)
	private String employeeCode; // 账号

	@Column(length = 32, nullable = false)
	private String empoyeePassword; // 密码

	@Column(length = 1, nullable = false, columnDefinition = "enum('1','0') default '1'")
	private String employeeAvailable; // 账号是否可用

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getEmpoyeePassword() {
		return empoyeePassword;
	}

	public void setEmpoyeePassword(String empoyeePassword) {
		this.empoyeePassword = empoyeePassword;
	}

	public String getEmployeeAvailable() {
		return employeeAvailable;
	}

	public void setEmployeeAvailable(String employeeAvailable) {
		this.employeeAvailable = employeeAvailable;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

}
