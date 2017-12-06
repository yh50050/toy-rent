package com.toy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 管理员表
 * 
 * @author 枫茗丿love
 *
 */
@ApiModel("管理员类")
@Entity
@Table(name = "t_admin")
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long adminId; // id

	@ApiModelProperty("用户名")
	@Column(length = 32, unique = true, nullable = false)
	private String adminUserName; // 用户名

	@ApiModelProperty("昵称")
	@Column(length = 32)
	private String adminNickname; // 昵称

	@Column(nullable = false, length = 32)
	private String adminName; // 姓名

	@ApiModelProperty("密码")
	@Column(length = 32, nullable = false)
	private String adminPassword; // 密码

	@ApiModelProperty("级别")
	@Column(length = 1, nullable = false, columnDefinition = "int default 1")
	private int adminLevel; // 级别

	@ApiModelProperty("电话")
	@Column(length = 11, unique = true, nullable = false)
	private String adminPhone; // 电话

	@ApiModelProperty("邮箱")
	@Column(length = 50, unique = true, nullable = false)
	private String adminEmail; // 邮箱

	@ApiModelProperty("账号可用")
	@Column(length = 1, nullable = false, columnDefinition = "enum('1','0') default '1'")
	private String adminAvailable; // 账号是否可用

	public long getAdminId() {
		return adminId;
	}

	public void setAdminId(long adminId) {
		this.adminId = adminId;
	}

	public String getAdminUserName() {
		return adminUserName;
	}

	public void setAdminUserName(String adminUserName) {
		this.adminUserName = adminUserName;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	public int getAdminLevel() {
		return adminLevel;
	}

	public void setAdminLevel(int adminLevel) {
		this.adminLevel = adminLevel;
	}

	public String getAdminPhone() {
		return adminPhone;
	}

	public void setAdminPhone(String adminPhone) {
		this.adminPhone = adminPhone;
	}

	public String getAdminEmail() {
		return adminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}

	public String getAdminNickname() {
		return adminNickname;
	}

	public void setAdminNickname(String adminNickname) {
		this.adminNickname = adminNickname;
	}

	public String getAdminAvailable() {
		return adminAvailable;
	}

	public void setAdminAvailable(String adminAvailable) {
		this.adminAvailable = adminAvailable;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

}
