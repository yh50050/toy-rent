package com.toy.service;

import com.toy.model.Admin;
import com.toy.utils.Message;

public interface AdminService {

	/**
	 * 注册一个管理员账号
	 * 
	 * @param admin
	 * @return
	 */
	public Message<?> save(Admin admin);

	/**
	 * 管理员登录系统验证
	 * 
	 * @param adminUserName
	 * @param adminPassword
	 * @return
	 */
	public Message<?> login(String adminUserName, String adminPassword);

	/**
	 * 更新管理员信息（昵称、手机号、邮箱）
	 * 
	 * @param admin
	 * @return
	 */
	public Message<?> update(Admin admin);

	/**
	 * 根据管理员id删除一个管理员账号
	 * 
	 * @param adminId
	 * @return
	 */
	public Message<?> delete(long adminId);

	/**
	 * 根据id得到一个管理员信息
	 * 
	 * @param adminId
	 * @return
	 */
	public Message<?> getById(long adminId);
	/**
	 * 修改管理员级别或是否可用
	 * @param admin
	 * @return
	 */
	public Message<?> updateLevel(Admin admin);
}
